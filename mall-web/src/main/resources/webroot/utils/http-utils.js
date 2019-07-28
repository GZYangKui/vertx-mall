/**
 *
 * 发起http请求
 *
 * @param requestInfo 请求信息
 * @example {
 *     url:"/api/user/login,
 *     type:"post/get",
 *     dataType:'json'
 *     query:{},
 *     data:{},
 * }
 */

let request = (requestInfo, resultHandler) => {
    let token = $.cookie("token");
    if (token == null) {
        token = "";
    }
    if (requestInfo.query != null) {
        let temp = "?";
        let index = 0;
        for (let item in requestInfo.query) {
            if (index === Object.keys(requestInfo.query).length - 1) {
                temp += (item + "=" + requestInfo.query[item]);
            } else {
                temp += (item + "=" + requestInfo.query[item] + "&")
            }
            index++;
        }
        requestInfo.url += temp;
    }
    $.ajax({
        url: requestInfo.url,
        type: requestInfo.type,
        dataType: requestInfo.dataType == null ? "json" : requestInfo.dataType,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: JSON.stringify(requestInfo.data),
        headers: {
            Authorization: "Bearer " + token
        },
        success: (res) => {
            if (res.code === 403) {
                layer.msg("登录过期,请重新登录");
                window.location.href = "/login.html";
            } else {
                if (!res.flag) {
                    layer.msg(res.message);
                }
                resultHandler(res);
            }
        },
        error: (e) => {
            console.log(e);
        }
    });

};

//判断字符串是否为空
let isEmpty = (i) => {
    return i === undefined || i === null || i.trim() === "";
};

