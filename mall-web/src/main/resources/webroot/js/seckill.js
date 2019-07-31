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
            $("#tabContent").append("<div></div>");
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

    });
};
$(document).ready(() => {
    getTimeSlots();
});