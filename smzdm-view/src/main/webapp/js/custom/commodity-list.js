/**
 * Created by changdy on 2017/5/21.
 */
$(document).ready(function () {
    window.onresize = adjust;
    $('.add-lazyload').addClass('lazyload');
    page = new Vue({
        el: '#pagination',
        data: {
            currentPageNo: 1,
            defaultPageSize: 20,
            itemCount: 1601,
            currentResultLength: 20,
            jumpPageNo: 1,
            searchInfo: {
                title: ""
            }
        },
        computed: {
            pageCount: function () {
                return Math.ceil(this.itemCount / this.defaultPageSize);
            },
            pageList: function () {
                if (this.itemCount === 0) {
                    return [];
                } else {
                    let min = Math.max(1, this.currentPageNo - 2);
                    let max = Math.min(this.pageCount, this.currentPageNo + 2);
                    let arr = [];
                    for (; min <= max; min++) {
                        arr.push(min);
                    }
                    return arr;
                }
            },
            startIndex: function () {
                return this.defaultPageSize * (this.currentPageNo - 1) + 1;
            },
            endIndex: function () {
                return this.defaultPageSize * (this.currentPageNo - 1) + this.currentResultLength;
            }
        },
        methods: {
            resetPage: function (total, resultLength, pageNo, pageSize) {
                if (typeof(pageSize) !== "undefined") {
                    this.defaultPageSize = pageSize;
                }
                if (typeof(pageNo) !== "undefined") {
                    this.currentPageNo = pageNo;
                } else {
                    if (total !== 0) {
                        this.currentPageNo = 1;
                    } else {
                        this.currentPageNo = 0;
                    }
                }
                this.currentResultLength = resultLength;
                this.itemCount = total;
            },
            jumpPage: function (pageNo) {
                getList(pageNo);
                scrollTo(0, 0);
            },
            submit: function () {
                if (typeof(this.jumpPageNo) === "number" && this.jumpPageNo < this.pageCount) {
                    this.jumpPage(this.jumpPageNo);
                } else {
                    this.jumpPageNo = 1;
                }
            }
        }
    });
    const today = new moment();
    today.millisecond(0);
    today.second(0);
    today.minute(0);
    today.hour(0);
    dayUnix = today.valueOf();
    yearUnix = (new moment(String(today.year()), 'YYYY')).valueOf();
    let listVue = new Vue({
        el: '.commodity-list',
        data: {
            items: [],
            config: {
                pcWeb: false
            }
        },
        filters: {
            timeHandler: function (value) {
                if (value > dayUnix) {
                    return (new moment(value)).format('HH:mm');
                } else if (value > yearUnix) {
                    return (new moment(value)).format('MM-DD HH:mm');
                } else {
                    return (new moment(value)).format('YYYY-MM-DD HH:mm');
                }
            },
            hrefHandler: function (val) {
                return "http://www.smzdm.com/p/" + val;
            }
        }
    });

    function getList(pageNo) {
        $.get(location.origin + "/query-list",
            {sort: "id", order: "desc", offset: (pageNo - 1) * page.defaultPageSize, limit: page.defaultPageSize, search: page.searchInfo.title},
            function (data) {
                listVue.items = data.rows;
                page.resetPage(data.total, data.rows.length, pageNo);
            },
            "json"
        );
    }

    //搜索
    $("#search-btn").click(function () {
        page.searchInfo.title = $("#search-title").val().trim();
        getList(1);
    });
    getList(1);
    adjust();
    function adjust() {
        if ($(window).width() <= 768) {
            listVue.config.pcWeb = false;
            $(".container").addClass("mobile-adupt");
        } else {
            listVue.config.pcWeb = true;
            $(".container").removeClass("mobile-adupt");
        }
    }

    //过滤接口
    $('#filter-btn').click(function () {
        let name = Cookies.get('user-name');
        if (typeof name !== 'undefined') {
            window.location.href = 'filter-list.html';
        } else {
            $('#login-modal').modal();
        }
    });
    //登录按钮
    $("#login-btn").click(function () {
        loginIn();
    });
    //点X隐藏
    $('.alert-dismissable button').click(function () {
        $(this).closest('.fade-alert').removeClass('on-alert');
    });
    //input效果
    $('.input-box').click(function () {
        $(this).addClass('focus-on');
        $(this).children('input').focus();
    });
    $('.input-box input').blur(function () {
        if ($(this).val() === '') {
            $(this).parent().removeClass('focus-on');
        }
    }).focus(function () {
        $(this).parent().addClass('focus-on');
    });
});
//登录接口
function loginIn() {
    $.ajax({
        url: location.origin + "/login-controller",
        dataType: "json",
        data: {
            name: $('#user-name').val(),
            pwd: $('#user-pwd').val(),
        },
    }).then(function (data) {
        if (data.result === true) {
            window.location.href = 'filter-list.html';
        } else {
            $('#login-modal').find('.fade-alert').addClass('on-alert');
        }
    });
}