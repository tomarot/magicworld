<%--
  Created by IntelliJ IDEA.
  User: T5S
  Date: 2018/2/19
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    <%--引入jstl表达式--%>
<%@ page isELIgnored="false" %>    <%--是否启用EL表达式--%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>kchart</title>
    <link rel="shortcut icon" href="/library/cfda.ico">
    <%--<link href="/library/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
    <link href="/library/font_awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="/library/animate/animate.min.css" rel="stylesheet">
    <link href="/library/layui/css/layui.css" rel="stylesheet">
    <style type="text/css">
        h2.accountinfo{
            text-align: center;
            margin: 10px 0px;
            background-color: #e5e9ea;
        }
        div.accountinfo{
            margin: 5px 0px;
            padding: 0px 10px;
        }
        .left{
            text-align: left;
        }
        .right{
            text-align: right;
        }
    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInUp" style="margin-left: 10px;">
    <div class="layui-row">
        <div class="layui-col-xs9" style="height:100%;background-color: white;">
            <div>
                <div>
                    <div id="container" style="min-width:400px;height:500px"></div>
                </div>
                <div>
                    <div style="height:180px;">
                        <form class="layui-form layui-form-pane">
                            <input id="gamecode" type="hidden" value="${gameCode}">
                            <input id="accountid" type="hidden" value="${accountid}">
                            <div class="layui-form-item" pane="">
                                <label class="layui-form-label">操作</label>
                                <div class="layui-input-block">
                                    <%--<button id="selectLowBtn" class="layui-btn layui-btn-sm" style="margin-top: 3px;margin-left: 10px;" type="button">选为低点</button>
                                    <button id="selectHighBtn" class="layui-btn layui-btn-sm" style="margin-top: 3px;margin-left: 5px;" type="button">选为高点</button>--%>
                                    <button id="nextBtn" class="layui-btn layui-btn-sm" style="margin-top: 3px;margin-left: 5px;" type="button">下一天</button>
                                    <button id="dealBtn" class="layui-btn layui-btn-sm" style="margin-top: 3px;margin-left: 5px;" type="button">交易</button>
                                </div>
                            </div>
                            <div style="margin-top: 10px;">

                            </div>
                            <div class="layui-form-item" pane="">
                                <label class="layui-form-label">提前报价</label>
                                <div class="layui-input-block">
                                    <input type="checkbox" checked="" name="open" lay-skin="switch" lay-filter="switchTest" title="开关">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-xs3" style="height:100%;background-color: white;">
            <div>
                <%--账户信息--%>
                <div style="height:160px">
                    <div class="layui-row">
                        <div class="layui-col-xs12">
                            <h2 class="accountinfo">账户信息</h2>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <div class="accountinfo right"><label>账户信息:</label></div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="accountinfo left"><label id="accountname">?</label></div>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <div class="accountinfo right"><label>初始资金:</label></div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="accountinfo left"><label id="initmoney">?</label></div>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <div class="accountinfo right"><label>可用资金:</label></div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="accountinfo left"><label id="availablemoney">?</label></div>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <div class="accountinfo right"><label>市值:</label></div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="accountinfo left"><label id="marketvalue">??</label></div>
                        </div>
                    </div>
                </div>
                <%--当前股价--%>
                <div style="height:160px;">
                    <div class="layui-row">
                        <div class="layui-col-xs12">
                            <h2 class="accountinfo">当前股价</h2>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <div class="accountinfo right"><label>时间:</label></div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="accountinfo left"><label id="datatime" >?</label></div>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <div class="accountinfo right"><label>开盘:</label></div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="accountinfo left"><label id="startprice">?</label></div>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <div class="accountinfo right"><label>最高:</label></div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="accountinfo left"><label id="highprice">?</label></div>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <div class="accountinfo right"><label>最低:</label></div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="accountinfo left"><label id="lowprice">?</label></div>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <div class="accountinfo right"><label>收盘:</label></div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="accountinfo left"><label id="endprice">?</label></div>
                        </div>
                    </div>
                </div>
                <%--持仓信息--%>
                <div style="height:170px;">
                    <div class="layui-row">
                        <div class="layui-col-xs12">
                            <h2 class="accountinfo">持仓信息</h2>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <div class="accountinfo right"><label>数量:</label></div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="accountinfo left"><label id="warehouseNum">?</label></div>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <div class="accountinfo right"><label>盈亏:</label></div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="accountinfo left"><label id="exhibit">?</label></div>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs6">
                            <div class="accountinfo right"><label>成本/现价:</label></div>
                        </div>
                        <div class="layui-col-xs6">
                            <div class="accountinfo left"><label id="cost">?</label></div>
                        </div>
                    </div>
                </div>
                <%--报价信息--%>
                <div style="height:150px;">
                    <div class="layui-row">
                        <div class="layui-col-xs12">
                            <h2 class="accountinfo">当前报价信息</h2>
                        </div>
                    </div>
                    <div class="layui-row">
                        <div class="layui-col-xs3">
                            <div class="accountinfo right"><label>单据类型:</label></div>
                        </div>
                        <div class="layui-col-xs3">
                            <div class="accountinfo left"><label>数量:</label></div>
                        </div>
                        <div class="layui-col-xs3">
                            <div class="accountinfo left"><label>报价:</label></div>
                        </div>
                        <div class="layui-col-xs3">
                            <div class="accountinfo left"><label>操作:</label></div>
                        </div>
                    </div>
                    <div class="layui-row">
                        <input id="offerbillid" type="hidden" value="">
                        <div class="layui-col-xs3">
                            <div class="accountinfo right"><label id="quotationbilltype">?买入</label></div>
                        </div>
                        <div class="layui-col-xs3">
                            <div class="accountinfo left"><label id="offerbillNum">?</label></div>
                        </div>
                        <div class="layui-col-xs3">
                            <div class="accountinfo left"><label id="offerbillPrice">?</label></div>
                        </div>
                        <div class="layui-col-xs3">
                            <div class="accountinfo left"><a id="cancelBtn" class="layui-btn layui-btn-xs">X</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/library/layui/layui.all.js"></script>
