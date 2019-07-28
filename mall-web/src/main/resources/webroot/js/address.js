let pageNum = 1;
let pageSize = 10;
//收货地址列表
let address = [];

let loadingStatus = false;

/**
 *
 * 获取地址列表
 *
 */
let init = () => {
    let index = layer.load();
    loadingStatus = true;
    let requestInfo = {
        url: "/api/address/list",
        type: 'get',
        query: {
            pageSize: pageSize,
            pageNum: pageNum
        }
    };
    request(requestInfo, (rs) => {
        layer.close(index);
        let data = rs.data;
        if (data.length > 0) {
            pageNum++;
            loadingStatus = false;
        }
        address = address.concat(data);
        data.forEach((item, _index, _self) => $("#container").append(createItem(item, _index)));

        $(".checked-item").on('click', function () {
            let index = $(this).parents(".address-item").data('index');
            updateDefault(index);
        });

        $(".edit-box").on('click', function () {
            let index = $(this).parents(".address-item").data('index');
            window.location.href = "/add_address.html?address_id=" + address[index].id;
        });

        $(".delete-box").on('click', function () {
            let index = $(this).parents(".address-item").data('index');
            let requestInfo = {
                url: '/api/address/delete',
                type: 'POST',
                data: {addressId: address[index].id}
            };
            request(requestInfo, (rs) => {
                window.location.reload();
            });
        });
    });
};
/**
 *
 * 创建收货地址
 *
 * @param item
 */
let createItem = (item, index) => {
    return "<div class='address-item' data-index='" + index + "'>" +
        "<div class='address-info'>" +
        "<div><span>" + item.name + " " + item.phoneNumber + "</span><span class='non-default " + (item.isDefault === 1 ? 'default-address' : '') + "'>默认</span></div>" +
        "<div><span>" + item.province + item.city + (item.region === null ? "" : item.region) + item.detailAddress + "</span></div>" +
        "</div>" +
        "<div class='flex-box action-box'>" +
        "<div><input type='checkbox' class='checked-item' id='item" + index + "'/><label for='item" + index + "'></label>&nbsp;&nbsp;<span>设为默认</span></div>" +
        "<div class='flex-box'>" +
        "<div class='flex-box edit-box'>" +
        "<div></div>" +
        "<div><span>编辑</span></div>" +
        "</div>" +
        "<div class='flex-box delete-box'>" +
        "<div></div>" +
        "<div><span>删除</span></div>" +
        "</div>" +
        "</div>" +
        "</div>" +
        "</div>"
};
/**
 *
 * 更新默认地址
 *
 * @param index
 */
let updateDefault = (index) => {
    let requestInfo = {
        url: '/api/address/updateDefault',
        type: 'post',
        data: {addressId: address[index].id}
    };
    request(requestInfo, (rs) => {
        window.location.reload();
    });
};