//分类列表
let list = [];
//初始化分类信息
let init = () => {
    let index = layer.load();
    let requestInfo = {
        url: '/api/productCate/list',
        type: 'get'
    };
    request(requestInfo, function (rs) {
        layer.close(index);
        list = rs.data;
        list.forEach((item, index, _self) => {
            createLeftNav(item, index);
            createRightContent(item.children);
        });

        //动态加载js库
        load("/lib/scrolltab.js");

        $(".category-item").on('click', function () {
            let id = $(this).data('id');
            let name = $(this).find('span').text();
            window.location.href = "/category_detail.html?categoryId=" + id + "&name=" + name;
        });

    });
};

//创建左侧导航栏
function createLeftNav(item, index) {
    let temp =
        "<a href='javascript:;' class='" + (index === 0 ? "scrolltab-item crt" : "scrolltab-item") + "'>" +
        "<div class='scrolltab-title'>" + item.name + "</div>" +
        "</a>";
    $("#scrollTabNav").append(temp);
}

//创建右侧二级分类信息
function createRightContent(categories) {
    let items = "<div class='scrolltab-content-item'>";
    group(categories, 2).forEach((_item, _index, _self) => {
        let item = "<div class='flex-box category'>";
        for (let i in _item) {
            item += "<div class='flex-box category-item' data-id='" + _item[i].id + "'>" +
                "<div><img src='http://39.98.190.128/mall-app/img/mainpage/p4.png' alt=''/></div>" +
                "<div><span>" + _item[i].name + "</span></div>" +
                "</div>"
        }
        item += "</div>";
        items += item;
    });

    items += "</div>";

    $("#scrollTabContent").append(items);
}

/**
 *
 * 将数组分组
 *
 * @param array
 * @param subGroupLength
 * @returns {Array}
 */
let group = (array, subGroupLength) => {
    let index = 0;
    let newArray = [];
    while (index < array.length) {
        newArray.push(array.slice(index, index += subGroupLength));
    }
    return newArray;
};

$(document).ready(() => {
    layer.ready(() => init());
});
