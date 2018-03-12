<%--
  Created by IntelliJ IDEA.
  User: T5S
  Date: 17-12-17
  Time: 上午11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>demo</title>
    <link rel="shortcut icon" href="/library/cfda.ico">
    <%--<link href="/library/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
    <link href="/library/font_awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="/library/animate/animate.min.css" rel="stylesheet">
    <link href="/library/layui/css/layui.css" rel="stylesheet">

</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInUp" style="margin-left: 10px;">
    <div class="query-part" style="border: 1px solid #1f9fff; margin-top: 10px;padding: 5px;margin-right: 15px;">
        <form id="queryForm" class="layui-form layui-form-pane" action="">
            <div style="margin-top: 10px;">
                <div class="layui-form-item" style="width: 310px;float:left;clear: none;">
                    <label class="layui-form-label">股票名称</label>
                    <div class="layui-input-inline">
                        <input id="sharesName" type="text" name="sharesName" placeholder="请输入股票名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" style="width: 310px;float:left;clear: none;">
                    <label class="layui-form-label">周期</label>
                    <div class="layui-input-block" style="width:190px;">
                        <select id="frequency" name="frequency">
                            <option value="" selected=""></option>
                            <option value="1" >年</option>
                            <option value="2">季</option>
                            <option value="3">月</option>
                            <option value="4" >周</option>
                            <option value="5">日</option>
                            <option value="6">120分</option>
                            <option value="7" >60分</option>
                            <option value="8">30分</option>
                            <option value="9">15分</option>
                            <option value="10" >5分</option>
                            <option value="11">1分</option>
                        </select>
                    </div>
                </div>
                <br style="clear:both;"/>
            </div>
            <div style="text-align: right; padding-right: 50px;">
                <button id="queryBtn" class="layui-btn layui-btn-normal layui-btn-sm" type="button">查询</button>
                <button id="resetBtn" class="layui-btn layui-btn-normal layui-btn-sm" type="button">重置</button>
            </div>
        </form>
    </div>
    <div class="data-part">
        <div style="margin-top: 5px;">
            <div class="layui-btn-group">
               <%-- <button id="addBtn" class="layui-btn layui-btn-normal layui-btn-sm">增加</button>
                <button id="updateBtn" class="layui-btn layui-btn-normal layui-btn-sm">编辑</button>
                <button id="deleteBtn" class="layui-btn layui-btn-normal layui-btn-sm">删除</button>--%>
                <button id="impBtn" class="layui-btn layui-btn-normal layui-btn-sm">execl导入数据</button>
            </div>
        </div>
        <table class="layui-hide" id="dataTable"></table>
    </div>
</div>
<%--<script src="/library/layui/layui.js"></script>--%>
<script src="/library/layui/layui.all.js"></script>
<script src="/library/jquery/jquery.min.js"></script>
<script src="/common/common_layui.js"></script>
<script>
;!function(){
        var form = layui.form;
        var table = layui.table;
        var upload = layui.upload;
        var dataTableObj = table.render({
            id:'dataTable',
            elem: '#dataTable',
            height: 'full-200',
            method:'post',
            url:'/shares/getSharesHistoryDataList.do',
            cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {type:'checkbox'},
                /*{field:'id', width:80, title: 'ID', align:'center'},*/
                {field:'sharesName', width:100, title: '股票名称', align:'center'},
                {field:'frequency', width:100, title: '数据周期', align:'center'},
                {field:'startPrice', width:100, title: '开盘价', align:'center'},
                {field:'highPrice', width:110, title: '最高价', align:'center'},
                {field:'lowPrice', width:100, title: '最低价', align:'center'},
                {field:'endPrice', width:100, title: '收盘价', align:'center'},
                {field:'upVal', width:100, title: '涨幅', align:'center'},
                {field:'amplitude', width:100, title: '振幅', align:'center'},
                {field:'countNum', width:100, title: '总手', align:'center'},
                {field:'countMoney', width:100, title: '金额', align:'center'},
                {field:'changedHands', width:100, title: '换手', align:'center'},
                {field:'volamount', width:100, title: '成交次数', align:'center'},
                {field:'dataTime', width:200, title: '数据时间', align:'center',templet:'<div>{{ layui.laytpl.toDateString(d.dataTime,"yyyy-MM-dd") }}</div>'},
                {field:'createTime', width:200, title: '创建时间', align:'center',templet:'<div>{{ layui.laytpl.toDateString(d.createTime) }}</div>'}/*,
                {field:'updateTime', width:100, title: '修改时间', align:'center',templet: '#titleTpl'}*/
            ]],
            page: true,
            request: {
                pageName: 'page', //页码的参数名称，默认：page
                limitName: 'limit' //每页数据量的参数名，默认：limit
            },
            response: {
                statusName: 'resultCode', //数据状态的字段名称，默认：code
                statusCode: 0, //成功的状态码，默认：0
                msgName: 'msg', //状态信息的字段名称，默认：msg
                countName: 'count', //数据总数的字段名称，默认：count
                dataName: 'data', //数据列表的字段名称，默认：data
            }
        });
        /* 导入数据 */
        upload.render({
            elem: '#impBtn',
            url: '/shares/importSharesHistoryData.do',
            accept:'file',
            exts:'xls|xlsx',
            field:'file',
            done: function(res, index, upload){
                //获取当前触发上传的元素，一般用于 elem 绑定 class 的情况，注意：此乃 layui 2.1.0 新增
                var item = this.item;
            },
            error:function(index,upload){

            }
        })
