//默认查询全部订单
let currentIndex = 10;

//分页查询参数
let pageNum = 1;
let pageSize = 10;

//加载数据状态 false表示无加载任务 true 表示正在加载
let loadingStatus = false;

/**
 *
 * 设置默认选中
 *
 */
let initTab = () => {
    let _tt = [10, 0, 1, 3, 4];
    let index = _tt.indexOf(currentIndex);
    $(".layui-tab-title > li").each((i, item) => {
        if (i === index) {
            $(item).addClass("layui-this");
        } else {
            $(item).removeClass("layui-this");
        }
    });
    $(".layui-tab-item").each((i, item) => {
        if (i === index) {
            $(item).addClass("layui-show");
        } else {
            $(item).removeClass("layui-show");
        }
    });
};

if (getUrlParam("currentIndex") !== null) {
    currentIndex = parseInt(getUrlParam("currentIndex"));
}
/**
 *
 * 初始化数据
 *
 */
let init = (isClear) => {

    if (isClear !== undefined) {
        getCurrentTab().empty();
    }

    $(".ax_default").css("display", "block");

    loadingStatus = true;

    let requestInfo = {
        url: "/api/order/list",
        query: {
            type: currentIndex,
            pageNum: pageNum,
            pageSize: pageSize
        }
    };
    request(requestInfo, (rs) => {
        $(".ax_default").css("display", "none");
        let target = getCurrentTab();
        let data = rs.data;
        if (data.length > 0) {
            loadingStatus = false;
            pageNum++;
            data.forEach((item, index, _self) => {
                target.append(loadOrderInfo(item));
            });

            //调起重新支付
            $(".pay").on('click', function () {
                let id = parseInt($(this).data('flag'));

            });

            //关闭订单
            $(".cancel").on('click', function () {
                let index = layer.load();
                let id = parseInt($(this).data('flag'));
                let requestInfo = {
                    url:'/api/order/close',
                    type:'post',
                    data:{
                        orderId:id
                    }
                };
                request(requestInfo,(rs)=>{
                    layer.close(index);
                    init(true)
                })
            });

            //咨询客服
            $(".consult").on('click', function () {

            });

            //确定收货
            $(".receipt").on('click', function () {
                let index = layer.load();
                let id = parseInt($(this).data('flag'));
                let requestInfo = {
                    url: '/api/order/receipt',
                    type: 'post',
                    data: {
                        orderId: id
                    }
                };
                request(requestInfo, (rs) => {
                    layer.close(index);
                    init(true);
                });
            });

            //删除订单
            $(".delete").on('click', function () {
                let index = layer.load();
                let id = parseInt($(this).data('flag'));
                let requestInfo = {
                    url: '/api/order/delete',
                    type: 'post',
                    data: {
                        orderId: id
                    }
                };
                request(requestInfo, (rs) => {
                    layer.close(index);
                    init(true);
                });
            })
        }
    })
};

/**
 *
 * 加载订单item
 *
 */
let loadOrderInfo = (item) => {
    return "<div class='order-info'>" +
        "<div class='flex-box order-info-box'>" +
        "<div><span>订单编号:" + item.orderSn + "</span></div>" +
        "<div><span>" + formatStatus(item.status) + "</span></div>" +
        "</div>" +
        "<div class='product-box'>" + loadOrderItem(item.items) + "</div>" +
        "<div class='flex-box action-box'>" +
        "<div><span>" + formatMoney(item.status, item.totalAmount) + "</span></div>" +
        "<div>" + formatAction(item.status, item.orderId) + "</div>" +
        "</div>" +
        "</div>";
};

/**
 *
 * 加载订单item
 *
 */
let loadOrderItem = (items) => {
    let itemList = "";
    items.forEach((item, index, _self) => {
        itemList +=
            "<div class='flex-box'>" +
            "<div class='product-icon'><img src='" + item.productPic + "'></div>" +
            "<div class='flex-box product-info'>" +
            "<div><span>" + item.productName + "</span></div>" +
            "<div><span>" + (item.subTitle == null ? "" : item.subTitle) + "</span></div>" +
            "<div><span>￥ " + item.price + "</span></div>" +
            "</div>" +
            "<div><span>x" + item.quantity + "</span></div>" +
            "</div>"
    });
    return itemList;
};

let formatStatus = (status) => {
    if (status === 0) {
        return "等待付款"
    }
    if (status === 1) {
        return "等待发货"
    }
    if (status === 2) {
        return "等待收货"
    }
    if (status === 3) {
        return "交易完成"
    }
    if (status === 4) {
        return "交易关闭"
    }
    return ""
};
let formatMoney = (status, amount) => {
    if (status === 0 || status === 4) {
        return "应付金额： ￥" + amount
    } else {
        return "已付金额: ￥" + amount
    }
};

let formatAction = (status, id) => {
    let temp = "<div>";
    if (status === 0) {
        temp += "<button class='cancel' data-flag='" + id + "'>取消订单</button>";
        temp += "<button class='pay' data-flag='" + id + "'>立即付款</button>";
    }
    if (status === 1) {
        temp += "<button class='consult'>咨询客服</button>"
    }
    if (status === 2) {
        temp += "<button class='receipt' data-flag='" + id + "'>确认收货</button>"
    }
    if (status === 3 || status === 4) {
        temp += "<button class='delete' data-flag='" + id + "'>删除订单</button>"
    }
    temp += "</div>";
    return temp
};
/**
 *
 * 获取当前选中tab
 *
 */
let getCurrentTab = () => {
    let target;
    $(".layui-tab-item").each((index, item) => {
        let _t = parseInt($(item).data("flag"));
        if (_t === currentIndex) {
            target = $(item);
        }
    });
    return target;
};

/**
 *
 * 重新初始分页参数
 *
 */
let resetPageParam = () => {
    pageNum = 1;
    pageSize = 10;
    loadingStatus = true;
};

