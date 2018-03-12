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
    <form id="dateForm" class="layui-form layui-form-pane" action="/dictionary/operatorDictionary.do">
        <input type="hidden" name="id" value="${dictionary.id}">
        <div class="layui-form-item">
            <label class="layui-form-label">字典项名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" placeholder="请输入字典项名称" class="layui-input" value="${dictionary.name}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">字典项编号</label>
            <div class="layui-input-block">
                <input type="text" name="code" placeholder="请输入字典项编号" class="layui-input" value="${dictionary.code}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">字典项值</label>
            <div class="layui-input-block">
                <input type="text" name="value" placeholder="请输入字典项值" class="layui-input" value="${dictionary.value}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">顺序号</label>
            <div class="layui-input-block">
                <input type="text" name="ordernum" placeholder="请输入顺序号" class="layui-input" value="${dictionary.ordernum}">
            </div>
        </div>
        <div class="layui-form-item" pane="">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input id="state" type="checkbox" name="state" lay-skin="switch" title="开关" lay-text="启用|禁用" checked="checked" value="1">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea name="remark" placeholder="请输入备注" class="layui-textarea">${dictionary.remark}</textarea>
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <button id="submitBtn" class="layui-btn" type="button" lay-filter="demo2">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>
</div>

<%--<script src="/library/layui/layui.js"></script>--%>
<script src="/library/layui/layui.all.js"></script>
<script src="/library/jquery/jquery.min.js"></script>
<script>
;!function(){
    var form = layui.form;
    //$("#state").removeAttr('checked');
    //form.render();
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
                    parent.reloadDataTable();
                    //当你在iframe页面关闭自身时
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                }else{

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