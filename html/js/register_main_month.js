//用于显示走势图的js
var status="freight"
var trendname="货运走势图"
var year;		//存年份
var monthdata;		//存数据
var unit;		//单位
var airportname="所有"

$(function () {
	login();
	img();
	freighttrend();
	passengertrend();
})



//点击搜索
$("#select").on("click", function () {
	console.log("ok")
	airportname=$('#airportname').val()
	// start=$('#start').val()
	// end=$('#end').val()
	if(airportname==""){
		airportname="所有"
	}
	// if(start=="" && end==""){
	// 	start="1"
	// 	end="12"
	// }
	$(".selectair").html("");
	var txt = "";
	txt += `显示 ${airportname} 机场 1 - 12 月数据(07-20年数据)`
	$(".selectair").append(txt);
	freighttrend();
	passengertrend();
})


//查询货运机场
function freighttrend() {
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/airtrend/freightbymonth",
		//请求参数
		data: {
			airportname:$('#airportname').val(),
			start:$('#start').val(),
			end:$('#end').val()
		},
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
			status="freight"
			trendname="货运统计图"
			unit="吨";
			year=data.year;
			monthdata=data.sum;
			data_ana()
			echarts_trend();
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}


//查询客运机场分布
function passengertrend() {
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/airtrend/passengerbymonth",
		//请求参数
		data: {
			airportname:$('#airportname').val(),
			start:$('#start').val(),
			end:$('#end').val()
		},
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
			status="passenger"
			trendname="客运统计图"
			unit="人次";
			year=data.year;
			monthdata=data.sum;
			data_ana()
			echarts_trend()
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}

//文字分析
function data_ana(){
	var max=monthdata[0];
	var min=monthdata[0];
	var maxmonth=year[0];
	var minmonth=year[0];
	for(var i=0;i<monthdata.length;i++){
		if(max<monthdata[i]){
			max=monthdata[i];
			maxmonth=year[i];
		}
		if(min>monthdata[i]){
			min=monthdata[i]
			minmonth=year[i];
		}
	}
	if(status=="freight"){
	$("#freightfx").html("");
	var txt = "";
		txt += `
		表示07年到19年俄罗斯所有机场${trendname}的变化，其中货运量最多的是${maxmonth}月，为${max} ${unit}，最少的是${minmonth}月，为${min} ${unit}`
	$("#freightfx").append(txt);}
	else {
		$("#passengerfx").html("");
		var txt = "";
			txt += `
			表示07年到19年俄罗斯所有机场${trendname}的变化，其中客运量最多的是${maxmonth}月，为${max} ${unit}，最少的是${minmonth}月，为${min} ${unit}`
		$("#passengerfx").append(txt);
	}
}




function echarts_trend(){
	var container = echarts.init(document.getElementById(status));
var data = genData(50);

option = {
    title: {
        text: trendname,
        subtext:'单位：'+ unit,
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
	},
	toolbox: {
        show: true,
        feature: {
            dataView: {readOnly: false},
            saveAsImage: {}
        }
    },
    legend: {
        type: 'scroll',
        orient: 'vertical',
        right: 10,
        top: 20,
        bottom: 20,
        data: data.legendData,

        selected: data.selected
    },
    series: [
        {
            name: '月份',
            type: 'pie',
            radius: '55%',
            center: ['40%', '50%'],
            data: data.seriesData,
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
container.setOption(option,true);	


function genData(count) {
    var legendData = [];
    var seriesData = [];
    var selected = {};
    for (var i = 0; i < monthdata.length; i++) {
        legendData.push(year[i]);
        seriesData.push({
            name: year[i],
            value: monthdata[i]
        });
        // selected[month[i]] = 1;
    }

    return {
        legendData: legendData,
        seriesData: seriesData,
        // selected: selected
	};
	
}

}




//显示头像
function img() {
	var userid = sessionStorage.getItem("id");
	var username = sessionStorage.getItem("name");
	$(".user").html("");
	var txt = "";
	txt += `<img src="/apiz/registerheadImg?id=${userid}" onerror="defaultImg(this)" style="
    width: 50px;
    height: 50px;
    border-radius: 50px;">${username}`
	$(".user").append(txt);
}
function defaultImg(img) {
	var id = sessionStorage.getItem("id")
	img.src = "/images/default_user.png";
}

//登录判断
function login() {
	var status = sessionStorage.getItem("status");
	if (status != 1) {
		alert("请先登录");
		sessionStorage.clear();
		location.href = "register_login.html";
	}
}

//注意：导航 依赖 element 模块，否则无法进行功能性操作	
layui.use('element', function () {
	var element = layui.element;

	//…
});
