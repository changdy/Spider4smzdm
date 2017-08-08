/**
 * Created by changdy on 2017/5/21.
 */
$(document).ready(function () {
    window.onresize = adjust;
    $('.add-lazyload').addClass('lazyload');
    window.searchModal = new Vue({
        el: '#search-modal',
        data: {
            titleUnmatch: '',
            titleMatch: '',
            categoryMatchArr: [],
            categoryUnmatchArr: [],
            type: -1,
            ratingCount: 0,
            worthPercent: 0,
            dateRange: '',
            sort: 'hot',
        },
        computed: {
            categoryUnmatchObj: {
                get: function () {
                    return this.getCategoryInfo(this.categoryUnmatchArr);
                },
                set: function (obj) {
                    this.categoryUnmatchArr = this.getCategoryArr(obj);
                }
            },
            categoryMatchObj: {
                get: function () {
                    return this.getCategoryInfo(this.categoryMatchArr);
                },
                set: function (obj) {
                    this.categoryMatchArr = this.getCategoryArr(obj);
                }
            },
            param: function () {
                let param = {};
                param.titleMatch = this.titleMatch.mysplit();
                param.titleUnmatch = this.titleUnmatch.mysplit();
                param.type = this.type;
                param.ratingCount = this.ratingCount;
                param.sort = this.sort;
                let arr = this.dateRange.split(' 至 ');
                if (arr.length) {
                    param.startTime = arr[0];
                    param.endTime = arr[1];
                }
                arr = this.categoryUnmatchObj.titles.mysplit();
                param.categoryUnmatchTitle = arr.slice();
                arr = this.categoryMatchObj.titles.mysplit();
                param.categoryMatchTitle = arr.slice();
                return param;
            }
        },
        methods: {
            getCategoryInfo: function (arr) {
                let titles = '', ids = '';
                for (let category of arr) {
                    titles += category.title + ',';
                    ids += category.id + ',';
                }
                return {
                    titles: titles.endTrim(','),
                    ids: ids.endTrim(',')
                }
            },
            getCategoryArr: function (obj) {
                let category = [];
                if (obj.ids !== '' && obj.titles !== '') {
                    let idArr = obj.ids.split(',');
                    let titleArr = obj.titles.split(',');
                    if (idArr.length === titleArr.length) {
                        idArr.forEach(v => category.push({id: v}));
                        titleArr.forEach((v, i) => category[i].title = v);
                    } else {
                        sweetAlert("id和title长度不一", '', "warning");
                    }
                }
                return category;
            },
            match: function () {
                this.sendToIframe(this.categoryMatchArr, true);
            },
            unMatch: function () {
                this.sendToIframe(this.categoryUnmatchArr, false);
            },
            sendToIframe: function (arr, matchFlag) {
                let msg = {
                    selectArr: arr,
                    type: 'reset',
                    matchFlag: matchFlag
                };
                $('#smzdm-frame').addClass('on-show')[0].contentWindow.postMessage(msg, '*');
            },
            reset: function (title = '') {
                this.titleUnmatch = '';
                this.titleMatch = title;
                this.categoryMatchArr = [];
                this.categoryUnmatchArr = [];
                this.ratingCount = 0;
                this.sort = 'hot';
                this.type = -1;
                this.resetDate();
            },
            resetDate: function () {
                $('#search-modal').find('.date-range').flatpickr({
                    mode: "range",
                    maxDate: 'today',
                    locale: 'zh',
                    wrap: true,
                    defaultDate: [new Date().fp_incr(-15), "today"]
                });
                this.dateRange = $('#search-modal').find('.flatpickr-input').val();
            },
            searchList: function (pageNo) {
                let param = $.extend({limit: page.defaultPageSize,offset: (pageNo - 1) * page.defaultPageSize}, this.param);
                $.post(reqBashPath + 'query-commodity-info', {data:JSON.stringify(param)}).then(data => {
                    $('.lazyloaded').addClass('lazyload').removeClass('lazyloaded');
                    listVue.items = data.rows;
                    page.resetPage(data.total, data.rows.length, pageNo);
                }, data => sweetAlert("失败", data, "error"));
            }
        },
        mounted: function () {
            this.resetDate();
        }
    });

    page = new Vue({
        el: '#pagination',
        data: {
            currentPageNo: 1,
            defaultPageSize: 20,
            itemCount: 1601,
            currentResultLength: 20,
            jumpPageNo: 1
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
                $("body").animate({scrollTop: 0}, 300, searchModal.searchList(pageNo));
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

    searchModal.searchList(1);

    //搜索
    $("#search-btn").click(function () {
        $('#search-modal').modal();
    });

    $('#search-title').bind('keydown', function (event) {
        if (event.keyCode === 13) {
            searchModal.reset($(this).val().trim());
            searchModal.searchList(1);
        }
    });

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
        url: reqBashPath + "user-login",
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

window.addEventListener('message', function (e) {
    let data = e.data;
    if (data.type === 'redundant') {
        let arrTitle = '', ids = '';
        for (let category of data.arrInfo) {
            arrTitle += category.title + ',';
            ids += category.id + ',';
        }
        swal({
                title: "是否删除多余目录",
                text: arrTitle.endTrim(','),
                type: "warning",
                showCancelButton: true,
                cancelButtonText: '取消',
                confirmButtonText: "删除",
                confirmButtonColor: "#DD6B55",
                showLoaderOnConfirm: true,
                closeOnConfirm: false
            },
            function () {
                $.post(reqBashPath + 'delete-category', {ids: ids.endTrim(',')}).then(response => {
                    if (response.count > 0) {
                        swal("成功", `删除了${data.arrInfo.length}个多余目录`, "success");
                    } else {
                        Cookies.remove('user-name');
                        window.location.href = reqBashPath + 'html/commodity-list.html';
                    }
                });
            });
    } else if (data.type === 'selection') {
        $('#smzdm-frame').removeClass('on-show');
        if (data.matchFlag) {
            searchModal.categoryMatchArr = data.arr;
        } else {
            searchModal.categoryUnmatchArr = data.arr;
        }
    }
}, false);