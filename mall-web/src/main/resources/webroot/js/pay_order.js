let temp = getUrlParam("info");
if (temp === null || temp === undefined) {
    window.location.href = "/person.html";
}

let info = JSON.parse(decodeURIComponent(temp));


//初始化订单信息
function initOrderInfo() {
    console.log(info);
    let closeTime = (Date.parse(info.createTime) + info.expiration - new Date().getTime()) / 1000;

    let temp = setInterval(function () {
        let leftTime = formatSeconds(closeTime);
        $("#leftTime").text("请在" + leftTime + "内完成支付");
        closeTime--;
        if (closeTime <= 0) {
            clearInterval(temp);
            $("#leftTime").text("订单已关闭");
        }
    }, 1000);
    $("#totalAmount").text("￥ " + info.totalAmount);
    /*
    $(".order-info").find("p").each(function (index, elem) {
        if (index === 0) {
            $(elem).text("订单编号：" + currentOrder.orderSn)
        } else if (index === 1) {
            $(elem).text("创建时间：" + currentOrder.createTime)
        } else {
            $(elem).text("实付金额：￥ " + currentOrder.totalAmount)
        }
    })*/
}


//创建预订单
function createAdvance(index) {

    currentOrder.type = $("input[name='payWay']:checked").data("type");

    let requestInfo = {
        url: '/api/order/advance',
        type: 'post',
        data: currentOrder
    };

    request(requestInfo, function (response) {
        closeLoading(index);
        let data = response.data;

        wx.chooseWXPay({
            timestamp: data.timeStamp,
            nonceStr: data.nonceStr,
            package: data.package,
            signType: 'MD5',
            paySign: data.paySign,
            success: res => {
                // 支付成功后的回调函数
                if (res.errMsg === 'chooseWXPay:ok') {
                    let requestInfo = {
                        url: '/api/cart/deletes',
                        type: 'post',
                        data: {orderSn: currentOrder.orderSn}
                    };
                    request(requestInfo, function (response) {
                        window.location.href = "/order_detail.html?order_sn=" + currentOrder.orderSn
                    });
                }
            },
            fail: rs => {
                layer.msg("支付失败");
            },
            cancel: rs => {
                layer.msg("支付取消")
            }
        });
    });
}

/**
 *
 * 将秒数换成时分秒格式
 *
 */

function formatSeconds(value) {
    let theTime = parseInt(value);// 秒
    let theTime1 = 0;// 分  
    let theTime2 = 0;// 小时  
    if (theTime > 60) {
        theTime1 = parseInt(theTime / 60);
        theTime = parseInt(theTime % 60);
        if (theTime1 > 60) {
            theTime2 = parseInt(theTime1 / 60);
            theTime1 = parseInt(theTime1 % 60);
        }
    }
    let result = "" + parseInt(theTime) + "秒";
    if (theTime1 > 0) {
        result = "" + parseInt(theTime1) + "分" + result;
    }
    if (theTime2 > 0) {
        result = "" + parseInt(theTime2) + "小时" + result;
    }
    return result;
}

//注入微信配置信息
function wxConfig() {
    let requestInfo = {
        url: '/api/wx/config',
        type: 'GET',
        query: {
            url: window.location.href
        }
    };
    request(requestInfo, function (rs) {
        let config = rs.data;
        config.debug = false;
        config.jsApiList = ['chooseWXPay'];
        wx.config(config);
    });
}

$(document).ready(function () {
    //wxConfig();
    initOrderInfo();
});