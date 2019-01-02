<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>多柱图</title>
    <script src="<%=basePath%>plugins_sunny/echarts/echarts.min.js"></script>

</head>
<body>



<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 800px;height:400px;"></div>

</body>

<script type="text/javascript">
    initData();
    //生成数据
    function initData() {
        var legendData = ['甲', '乙','丙'];
        var bgColorList = ['#FBB730', '#31BDF2','#6197fb'];
        var axisLabel = ['化学', '数学', '地理', '物理', '英语', '音乐', '语文', '历史', '美术', '生物', '信息技术', '政治', '体育'];
        var seriesValue = [];

        for (var i = 0; i < legendData.length; i++) {
            var arrData = [];
            var seriesDataVal = null;
            for (var j = 0; j < axisLabel.length; j++) {
                arrData.push(Math.floor(Math.random() * 100));
            }
            seriesDataVal = {
                barWidth: 10,//柱状条宽度
                name:legendData[i],
                type:'bar',
                itemStyle: {
                    normal: {
                        show: true,//鼠标悬停时显示label数据
                        barBorderRadius: [10, 10, 10, 10],//柱形图圆角，初始化效果
                        color: bgColorList[i]
                    }
                },
                label: {
                    normal: {
                        show: true, //显示数据
                        position: 'top'//显示数据位置 'top/right/left/insideLeft/insideRight/insideTop/insideBottom'
                    }
                } ,
                data:arrData
            };
            seriesValue.push(seriesDataVal);
        }

        buildChart(legendData, axisLabel, seriesValue);

    }


    //生成Echarts图形
    function buildChart(legendData, axisLabel, seriesValue) {
        var chart = document.getElementById('main');
        var echart = echarts.init(chart);
        var option = {
            title: {
                text: "学科兴趣倾向分析",//正标题
                x: "center", //标题水平方向位置
                y: "0", //标题竖直方向位置
                textStyle: {
                    fontSize: 18,
                    fontWeight: 'normal',
                    color: '#666'
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'//阴影，若需要为直线，则值为'line'
                }
            },
            toolbox: {
                show: true,
                feature: {
                    saveAsImage: {show: true}
                }
            },
            legend: {
                data: legendData,
                y: 'bottom'//图例说明文字设置

            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '10%',
                top:'10%',
                containLabel: true
            },
            xAxis: [{
                min: 0,
                type: 'category', //纵向柱状图，若需要为横向，则此处值为'value'， 下面 yAxis 的type值为'category'
                data: axisLabel
            }],
            yAxis: [{
                min: 0,
                type: 'value',
                splitArea: {show: false}
            }],
            label: {
                normal: { //显示bar数据
                    show: true,
                    position: 'top'
                }
            },
            series: seriesValue
        };

        echart.setOption(option);
    }


</script>

</html>
