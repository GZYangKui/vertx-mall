//时间段
let timeSlots = [];

let currentIndex = 0;

//初始化轮播图
layui.use('carousel', () => {
    let carousel = layui.carousel;
    carousel.render({
        elem: "#test1",
        width: "100%",
        height: "15rem"
    });
});
/**
 *
 * 获取秒杀时间段
 *
 */
let getTimeSlots = () => {
    let requestInfo = {
        url: "/api/secKill/timeSlots",
        type: "get"
    };
    request(requestInfo, (rs) => {
        timeSlots = rs.data;
        timeSlots.forEach((_item, _index, _self) => {
            let item = "";
            if (_index === 0) {
                item = "<li lay-id='" + _item.id + "' class='layui-this'>" + _item.name + "</li>";
            } else {
                item = "<li lay-id='" + _item.id + "'>" + _item.name + "</li>";
            }
            $("#tabHeader").append(item);
            $("#tabContent").append("<div data-flag='" + _index + "'></div>");
        });
        initData();
    });
    layui.use('element', function () {
        let element = layui.element;
        element.on('tab(demo)', function (data) {
            let index = data.index;
            if (index === currentIndex) {
                return;
            }
            initData();
        });
    });
};
/**
 *
 * 初始化数据
 *
 */
let initData = () => {
    let requestInfo = {
        url: "/api/secKill/timeSlotWithProduct",
        method: "get",
        query: {
            timeSlotId: timeSlots[currentIndex].id
        }
    };
    request(requestInfo, (rs) => {
        let target = null;
        //现寻找对应的内容面板
        $("#tabContent > div").each((_index, _item) => {
            let flag = parseInt($(_item).data("flag"));
            if (flag === currentIndex) {
                target = $(_item);
            }
        });
        rs.data.forEach((_item, _index, _self) => {
            target.append(createFlashProduct(_item, _index));
        })
    });
};

/**
 * 创建抢购商品
 *
 * @param item
 */
let createFlashProduct = (item, index) => {
    return "<div class='flex-box' data-index='" + index + "'>" +
        "<div></div>" +
        "<div></div>" +
        "</div>";
};
$(document).ready(() => {
    getTimeSlots();
});