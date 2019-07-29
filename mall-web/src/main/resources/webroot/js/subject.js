let page = 1;
let pageSize = 10;

//加载数据状态 false表示无加载任务 true 表示正在加载
let loadingStatus = false;

//分类id 0代表无分类 获取全部专题
let cateId = 0;

//分类列表
let categories = [];

//获取分类列表
let cateList = () => {
    let requestInfo = {
        url: "/api/subject/cateList",
        type: 'GET',
    };
    request(requestInfo, (rs) => {
        categories = rs.data;
        rs.data.forEach((item, _index, _self) => {
            let cateItem = "<div class='flex-box cate-item' data-id='" + item.id + "'>" +
                "<div style='background-image: url(" + item.icon + ")'></div>" +
                "<div><span>" + item.name + "</span></div>" +
                "</div>";
            $("#subjectCate").append(cateItem);
        });
        $(".cate-item").on('click', function () {
            let temp = parseInt($(this).data('id'));
            if (temp === cateId) {
                return;
            }
            let items = $(".cate-item");
            for (let i = 0; i < items.length; i++) {
                let item = items[i];
                if (this === item) {
                    $(item).addClass("cate-selected")
                } else {
                    $(item).removeClass("cate-selected");
                }
            }
            cateId = temp;
            page = 1;
            $("#subjects").empty();
            getSubjectFromCate()
        })
    });
};


//从指定分类下分页获取专题信息
let getSubjectFromCate = () => {
    $(".ax_default").css("display", "block");
    let requestInfo = {
        url: '/api/subject/list',
        type: 'get',
        query: {
            page: page,
            pageSize: pageSize,
            cateId: cateId
        }
    };

    request(requestInfo, (rs) => {

        loadingStatus = true;

        $(".ax_default").css("display", "none");

        if (rs.data.length > 0) {
            page++;
            rs.data.forEach((item) => {
                createSubject(item);
            });
            $(".subject-item").on('click', function () {
                let id = $(this).data('id');
                window.location.href = '/subject_detail.html?id=' + id;
            });
            loadingStatus = false;
        } else {
            //TODO 提示用户 数据为空

        }


    });
};

//创建专题
let createSubject = (subject) => {

    let category = findCateById(subject.category_id);

    //由于数据库中有的分类已经不存在 故作此处理
    if (category === undefined) {
        return;
    }
    let item = "<div class='layui-card subject-item' data-id='" + subject.id + "'>" +
        "<div class='layui-card-header'>" +
        "<div class='cate-icon' style='background-image: url(" + category.icon + ")'></div>" +
        "<span class='cate-name'>" + category.name + "</span>" +
        "</div>" +
        "<div class='layui-card-body'>" +
        "<div class='subject-icon' style='background-image: url(" + subject.pic + ")'></div>" +
        "<div class='title-box'><span>" + subject.title + "</span></div>" +
        "<div class='description-box'><span>" + subject.description + "</span></div>" +
        "<div class='flex-box action-box'>" +
        "<div><img src='../img/svg/watch.svg' alt=''/><span>&nbsp;&nbsp;" + subject.read_count + "</span></div>" +
        "<div></div>" +
        "</div>" +
        "</div>" +
        "</div>";
    $("#subjects").append(item);
};

//根据分类id查找分类
let findCateById = (id) => {
    for (let i = 0; i < categories.length; i++) {
        if (categories[i].id === id) {
            return categories[i]
        }
    }
};

$(document).ready(() => {
    cateList();
    getSubjectFromCate();
});
