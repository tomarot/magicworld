<%--
  Created by IntelliJ IDEA.
  User: T5S
  Date: 2018/2/19
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInUp" style="margin-left: 10px;">
    <div id="container" style="min-width:400px;height:600px"></div>
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
    });
//页面初始化
function init(){
    $.getJSON('/shares/KChartShow.do?page=0&limit=50', function (data) {
        if(data.code !== 200) {
            alert('读取股票数据失败！');
            return false;
        }
        data = data.dataList;
        var ohlc = [],
            volume = [],
            dataLength = data.length,
            // set the allowed units for data grouping
            groupingUnits = [[
                'week',                         // unit name
                [1]                             // allowed multiples
            ], [
                'month',
                [1, 2, 3, 4, 6]
            ]],
            i = 0;
        for (i; i < dataLength; i += 1) {
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
            /*tooltip: {
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
            },*/
            plotOptions: {
                series: {
                    showInLegend: true
                }
            },
            tooltip: {
                split: false,
                shared: true
                // backgroundColor: null
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
    });
}

</script>
</body>
</html>
