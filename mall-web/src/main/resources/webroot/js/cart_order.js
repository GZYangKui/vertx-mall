let address = {};
let cartItems = {};
let coupons = [];
//选择发货时间
let selectedDeliveryTime = null;
//选中的优惠券列表
let selectCoupon = [];
//商品信息
let products = null;

/**
 *
 * 初始化商品信息和费用信息
 *
 */
let init = () => {
    let temp = window.sessionStorage.getItem("cartInfo");
    if (temp === null) {
        window.history.go(-1);
    }
    products = JSON.parse(temp);
    initCouponList();
    let requestInfo = {
        url: "/api/order/item",
        type: 'post',
        data: {
            products: products,
            coupons: selectCoupon
        }
    };
    let index = layer.load();
    request(requestInfo, (rs) => {
        layer.close(index);
        $("#products").empty();
        cartItems = rs.data;
        cartItems.products.forEach((value) => $("#products").append(createProduct(value)));
        $("#totalAMount").text("￥ " + cartItems.totalAmount);
        $("#payAmount > span").text("￥ " + cartItems.payAmount);
        $("#freight > span:first-child").text("满" + cartItems.freeFreight + "包邮");
        $("#freight > span:last-child").text("￥ " + cartItems.freight);
        $("#couponDiscount").text("￥ " + cartItems.couponDiscount);
    });

};
/**
 *
 * 初始化优惠券列表
 *
 * @param items
 */
let initCouponList = () => {
    let requestInfo = {
        url: "/api/coupon/useCoupon",
        type: 'POST',
        data: products
    };
    request(requestInfo, (rs) => {
        coupons = rs.data;
        $("#couponInfo > span").text(coupons.length === 0 ? "不可用" : coupons.length + "张可用")
    });
};

let createProduct = (item) => {
    return "<div class='flex-box product'>" +
        "<div style='background-image: url(" + item.pic + ")'></div>" +
        "<div class='flex-box product-info'>" +
        "<div><span>" + item.name + "</span></div>" +
        "<div><span>" + item.subTitle + "</span></div>" +
        "<div><span>￥ " + item.price + "</span></div>" +
        "</div>" +
        "<div><span>x" + item.count + "</span></div>" +
        "</div>"
};
/**
 *
 * 获取默认地址
 *
 */
let getDefaultAddress = () => {
    let requestInfo = {
        url: '/api/address/default',
        type: 'get'
    };
    request(requestInfo, (rs) => {
        let data = rs.data;
        if (Object.keys(data).length === 0) {
            window.location.href = "/address.html";
            return;
        }
        address = data;
        initAddress(address);
    });
};

/**
 *
 * 去选择地址
 *
 *
 */
let toSelectAddress = () => {
    window.location.href = "/address.html";
};

/**
 *
 * 初始化地址信息
 *
 * @param item
 */

let initAddress = (item) => {
    $(".address > div:first-child").text(item.name + "  " + item.phoneNumber);
    $(".address > div:last-child > span:last-child").text(item.province + item.city + (item.region === null ? "" : item.region) + item.detailAddress)
};

/**
 *
 * 选择配送时间
 *
 */
let selectDeliveryTime = () => {
    let deliveryTimeOption = [];

    for (let i = 1; i <= 2; i++) {
        deliveryTimeOption.push(getTime(i))
    }

    let content = "<div class='flex-box select-box' id='selectDialog'>" +
        "<div id='dateBox'></div>" +
        "<div id='detailDateBox'>" +
        "<div class='flex-box'>" +
        "<div id='t1'><span></span></div>" +
        "<div><input type='checkbox' class='check-box-item date-selected' id='tt1'/><label for='tt1'></label></div>" +
        "</div>" +
        "<div class='flex-box'>" +
        "<div id='t2'><span></span></div>" +
        "<div><input type='checkbox' class='check-box-item date-selected' id='tt2'/><label for='tt2'></label></div>" +
        "</div>" +
        "</div>" +
        "</div>";
    layui.use('layer', () => {
        let layer = layui.layer;
        layer.open({
            type: 1,
            title: '选择送达时间',
            content: content,
            area: ['100%', '50%'],
            offset: 'b',
            anim: 2,
            shadeClose: true,
            closeBtn: 1,
            success: (layro, layerIndex) => {
                deliveryTimeOption.forEach((item, _index, _self) => {
                    let leftBox = "<div class='date-item " + (_index === 0 ? "current-date" : "") + "' data-id='" + _index + "'>" +
                        "<div><span>" + item.month + "月" + item.day + "日</span></div>" +
                        "<div><span>" + item.weekday + "</span></div>" +
                        "</div>";
                    $("#dateBox").append(leftBox);
                    if (_index === 0) {
                        $("#t1 span").text(item.data[0]);
                        $("#t2 span").text(item.data[1]);
                    }
                });
                $(".date-item").on('click', function () {
                    $(".date-item").each((i, t) => {
                        if (this === t) {
                            $(t).addClass('current-date');
                            let index = parseInt($(t).data('id'));
                            let tt = deliveryTimeOption[index];
                            $("#t1 span").text(tt.data[0]);
                            $("#t2 span").text(tt.data[1]);
                        } else {
                            $(t).removeClass('current-date');
                        }
                    });
                });
                $(".date-selected").on('click', function () {
                    let id = $(this).attr("id");
                    let index = parseInt($(".current-date").data('id'));
                    selectedDeliveryTime = deliveryTimeOption[index];
                    //选中第一个
                    if (id === "tt1") {
                        selectedDeliveryTime.selected = 0;
                    } else {
                        //选中第二个
                        selectedDeliveryTime.selected = 1;
                    }
                    $("#deliveryInfo > span").text(selectedDeliveryTime.data[selectedDeliveryTime.selected]);
                    layer.close(layerIndex);
                });
            }
        });
    });
};
/**
 *
 * 显示日期
 * @param num　向前/向后推迟几天
 *
 */
