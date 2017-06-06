/**
 * Created by changdy on 2017/5/21.
 */
$(document).ready(function () {
    window.onresize = adjust;
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
                scrollTo(0,0);
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
});