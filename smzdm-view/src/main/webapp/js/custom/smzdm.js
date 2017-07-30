// ==UserScript==
// @name         什么值得买
// @namespace    http://45.77.22.33:1212/
// @version      0.1
// @description  给smzdm定制的js脚本
// @author       You
// @match        http://www.smzdm.com/fenlei/
// @grant        none
// ==/UserScript==
let appendStyle = `
<style>
    .indexList a.on-select {
        color: #FF6347!important
    }
    .indexList a.no-select {
        color: #DC143C!important;
        font-weight: 600
    }
    .btn {
        padding: 5px 10px;
        font-size: 12px;
        line-height: 1.5;
        border-radius: 3px;
        color: #333;
        text-align: center;
        white-space: nowrap;
        vertical-align: middle;
        cursor: pointer;
        margin-left: 5px;
    }
    .btn-event {
        border: #46b8da 1px solid;
        background-color: #5bc0de;
        display: inline-block;
        margin-top: 3px
    }
    .btn-event:active {
        background-color: #269abc;
        border-color: #1b6d85;
    }
    .btn-confirm {
        font-size: 13px;
        float: right;
        margin-right: 5px;
        background-color: #d9534f;
        border-color: #d43f3a;
        position: absolute;
        bottom: 4px;
        right: 5px;
    }
    .btn-confirm:active {
        background-color: #c9302c;
        border-color: #ac2925;
    }
    .select-info {
        position: fixed;
        bottom: 60px;
        width: 500px;
        height: 100px;
        margin: auto;
        left: 0;
        right: 0;
        border: #174FA9 solid 2px;
        border-radius: 9px;
        background-color: rgba(148, 180, 230, 0.5)
    }
    .select-info-title {
        margin: 10px 5px 0;
        font-size: 16px;
    }
</style>`;
let appendDom = `
<div class="select-info">
    <div class="select-info-title">已经选:</div>
    <div id='show-select'></div>
    <span class="btn btn-confirm" id="send-select-btn">确定</span>
</div>`;
(function () {
    'use strict';
    $('.xiTop').remove();
    $('head').append(appendStyle);
    $('body').append(appendDom);
    $('#send-select-btn').click(function () {
        sendToParent(selectArr, 'selection');
    });
    // 被选中的数组
    let selectArr = [], parentURL = '', matchFlag;
    //事件监听
    window.addEventListener('message', function (e) {
        if (e.data.type === 'init') {
            parentURL = e.data.reqBashPath;
            initList(e.data.totalArr);
        } else if (e.data.type === 'reset') {
            matchFlag = e.data.matchFlag;
            resetSelect(e.data.selectArr);
        }
    }, false);

    //初始化列表情况
    function initList(arr) {
        $('.indexList a').each(function () {
            let dom = this;
            let arrIndex = getArrIndex(arr, $(dom).prop('href', 'javascript:void(0)').text());
            if (typeof (arrIndex) !== "undefined") {
                dom.dataset.categoryId = arr[arrIndex].id;
                $(dom).click(toggleSelect).addClass('category-selection');
                arr.splice(arrIndex, 1);
            } else {
                $(dom).addClass('no-select');
            }
        });
        sendToParent(arr, 'redundant');
    }

    //重新赋值
    function resetSelect(arr) {
        let domStr = '';
        selectArr = [];
        $('.indexList .category-selection').removeClass('on-select').each(function () {
            let dom = this;
            let arrIndex = getArrIndex(arr, $(dom).text());
            if (typeof (arrIndex) !== "undefined") {
                domStr += `<span class="btn btn-event" data-category-id="${arr[arrIndex].id}" onclick="consoleBtn()">${arr[arrIndex].title}</span>`;
                //保证顺序一致
                selectArr.push(arr[arrIndex]);
                $(dom).addClass('on-select');
                arr.splice(arrIndex, 1);
            }
        });
        sendToParent(arr, 'redundant');
        $('#show-select').empty().append(domStr);
    }


    //根据dom取值
    function getValue(dom) {
        return {
            title: $(dom).text(),
            id: $(dom).data('category-id')
        };
    }

    //切换选中状态
    function toggleSelect() {
        let dom = this;
        let category = getValue(dom);
        if ($(dom).hasClass('on-select')) {
            $(dom).removeClass('on-select');
            let arrIndex = getArrIndex(selectArr, category.title);
            if (typeof (arrIndex) !== "undefined") {
                selectArr.splice(arrIndex, 1);
                $('#show-select').find('span').eq(arrIndex).remove();
            }
        } else {
            $(dom).addClass('on-select');
            selectArr.push(category);
            $('#show-select').append(`<span class="btn btn-event" data-category-id="${category.id}" onclick="consoleBtn()">${category.title}</span>`);
        }
    }

    //得到数组下标
    function getArrIndex(arr, title) {
        let arrIndex;
        $.each(arr, function (index, obj) {
            if (obj.title === title) {
                arrIndex = index;
                return false;
            }
        });
        return arrIndex;
    }

    //发送消息到父组件
    function sendToParent(arr, type) {
        let msg = {
            type: type
        };
        if (type === 'redundant') {
            if (arr.length !== 0) {
                msg.arrInfo = arr;
            } else {
                return;
            }
        } else {
            msg.arr = arr;
            msg.matchFlag = matchFlag;
        }
        window.parent.postMessage(msg, '*');
    }
})();