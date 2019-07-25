let login = () => {
    let username = $("#username").val();
    let password = $("#password").val();
    if (isEmpty(username) || isEmpty(password)) {
        return;
    }
    let requestInfo = {
        url: "/api/user/login",
        type: "post",
        data: {
            username: username,
            password: password
        }
    };
    request(requestInfo, (rs) => {
        //登录成功
        if (rs.code===200&&rs.flag){
            //设置过期时间为2h
            let expires = new Date().getTime()+120*60*1000;
            $.cookie("token",rs.data.token,{expires:expires,path:'/'});
            window.location.href = "index.html";
        }
    });
};