<script src="/library/jquery/jquery.min.js"></script>
<script src="/library/highcharts/code/highstock.js"></script>
<script type="text/javascript">
    $(function(){
        Highcharts.setOptions({
            lang: {
                rangeSelectorZoom: '分类',
                resetZoom:'重置',
                rangeSelectorFrom: '从',
                rangeSelectorTo: '到',
                months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                shortMonths: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
                weekdays: ['周日', '周一', '周二', '周三', '周四', '周五', '周六']
            },
            global: {
                useUTC: false
            }
        });
        init();
        initBind();
    });
//事件绑定
function initBind(){
    //选为低点
    $("#selectLowBtn").click(function(){
        layer.msg('选为低点', {
            time: 3000, //3s后自动关闭
            btn: ['知道了']
        });
    });
    //选为高点
    $("#selectHighBtn").click(function(){
        layer.msg('选为高点', {
            time: 3000, //3s后自动关闭
            btn: ['知道了']
        });
    });
    //下一天
    $("#nextBtn").click(function(){
        //加载数据请求
        getData('y');
    });
    //交易
    $("#dealBtn").click(function(){
        var gameCode = $("#gamecode").val();
        var accountid = $("#accountid").val();
        layer.open({
            type: 2,
            title: '添加报价单',
            maxmin: true,
            shadeClose: false, //点击遮罩关闭层
            area : ['800px' , '520px'],
            content: '/shares/toOperatorOfferbillPage.do?gameCode='+gameCode+'&accountid='+accountid
        });
    });
    //取消报价单
    $("#cancelBtn").click(function(){
        var offerbillid = $("#offerbillid").val();
        layer.msg('确定要取消报价单吗？', {
            shade:0.3,
            time: 0, //不自动关闭
            btn: ['确定', '取消'],
            yes: function(index){
                layer.close(index);
                $.getJSON('/shares/calcelOfferbill.do?offerbillid='+offerbillid, function (data) {
                    if(data.state !== 200) {
                        alert('读取数据失败！');
                        return false;
                    }
                    window.getData('n')
                });
            }
        });
    });
}
//页面初始化
function init(){
    //加载数据请求
    getData('n');
}

