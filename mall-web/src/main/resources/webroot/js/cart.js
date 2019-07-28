let pageNum = 1;
let pageSize = 10;
let cartList = [];

let loadingStatus = false;

let selectedOptions = new Map();

//判断是否在编辑模式
let isEditModel = false;

let init = () => {

    loadingStatus = true;

    let requestInfo = {
        url: '/api/cart/list',
        type: 'get',
        query: {
            pageNum: pageNum,
            pageSize: pageSize
        }
    };

    request(requestInfo, (rs) => {
        let data = rs.data;

        if (data.length > 0) {
            cartList = cartList.concat(data);
            pageNum++;
            loadingStatus = false;
        }

        //如果购物车信息为空 显示信息提示
        if (cartList.length === 0) {
            notFound();
            return;
        }

        data.forEach((item, _index, _self) => {
            $("#carts").append(createCart(item, _index));
        });

        //增加数量
        $(".increase").on('click', function () {
            let tt = $(this).parents('.cart-item');
            let index = tt.data('id');
            updateNUm(cartList[index].id, 1, $(this), index);
        });

        //减少数量
        $(".reduce").on('click', function () {
            let tt = $(this).parents('.cart-item');
            let temp = $(this).parents(".action-box").find("span");
            if (parseInt(temp.text()) === 1) {
                layer.msg("商品数量不能小于1");
                return;
            }
            let index = tt.data('id');
            updateNUm(cartList[index].id, -1, $(this), index);
        });

        //选中/不选中
        $(".item-option").on('click', function () {
            let status = $(this).prop('checked');
            let tt = $(this).parents('.cart-item');
            let index = tt.data('id');
            if (status) {
                selectedOptions.set(index, cartList[index]);
            } else {
                selectedOptions.delete(index);
            }
            computerTotalAmount();
        });
    });
};

/**
 *
 * 创建购物车item
 * @param item 购物车item原始数据
 * @param index 数组中的位置
 *
 */
let createCart = (item, index) => {
    return "<div class='flex-box cart-item' data-id='" + index + "'>" +
        "<div class='flex-box'>" +
        "<div><input type='checkbox' id='" + index + "' class='check-box-item item-option'/>" +
        "<label for='" + index + "'></label></div>" +
        "<div class='cart-pic' style='background-image: url(" + item.productPic + ")'></div>" +
        "</div>" +
        "<div class='flex-box product-info'>" +
        "<div><span>" + item.productName + "</span></div>" +
        "<div><span>" + item.subTitle + "</span></div>" +
        "<div class='flex-box'>" +
        "<div><span>￥ " + item.price + "</span></div>" +
        "<div class='flex-box action-box'>" +
        "<div class='reduce'></div>" +
        "<div><span>" + item.quantity + "</span></div>" +
        "<div class='increase'></div>" +
        "</div>" +
        "</div>" +
        "</div>" +
        "</div>"
};

/**
 *
 * 增加购物车商品数量
 * @param num 数量
 * @param id 购物车item id
 * @param _self
 * @param _index
 */
function updateNUm(id, num, _self, _index) {
    let index = layer.load();
    let requestInfo = {
        url: '/api/cart/updateNum',
        type: "post",
        data: {
            id: id,
            number: num
        }
    };
    request(requestInfo, () => {
        let temp = _self.parents(".action-box").find("span");

        //更新数据
        cartList[_index].quantity = parseInt(temp.text()) + num;

        //更新页面数据
        temp.text(cartList[_index].quantity);

        //关闭进度提示信息
        layer.close(index);

        computerTotalAmount();
    });
}

//更改模式 编辑/常规
let changeModel = () => {
    if (!isEditModel) {
        $(".edit > span").text('完成');
        $("#bottomActionBox").addClass("edit-model");
        $(".action-button").text("删除");
    } else {
        $(".edit > span").text('编辑');
        $("#bottomActionBox").removeClass("edit-model");
        $(".action-button").text("去结算");
    }
    isEditModel = !isEditModel;
};

/**
 *
 * 选择所有
 *
 */
function selectAll() {
    let status = $(this).prop('checked');
    selectedOptions.clear();
    //选中所有
    if (status) {
        cartList.forEach((item, _index) => {
            selectedOptions.set(_index, item)
        });
    }
    computerTotalAmount();
    $(".item-option").prop('checked', status);
}

/**
 *
 *
 * 计算总金额
 *
 *
 */
let computerTotalAmount = () => {
    //编辑模式下 不计算总金额
    if (isEditModel) {
        return;
    }
    if (selectedOptions.size === 0) {
        $("#bottomActionBox > div:nth-child(2) > span:last-child").text("￥ 0");
        return;
    }
    let index = layer.load();
    let products = [];
    selectedOptions.forEach((value, key, _map) => {
        products.push({id: value.productId, count: value.quantity});
    });
    let requestInfo = {
        url: '/api/cart/computer',
        type: 'post',
        data: products
    };
    request(requestInfo, (rs) => {
        //设置金额
        $("#bottomActionBox > div:nth-child(2) > span:last-child").text("￥ " + rs.data.totalAmount);
        layer.close(index);
    });
};

/**
 *
 *
 * 提交数据
 *
 *
 */
let handle = () => {
    if (selectedOptions.size === 0) {
        layer.msg("你还没有选定任何商品");
        return;
    }
    //删除商品信息
    if (isEditModel) {
        let temp = [];
        selectedOptions.forEach((value) => {
            temp.push(value.id)
        });
        let requestInfo = {
            url: '/api/cart/deletes',
            type: 'post',
            data: temp
        };
        let index = layer.load();
        request(requestInfo, (rs) => {
            layer.close(index);
            window.location.reload();
        });
    } else {
        let temp = [];
        selectedOptions.forEach((value) => {
            temp.push({id:value.productId,count:value.quantity});
        });
        window.sessionStorage.setItem("cartInfo", JSON.stringify(temp));
        window.location.href = "/cart-order.html";
    }
};

let notFound = () => {
    $(".edit").addClass("hidden-edit");
    let temp = "<div class='cart-empty'>" +
        "<div></div>" +
        "<div><span>你的购物车空空如也</span></div>" +
        "<div><button onclick='toGuangGuang()'>去逛逛</button></div>" +
        "</div>";
    $("#carts").append(temp);
};

/**
 *
 *  去逛逛
 *
 */
let toGuangGuang = () => {
    window.location.href = "/category.html";
};