/**
 * Created by changdy on 2017/7/9.
 */
$(document).ready(function () {
    let modal = new Vue({
        el: '#filter-info',
        data: {
            id: -1,
            name: '',
            titleUnmatch: '',
            titleMatch: '',
            categoryMatchArr: [],
            categoryUnmatchArr: [],
            ratingCount: '',
            worthPercent: ''
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
                let idArr = obj.ids.split(',');
                let titleArr = obj.titles.split(',');
                let category = [];
                if (idArr.length === titleArr.length) {
                    idArr.forEach(v => category.push({id: v}));
                    titleArr.forEach((v, i) => category[i].title = v);
                } else {
                    sweetAlert("id和title长度不一", '', "warning");
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
                this.ratingCount = '';
                this.worthPercent = '';
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
        $table.bootstrapTable('remove', {
            field: 'id',
            values: getIdSelections()
        });
        $remove.prop('disabled', true);
        $operate.prop('disabled', true);
    });
    $addItem.click(function () {
        modal.reset();
    });
    $operate.click(function () {
        let row = $table.bootstrapTable('getSelections')[0];
        modal.initVal(row);
    });
    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
});
document.getElementById('smzdm-frame').onload = function () {
    let msg = {
        totalArr: [{
            title: '手机通讯',
            id: 1
        }, {
            title: '智能手机',
            id: 2
        }, {
            title: '运营商',
            id: 3
        }, {
            title: '相机',
            id: 4
        }],
        type: 'init',
    };
    $('#smzdm-frame')[0].contentWindow.postMessage(msg, '*');
};

window.addEventListener('message', function (e) {
    if (e.data.type === 'redundant') {
        sweetAlert("以下目录已失效:", e.data.arrInfo, "warning");
    } else {

    }
}, false);