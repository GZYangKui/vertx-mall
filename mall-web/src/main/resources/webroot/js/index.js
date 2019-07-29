//广告位
let advertise = [];

/**
 *
 * 初始化首页信息
 *
 */
let init = () => {
    getAdvertiseList();
    getRecommendSubject();
};
/**
 *
 * 得到广告位列表
 *
 */
let getAdvertiseList = () => {
    let requestInfo = {
        url: "/api/homeAdvertise/list",
        type: "get",
        query: {
            type: 0
        }
    };
    request(requestInfo, (rs) => {
        advertise = rs.data;
        advertise.forEach((_item, _index, _self) => {
            let item = "<div class='carousel-item' data-index='" + _index + "'>" +
                "<img src='" + _item.pic + "' alt=''>" +
                "</div>";
            $("#carouselItem").append(item);
        });
        //初始化轮播图信息
        layui.use('carousel', () => {
            let carousel = layui.carousel;
            carousel.render({
                elem: '#advertises',
                interval: 3000,
                anim: 'fade',
                height: '15rem',
                width: '100%'
            })
        });
        //点击轮播图触发
        $(".carousel-item").on('click', function () {
            let index = parseInt($(this).data('index'));
            let url = advertise[index].url;
            if (!isEmpty(url)) {
                window.location.href = url;
            }
        });
    });

};

/**
 *
 * 获取首页推荐专题
 *
 */
let getRecommendSubject = () => {
    let requestInfo = {
        url: "/api/homeSubject/list",
        type: 'get'
    };
    request(requestInfo, (rs) => {

    });
};

$(document).ready(() => {
    init();
});