let getTime = (num) => {

    let result = {};
    let temp = ["周日", "周一", "周二", "周三", "周四", "周五", "周六"];
    let date = new Date(new Date().getTime() + num * 24 * 60 * 60 * 1000);
    //获取年
    result.year = date.getFullYear();
    //获取月
    result.month = (date.getMonth() + 1 > 9 ? date.getMonth() : "0" + date.getMonth());
    //获取日期
    result.day = (date.getDate() + 1 > 9 ? date.getDate() : "0" + date.getDate());

    if (num === 0) {
        result.weekday = "今天" + temp[date.getDay()];
        result.data = ["今天上午０９:３０前", "今天下午１７:３０前"]
    }
    if (num === 1) {
        result.weekday = "明天" + temp[date.getDay()];
        result.data = ["明天上午０９:３０前", "明天下午１７:３０前"]
    }
    if (num === 2) {
        result.weekday = "后天" + temp[date.getDay()];
        result.data = ["后天上午０９:３０前", "后天下午１７:３０前"]
    }
    return result;
};
/**
 *
 * 显示优惠券列表
 *
 */
let showCoupons = () => {
    if (coupons.length === 0) {
        layer.msg("没有可用的优惠券");
        return;
    }
    let content = "<div id='coupons'>" +
        "<div id='couponItems'></div>" +
        "<div class='coupon-action' onclick='closeCouponDialog()'><button>关闭</button></div>" +
        "</div>";
    layui.use('layer', () => {
        let layer = layui.layer;
        layer.open({
            type: 1,
            title: '选择优惠券',
            content: content,
            area: ['100%', '50%'],
            offset: 'b',
            anim: 2,
            shadeClose: false,
            closeBtn: 0,
            success: (layero, index) => {
                coupons.forEach((item, _index, _self) => {
                    let temp = "<div class='flex-box coupon-item'>" +
                        "<div><span>省" + item.amount + "元：</span><span>" + item.name + "</span></div>" +
                        "<div><input type='checkbox' class='check-box-item coupon-option' id='" + item.id + "'/><label for='" + item.id + "'></label></div>" +
                        "</div>";
                    $("#couponItems").append(temp);
                });

                $(".coupon-option").on('click', function () {
                    $(".coupon-option").each((index, tt) => {
                        if (this !== tt) {
                            $(tt).prop('checked', false);
                        }
                    });
                    selectCoupon = [];
                    selectCoupon.push($(this).attr('id'))
                });
            }
        });
    });
};
/**
 * 提交订单
 *
 */
let handleOrder = () => {
    if (Object.keys(address).length === 0) {
        layer.msg("请先选择送货地址");
        return;
    }
    if (selectedDeliveryTime === null) {
        layer.msg("请选择配送时间");
        return;
    }
    let data = {
        products: products,
        coupons: selectCoupon,
        address: address,
        deliveryTime: selectedDeliveryTime.year + "-" + selectedDeliveryTime.month + '-' + selectedDeliveryTime.day + "(" + selectedDeliveryTime.data[selectedDeliveryTime.selected].slice(2, selectedDeliveryTime.data[selectedDeliveryTime.selected].length) + ")",
        orderType: 0,
        sourceType: 0
    };
    let requestInfo = {
        url: '/api/order/create',
        type: 'POST',
        data: data
    };
    request(requestInfo, (rs) => {
        window.location.href = "/pay_order.html?info=" + JSON.stringify(rs.data);
    });
};

let closeCouponDialog = () => {
    layer.closeAll();
    init();
};

$(document).ready(() => {
    layer.ready(() => {
        init();
        getDefaultAddress();
    });
});