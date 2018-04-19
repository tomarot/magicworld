<%--
  Created by IntelliJ IDEA.
  User: T5S
  Date: 17-12-26
  Time: 上午11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
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
<div style="padding: 0px 50px;padding-top: 20px;">
    <form id="dateForm" class="layui-form layui-form-pane" action="/shares/saveOfferbill.do">
        <input type="hidden" name="accountid" value="${accountid}">
        <div class="layui-form-item">
            <label class="layui-form-label">操作类型</label>
            <div class="layui-input-block">
                <select id="level" name="quotationbilltype">
                    <option value="" selected="">请选择</option>
                    <option value="0" >买入</option>
                    <option value="1">卖出</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">数量</label>
            <div class="layui-input-block">
                <input type="text" name="num" placeholder="请输入数量" class="layui-input" value="">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">价格</label>
            <div class="layui-input-block">
                <input type="text" name="price" placeholder="请输入价格" class="layui-input" value="">
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <button id="submitBtn" class="layui-btn" type="button" lay-filter="demo2">确定</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>
</div>

<%--<script src="/library/layui/layui.js"></script>--%>
<script src="/library/layui/layui.all.js"></script>
<script src="/library/jquery/jquery.min.js"></script>
<script>
;!function(){
    $("#submitBtn").click(function(){
        var data = $("#dateForm").serialize();
        var url = $("#dateForm").attr("action");
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            data: data,
            success: function (result) {
                if(result.state == '200'){
                    window.parent.getData('n');
                    //当你在iframe页面关闭自身时
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                }else{
                    alert(result.message);
                }
            },
            error: function(data) {
            }
        });

    });
}();
</script>
</body>
</html>