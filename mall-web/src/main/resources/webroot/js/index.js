//广告位
let advertise = [];

//推荐专题
let recommendSubject = [];


/**
 *
 * 得到广告位列表
 *
 */
let getAdvertiseList = () => {
    let requestInfo = {
        url: "/api/homeAdvertise/list",
        type: "get",
        query: {
            type: 0
        }
    };
    request(requestInfo, (rs) => {
        advertise = rs.data;
        advertise.forEach((_item, _index, _self) => {
            let item = "<div class='carousel-item' data-index='" + _index + "'>" +
                "<img src='" + _item.pic + "' alt=''>" +
                "</div>";
            $("#carouselItem").append(item);
        });
        //初始化轮播图信息
        layui.use('carousel', () => {
            let carousel = layui.carousel;
            carousel.render({
                elem: '#advertises',
                interval: 3000,
                anim: 'fade',
                height: '18rem',
                width: '100%'
            })
        });
        //点击轮播图触发
        $(".carousel-item").on('click', function () {
            let index = parseInt($(this).data('index'));
            let url = advertise[index].url;
            if (!isEmpty(url)) {
                window.location.href = url;
            }
        });
    });

};

/**
 *
 * 获取首页推荐专题
 *
 */
let getRecommendSubject = () => {
    let requestInfo = {
        url: "/api/homeSubject/list",
        type: 'get'
    };
    request(requestInfo, (rs) => {
        recommendSubject = rs.data;
        recommendSubject.forEach((_item, _index, _self) => {
            let subject = _item.subject;
            let item = "<div class='carousel-item flex-box recommend-item'  data-index='" + _index + "'>" +
                "<div style='background-image: url(" + subject.pic + ")'></div>" +
                "<div>" +
                "<p><span>" + subject.title + "</span></p>" +
                "<p><span>" + subject.description + "</span></p>" +
                "</div>" +
                "</div>";
            $("#recommendItem").append(item);
        });
        //初始化推荐专题轮播图信息
        layui.use('carousel', () => {
            let carousel = layui.carousel;
            carousel.render({
                elem: '#recommendSubject',
                interval: 5000,
                anim: 'fade',
                height: '22rem',
                width: '100%'
            });
        });
        //点击推荐专题触发=>跳到详情页面
        $(".recommend-item").on('click', function () {
            let index = parseInt($(this).data('index'));
            let id = recommendSubject[index].subject.id;
            if (!isEmpty(id)) {
                window.location.href = "/subject_detail.html?id=" + id;
            }
        });
    });
};

/**
 *
 * 获取今日推荐商品信息
 *
 */
let todayRecommend = () => {
    let requestInfo = {
        url: "/api/product/recommend",
        method: 'get',
        query: {page: 1, pageSize: 10}
    };
    request(requestInfo, (rs) => {
        getRandomArrayElements(rs.data, 4).forEach((_item, _index, _self) => {
            let item = "<div>" +
                "<div style='background-image: url(" + _item.pic + ")'>" +
                "<i class='recommend-icon'></i>" +
                "</div>" +
                "<div>" +
                "<p><span>" + _item.title + "</span></p>" +
                "<p><span>" + _item.subtitle + "</span></p>" +
                "</div>" +
                "</div>";
            if (_index <= 1) {
                $("#today01").append(item);
            } else {
                $("#today02").append(item);
            }
        });
    });
};

//获取秒杀信息
let getSecKill = () => {
    let requestInfo = {
        url: "/api/secKill/home",
        method: "get"
    };
    request(requestInfo, (rs) => {
        let data = rs.data;
        if (isEmpty(data)) {
            return
        }
        let now = new Date();
        let temp = rs.data.end_time.split(":");
        let endTime = new Date(now.getFullYear(), now.getMonth(), now.getDate(), parseInt(temp[0]), parseInt(temp[1]));

        let leftSeconds = (endTime.getTime() - now.getTime()) / 1000;

        let interval = setInterval(function () {
            let leftTime = formatSeconds(leftSeconds);

            $("#secKillLeftTime > span:first-child").text(leftTime.hour);
            $("#secKillLeftTime > span:nth-child(2)").text(leftTime.minute);
            $("#secKillLeftTime > span:last-child").text(leftTime.second);

            leftSeconds--;
            if (leftSeconds <= 0) {
                clearInterval(interval);
                $("#secKillLeftTime").html("<span>本场次秒杀已结束</span>");
            }
        }, 1000);

        getRandomArrayElements(data.products, 4).forEach((_item, _index, _self) => {
            let product = _item.product;
            let item = "<div class='flash_item'>" +
                "<div style='background-image: url(" + product.pic + ")'>" +
                "<i class='seckill-price'>￥" + (product.price/100) + "</i>" +
                "</div>" +
                "<div>" +
                "<p class='flash-price'>秒杀价 ￥"+(_item.flash_promotion_price)/100+"</p>" +
                "<p><span>" + product.title + "</span></p>" +
                "<p><span>" + product.subtitle + "</span></p>" +
                "</div>" +
                "</div>";
            if (_index <= 1) {
                $("#secKill01").append(item);
            } else {
                $("#secKill02").append(item);
            }
        });
        $(".flash_item").on("click", function () {
            window.location.href = "/seckill_list.html";
        });
    });
};

/**
 *
 *
 * 随机从数组中去num个元素
 * @param arr 原始数组
 * @param num 取元素的个数
 * @returns {any[]} 返回一个新数组
 */
const getRandomArrayElements = (arr, num) => {
    // 新建一个数组,将传入的数组复制过来,用于运算,而不要直接操作传入的数组;
    let temp_array = new Array();
    for (let index in arr) {
        temp_array.push(arr[index]);
    }
    // 取出的数值项,保存在此数组
    let return_array = new Array();
    for (let i = 0; i < num; i++) {
        // 判断如果数组还有可以取出的元素,以防下标越界
        if (temp_array.length > 0) {
            // 在数组中产生一个随机索引
            let arrIndex = Math.floor(Math.random() * temp_array.length);
            // 将此随机索引的对应的数组元素值复制出来
            return_array[i] = temp_array[arrIndex];
            // 然后删掉此索引的数组元素,这时候temp_array变为新的数组
            temp_array.splice(arrIndex, 1);
        } else {
            // 数组中数据项取完后,退出循环,比如数组本来只有10项,但要求取出20项.
            break;
        }
    }
    return return_array;
};

$(document).ready(() => {
    getAdvertiseList();
    getRecommendSubject();
    todayRecommend();
    getSecKill();
});