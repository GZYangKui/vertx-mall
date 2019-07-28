let addressId = null;
let isDefault = 1;

//初始化编辑数据
function initEdit() {
    addressId = getUrlParam("address_id");
    if (addressId === null || addressId === undefined) {
        return;
    }
    let index = layer.load();
    let requestInfo = {
        url: '/api/address/detail',
        type: 'GET',
        query: {
            address_id: addressId
        }
    };
    request(requestInfo, function (response) {
        layer.close(index);
        //初始化数据
        let data = response.data;
        $("#name").val(data.name);
        $("#phone").val(data.phoneNumber);
        $("#address").val(data.province + "-" + data.city + (data.region == null ? '' : '-' + data.region));
        $("#detailAddress").val(data.detailAddress);
        layui.use('form', function () {
            let form = layui.form;
            if (data.isDefault !== 1) {
                $("#isDefault").removeAttr("checked");
                form.render();
            }
        });
    })

}

function submitData() {
    //更新地址
    let name = $("#name").val();
    let phone = $("#phone").val();
    let address = $("#address").val();
    let detailAddress = $("#detailAddress").val();

    if (isBlank(name)) {
        showTitleMsg("收货人姓名不能为空!");
        return;
    }
    if (isBlank(phone)) {
        showTitleMsg("收货人手机不能为空!");
        return;
    }
    if (isBlank(address)) {
        showTitleMsg("收货人地址不能为空!");
        return;
    }
    if (isBlank(detailAddress)) {
        detailAddress = "";
    }
    let list = address.toString().split('-');
    let province = list[0];
    let city = list[1];
    let region;
    if (list.length > 1) {
        region = list[2];
    } else {
        region = null;
    }

    let data = {
        name: name,
        phoneNumber: phone,
        province: province,
        city: city,
        region: region,
        detailAddress: detailAddress,
        isDefault: isDefault
    };
    let requestInfo = {
        type: 'post'
    };
    //创建地址
    if (addressId === null || addressId === undefined) {
        requestInfo.url = '/api/address/add';
        //更新地址
    } else {
        requestInfo.url = '/api/address/update';
        data.id = addressId;
    }

    requestInfo.data = data;
    request(requestInfo, function (response) {
        //返回上一页 并且刷新上一页的数据
        window.history.go(-1);
    });

}