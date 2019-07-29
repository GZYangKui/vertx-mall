let init = () => {
    let id = getUrlParam("id");
    let requestInfo = {
        url: '/api/subject/detail',
        type: 'get',
        query: {
            subjectId: id
        }
    };
    request(requestInfo, (rs) => {
        let data = rs.data;
        initCarousel(data.pic, data.albumPics);
        $("#subjectContent").append(data.content);
        $(".subject-name").text(data.title);
        $(".category-icon").css("background-image", "url("+data.categoryInfo.icon+")");
        $(".category-name").text(data.categoryInfo.name);
        $(".create-time").text(data.create_time.replace('T', " "));

        // data.products.forEach((item, _index, _self) => {
        //     $("#products").append(createProduct(item));
        // });

        $(".add-cart").on('click', function () {
            event.stopPropagation();
            let id = parseInt($(this).data("id"));
            let requestInfo = {
                url: "/api/cart/add",
                type: "post",
                data: {
                    productId: id,
                    quantity: 1
                }
            };
            request(requestInfo, (rs) => {
                alert("加入购物车成功");
            });
        });
    });
};


//初始化轮播图
function initCarousel(pic, str) {
    let temp = "<div carousel-item>";
    if (!isEmpty(pic)) {
        temp += "<div><img src='" + pic + "' alt='failed' class='item-pic'/></div>"
    }
    if (!isEmpty(str)) {
        str.split(',').forEach((item, _index, _self) => {
            temp += "<div><img src='" + item + "' alt='failed' class='item-pic'/></div>"
        });
    }
    temp += "</div>";
    $("#test10").append(temp);
    let ins;
    layui.use(['carousel'], function () {
        let carousel = layui.carousel;

        //图片轮播
        ins = carousel.render({
            elem: '#test10',
            width: '100%',
            height: '4.0rem',
            interval: 5000,
            arrow: 'none'
        });
    });
    $("#test10").on("touchstart", function (e) {
        let startX = e.originalEvent.targetTouches[0].pageX;//开始坐标X
        $(this).on('touchmove', function (e) {
            arguments[0].preventDefault();//阻止手机浏览器默认事件
        });
        $(this).on('touchend', function (e) {
            let endX = e.originalEvent.changedTouches[0].pageX;//结束坐标X
            e.stopPropagation();//停止DOM事件逐层往上传播
            if (endX - startX > 30) {
                ins.slide("sub");
            } else if (startX - endX > 30) {
                ins.slide("add");
            }
            $(this).off('touchmove touchend');
        });
    });

}

//创建商品
function createProduct(item) {
    let singleProduct = "<div class='product' data-id='" + item.id + "'>" +
        "<div class='product-icon'>" +
        "<i class='" + (item.recommandStatus === 1 ? "recommend-icon" : "") + "'></i>" +
        "<img src='" + item.pic + "' alt='商品图片不存在'/>" +
        "</div>" +
        "<div class='product-info'>" +
        "<div class='product-name'><span>" + item.name + (item.unit !== null && item.unit !== "" ? "/" + item.unit : "") + "</span></div>" +
        "<div class='product-price'><span>￥&nbsp;" + item.price + "</span></div>" +
        "<div class='flex-box'>" +
        "<div class='product-sale'><span>已售&nbsp;" + item.sale + "</span></div>" +
        "<div><span class='add-cart' data-id='" + item.id + "'></span></div>" +
        "</div>" +
        "</div>" +
        "</div>";
    return singleProduct;
}

$(document).ready(() => {
    init();
});