//    });
    /*事件绑定*/
    $("#resetBtn").click(resetQueryForm);
    $("#queryBtn").click(queryQueryForm);
    //重置查询项
    function resetQueryForm(){
        $("#queryForm input[type='text']").val("");
        $("#queryForm select").val("");
    }
    //条件查询
    function queryQueryForm(){
        dataTableObj.reload({
            where: { //设定异步数据接口的额外参数，任意设
                sharesName:$("#sharesName").val(),
                rel:$("#rel").val(),
                frequency:$("#frequency option:selected").val(),
                state:$("#state option:selected").val()
            }
            ,page: {
             curr: 1 //重新从第 1 页开始
             }
        });
    }
    //添加菜单
    $('#addBtn').on('click', function(){
        layer.open({
            type: 2,
            title: '添加菜单',
            maxmin: true,
            shadeClose: false, //点击遮罩关闭层
            area : ['800px' , '520px'],
            content: '/menu/operatorMenuPage.do'
        });
    });
    //修改菜单
    $('#updateBtn').on('click', function(){
        var checkStatus = table.checkStatus('dataTable'); //test即为基础参数id对应的值
        if(checkStatus.data.length==0){
            alert("请选择需要改的数据");
            return false;
        }
        if(checkStatus.data.length > 1){
            alert("只能选择一条数据进行修改");
            return false;
        }
        console.log(checkStatus.data) //获取选中行的数据
        console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
        console.log(checkStatus.isAll ) //表格是否全选
        layer.open({
            type: 2,
            title: '修改菜单',
            maxmin: true,
            shadeClose: false, //点击遮罩关闭层
            area : ['800px' , '520px'],
            content: '/menu/operatorMenuPage.do?id='+checkStatus.data[0].id
        });
    });
    //删除菜单
     $("#deleteBtn").on('click', function(){
         var checkStatus = table.checkStatus('dataTable'); //test即为基础参数id对应的值
         if(checkStatus.data.length==0){
             alert("请选择需要删除的数据");
             return false;
         }
         //得到要删除的数据的id
         var ids = "";
         for(var i=0;i<checkStatus.data.length;i++){
             ids += checkStatus.data[i].id+",";
         }
         ids = ids.substr(0,ids.length-1);
         layer.msg('确定要删除此数据？', {
             shade:0.3,
             time: 0 //不自动关闭
             ,btn: ['确定', '取消']
             ,yes: function(index){
                 layer.close(index);
                 $.ajax({
                     url:"/menu/deleteMenuPage.do",
                     type:"POST",
                     async:false,
                     data:{menuIds:ids},
                     dataType:"json",
                     success:function(data){
                         reloadDataTable();
                     }
                 });
             }
         });
     });
    //刷新数据表格
    window.reloadDataTable = function reloadDataTable(){
           $("#queryBtn").click();
    }
}();
</script>
</body>
</html>