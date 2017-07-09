/**
 * Created by changdy on 2017/7/9.
 */
$(document).ready(function () {
    let $table = $('#filter-table');
    $table.bootstrapTable({
        url: reqBashPath + 'query-filter',
        dataType: "json",
        striped: true,                      //是否显示行间隔色
        singleSelect: true,
        search: true,
        detailView: true,//显示细节
        pagination: true, //分页
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 25,                       //每页的记录行数（*）
        pageList: [25, 50, 100],        //可供选择的每页的行数（*）
        showColumns: true,
        sortName: 'id',
        sortOrder: 'desc',
        sidePagination: "server", //服务端处理分页
        columns: [{
            field: 'id',
            align: 'center',
            valign: 'middle',
            sortable: true,
            title: '序号',
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
            title: '点赞百分比',
        }]
    });
});