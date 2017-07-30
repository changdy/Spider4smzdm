/**
 * Created by changdy on 2017/7/9.
 */
$(document).ready(function () {
    window.filterModal = new Vue({
        el: '#filter-info',
        data: {
            id: -1,
            name: '',
            titleUnmatch: '',
            titleMatch: '',
            categoryMatchArr: [],
            categoryUnmatchArr: [],
            ratingCount: 0,
            worthPercent: 0
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
            message: function () {
                return this.id === -1 ? '新增过滤' : '修改过滤';
            },
            ignoreComment: function () {
                return this.ratingCount + this.worthPercent ? 0 : 1;
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
            reset: function () {
                this.id = -1;
                this.name = '';
                this.titleUnmatch = '';
                this.titleMatch = '';
                this.categoryMatchArr = [];
                this.categoryUnmatchArr = [];
                this.ratingCount = 0;
                this.worthPercent = 0;
            },
            initVal: function (row) {
                this.id = row.id;
                this.name = row.name;
                this.titleUnmatch = row.titleUnmatch;
                this.titleMatch = row.titleMatch;
                this.categoryMatchObj = {
                    ids: row.categoryMatchIds,
                    titles: row.categoryMatch
                };
                this.categoryUnmatchObj = {
                    ids: row.categoryUnmatchIds,
                    titles: row.categoryUnmatch
                };
                this.ratingCount = row.ratingCount;
                this.worthPercent = row.worthPercent;
            },
            submit: function () {
                let str = this.categoryMatchObj.titles + this.categoryUnmatchObj.titles + this.titleUnmatch + this.titleMatch;
                if (str === '') {
                    sweetAlert("缺少参数", '筛选条件不能全为空', "warning");
                    return;
                }
                let param = {
                    id: this.id === -1 ? null : this.id,
                    categoryMatch: this.categoryMatchObj.titles,
                    categoryUnmatch: this.categoryUnmatchObj.titles,
                    categoryMatchIds: this.categoryMatchObj.ids,
                    categoryUnmatchIds: this.categoryUnmatchObj.ids,
                    ignoreComment: this.ignoreComment
                };
                $.post(reqBashPath + 'operate-filter', $.extend(this.$data, param)).then(data => {
                    if (data.count > 0) {
                        $('#filter-modal').modal('hide');
                        sweetAlert("成功", '', "success");
                    } else {
                        window.location.href = reqBashPath + 'html/commodity-list.html';
                    }
                }, data => sweetAlert("失败", data, "error"));
            }
        }
    });
    let $table = $('#filter-table');
    $table.bootstrapTable({
        url: reqBashPath + 'query-filter',
        dataType: "json",
        striped: true,                      //是否显示行间隔色
        search: true,
        checkbox: true,
        pagination: true, //分页
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 25,                       //每页的记录行数（*）
        pageList: [25, 50, 100],        //可供选择的每页的行数（*）
        showColumns: true,
        toolbar: '#toolbar',
        sidePagination: "server", //服务端处理分页
        columns: [{
            field: 'state',
            checkbox: true,
        }, {
            field: 'id',
            align: 'center',
            valign: 'middle',
            title: '序号',
            width: 70,
            visible: false
        }, {
            field: 'name',
            align: 'center',
            valign: 'middle',
            title: '名称'
        }, {
            field: 'titleMatch',
            align: 'center',
            valign: 'middle',
            title: '标题匹配',
        }, {
            field: 'titleUnmatch',
            align: 'center',
            valign: 'middle',
            title: '标题排除'
        }, {
            field: 'categoryMatch',
            align: 'center',
            valign: 'middle',
            title: '目录匹配'
        }, {
            field: 'categoryUnmatch',
            align: 'center',
            valign: 'middle',
            title: '目录排除'
        }, {
            field: 'ratingCount',
            align: 'center',
            valign: 'middle',
            title: '关注度',
        }, {
            field: 'worthPercent',
            align: 'center',
            valign: 'middle',
            title: '点赞比例',
        }]
    });
    let $remove = $('#delete-item'), $operate = $('#operate-item'), $addItem = $('#add-item');
    $table.on('check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table', function () {
        let selectArrLength = $table.bootstrapTable('getSelections').length;
        $remove.prop('disabled', !selectArrLength);
        $operate.prop('disabled', selectArrLength !== 1);
    });
    $remove.click(function () {
        let ids = getIdSelections().join(',');
        $.post(reqBashPath + 'remove-filter', {
            ids: ids
        }).then(data => {
            if (data.count > 0) {
                $table.bootstrapTable('remove', {
                    field: 'id',
                    values: getIdSelections()
                });
                $remove.prop('disabled', true);
                $operate.prop('disabled', true);
            } else {
                window.location.href = reqBashPath + 'html/commodity-list.html';
            }
        });
    });
    $addItem.click(function () {
        filterModal.reset();
    });
    $operate.click(function () {
        let row = $table.bootstrapTable('getSelections')[0];
        filterModal.initVal(row);
    });
    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
});

document.getElementById('smzdm-frame').onload = function () {
    $.post(reqBashPath + 'get-category').then(data => {
        let msg = {
            totalArr: data,
            type: 'init',
        };
        $('#smzdm-frame')[0].contentWindow.postMessage(msg, '*');
    });
};
window.addEventListener('message', function (e) {
    let data = e.data;
    if (data.type === 'redundant') {
        let arrTitle = '';
        for (let category of data.arrInfo) {
            arrTitle += category.title + ',';
        }
        sweetAlert("以下目录已失效:", arrTitle.slice(0, -1), "warning");
    } else if (data.type === 'selection') {
        $('#smzdm-frame').removeClass('on-show');
        if (data.matchFlag) {
            filterModal.categoryMatchArr = data.arr;
        } else {
            filterModal.categoryUnmatchArr = data.arr;
        }
    }
}, false);