window.getData = function getData(flag){
    var gameCode = $("#gamecode").val();
    $.getJSON('/shares/getGameInfoData.do?gameCode='+gameCode+'&next='+flag, function (data) {
        if(data.state !== 200) {
            alert('读取数据失败！');
            return false;
        }
        var chartData = data.data.kChartData;//K线数据
        createKChart(chartData);
        var currentKData = data.data.currentKData;//最新数据
        buildCurrentKData(currentKData);
        var sharesAccountInfo = data.data.sharesAccountInfo;//账户信息
        buildAccountInfo(sharesAccountInfo);
        var sharesWarehouseInfo = data.data.sharesWarehouseInfo;//持仓信息
        buildWarehouseInfo(sharesWarehouseInfo,currentKData);
        var sharesOfferBillInfo = data.data.sharesOfferBillInfo;//当前报价单信息
        buildOfferBillInfo(sharesOfferBillInfo);
    });
}
//渲染最新数据
function buildCurrentKData(data){
    $("#datatime").text(data.dataTime);//时间
    $("#startprice").text(data.startPrice);//开盘
    $("#highprice").text(data.highPrice);//最高
    $("#lowprice").text(data.lowPrice);//最低
    $("#endprice").text(data.endPrice);//收盘
}
//渲染账户信息
function buildAccountInfo(data){
    if(!data){
        return false;
    }
    $("#accountname").text(data.accountname);
    $("#initmoney").text(data.initmoney);
    $("#availablemoney").text(data.availablemoney);
    $("#marketvalue").text();
}
//渲染持仓信息
function buildWarehouseInfo(data,currentKData){
    if(!data){
        var no = "";
        $("#warehouseNum").text(no);//数量
        $("#exhibit").text(no);//盈亏
        $("#cost").text(no);//成本/现价
        return false;
    }
    $("#warehouseNum").text(data.num);//数量
    var exhibit = (currentKData.endPrice - data.cost) * data.num;
    $("#exhibit").text(exhibit);//盈亏
    $("#cost").text(data.cost+"/"+currentKData.endPrice);//成本/现价
}
//渲染报价单信息
function buildOfferBillInfo(data){
    if(!data){
        var no = "";
        $("#quotationbilltype").text(no);//操作类型
        $("#offerbillNum").text(no);//数量
        $("#offerbillPrice").text(no);//价格
        $("#cancelBtn").hide();
        return false;
    }
    $("#quotationbilltype").text(data.quotationbilltype==0?"买入":"卖出");//操作类型
    $("#offerbillNum").text(data.num);//数量
    $("#offerbillPrice").text(data.price);//价格
    $("#offerbillid").val(data.id);//报价单主键
    $("#cancelBtn").show();
}
//创建K线图
function createKChart(data){
    data = data.dataList;
    var ohlc = [];
    var volume = [];
    var dataLength = data.length;
    // set the allowed units for data grouping
    var groupingUnits = [[
        'week',                         // unit name
        [1]                             // allowed multiples
    ], [
        'month',
        [1, 2, 3, 4, 6]
    ]];
    for (var i=0; i < dataLength; i += 1) {
        ohlc.push([
            data[i]['dataTime'], // the date
            data[i]['startPrice'], // open
            data[i]['highPrice'], // high
            data[i]['lowPrice'], // low
            data[i]['endPrice'] // close
        ]);
        volume.push([
            data[i]['dataTime'], // the date
            data[i]['volamount'] // the volume
        ]);
    }
    // create the chart
    $('#container').highcharts('StockChart', {
        rangeSelector: {
            selected: 0,// 默认选中的范围，值为上面 buttons 数组的下标（从 0 开始）
            inputDateFormat: '%Y-%m-%d',
            buttons: [/*{ // 时间范围按钮数组
                        type: 'day',
                        count: 1,
                        text: '1天'
                    }, {
                        type: 'day',
                        count: 3,
                        text: '3天'
                    }, {
                        type: 'day',
                        count: 6,
                        text: '6天'
                    }, {
                        type: 'month',
                        count: 1,
                        text: '1月'
                    }, {
                        type: 'year',
                        count: 1,
                        text: '1年'
                    },*/ {
                type: 'all',
                text: '全部'
            }],
        },
        title: {
            text: 'XXXX历史股价'
        },
        xAxis: {
            dateTimeLabelFormats: {
                millisecond: '%H:%M:%S.%L',
                second: '%H:%M:%S',
                minute: '%H:%M',
                hour: '%H:%M',
                day: '%m-%d',
                week: '%m-%d',
                month: '%y-%m',
                year: '%Y'
            }
        },
        tooltip: {
            split: false,
            xDateFormat: '%Y-%m-%d',
            shared: true,
            crosshairs: [{//十字准星
                width: 1,
                color: 'green'
            }, {
                width: 1,
                color: 'green'
            }],
            positioner: function () {//提示框位置
                return { x: 60, y: 30 };
            },
        },
        plotOptions: {
            series: {
                showInLegend: true
            }
        },
        /* plotOptions: {
             ohlc: {
                 tooltip: {
                     pointFormat: '<span style="color:{point.color}">\u25CF</span> <b> {series.name}</b><br/>' +
                     '开盘: {point.open}<br/>' +
                     '最高: {point.high}<br/>' +
                     '最低: {point.low}<br/>' +
                     '收盘: {point.close}<br/>'
                 }
             }
         },*/
        yAxis: [{
            labels: {
                align: 'right',
                x: -3
            },
            title: {
                text: '股价'
            },
            height: '65%',
            resize: {
                enabled: true
            },
            lineWidth: 2
        }, {
            labels: {
                align: 'right',
                x: -3
            },
            title: {
                text: '成交量'
            },
            top: '65%',
            height: '35%',
            offset: 0,
            lineWidth: 2
        }],
        series: [{
            type: 'candlestick',
            name: '平安银行',
            color: 'green',
            lineColor: 'green',
            upColor: 'red',
            upLineColor: 'red',
            tooltip: {
            },
            navigatorOptions: {
                color: Highcharts.getOptions().colors[0]
            },
            data: ohlc,
            dataGrouping: {
                units: groupingUnits
            },
            id: 'sz'
        },{
            type: 'column',
            data: volume,
            yAxis: 1,
            dataGrouping: {
                units: groupingUnits
            }
        }]
    });
}

</script>
</body>
</html>
