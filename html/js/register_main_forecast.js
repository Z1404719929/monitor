//用于显示走势图的js
var status="freight"
var trendname="货运走势图"
var month = new Array();		//存年份
var monthdata = new Array();		//存数据
var unit;		//单位

$(function () {
	login();
	img();
	freighttrend();
	passengertrend();
	data_ana()
})



//点击搜索
$("#select").on("click", function () {
	console.log("ok")
	airportname=$('#airportname').val()
	start=$('#start').val()
	end=$('#end').val()
	if(airportname==""){
		airportname="所有"
	}
	if(start=="" && end==""){
		start="1"
		end="12"
	}
	$(".selectair").html("");
	var txt = "";
	txt += `预测 ${airportname} 机场 2020 年数据`
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
		url: "/apiz/forecast/freight_forecast",
		//请求参数
		data: {
			airportname:$('#airportname').val(),
		},
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
			status="freight"
			trendname="货运走势图"
			unit="吨";
			month=data.month;
			monthdata=data.sum;
			echarts_trend();
			data_ana()
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
		url: "/apiz/forecast/passenger_forecast",
		//请求参数
		data: {
			airportname:$('#airportname').val(),
		},
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
			status="passenger"
			trendname="客运走势图"
			unit="人次";
			month=data.month;
			monthdata=data.sum;
			echarts_trend()
			data_ana()
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}



function echarts_trend(){
	var container = echarts.init(document.getElementById(status));
	option = {
		title: {
			text: trendname,
		},
		xAxis: {
			type: 'category',
			data: month,
			name: '月',
		},
		yAxis: {
			type: 'value',
			name: unit,
		},
		toolbox: {
			show: true,
			feature: {
				dataZoom: {
					yAxisIndex: 'none'
				},
				dataView: {readOnly: false},
				magicType: {type: ['line', 'bar']},
				restore: {},
				saveAsImage: {}
			}
		},
		tooltip: {
			trigger: 'axis'
		},
		series: [{
			data: monthdata,
			type: 'line',
			showBackground: true,
			backgroundStyle: {
				color: 'rgba(220, 220, 220, 0.8)'
			},
			itemStyle: {
				normal:{
					color:'#61a0a8'
				}
			}
		}]
	};
	container.setOption(option,true);	
}




//文字分析
function data_ana(){
	var max=monthdata[0];
	var min=monthdata[0];
	var maxyear=month[0];
	var minyear=month[0];
	var dif = new Array()	//差
	var difx,difn;	//存差最大最小值
	var yearstart = new Array();
	var yearend = new Array();
	for(var i=0;i<monthdata.length;i++){
		if(max<monthdata[i]){
			max=monthdata[i];
			maxyear=month[i];
		}
		if(min>monthdata[i]){
			min=monthdata[i]
			minyear=month[i];
		}
		//差的值
		if(i<monthdata.length-1){
			dif[i]=monthdata[i+1]-monthdata[i]
			yearstart[i]=month[i]
			yearend[i]=month[i+1]
		}
	}
	//差的最大最小值
	difx=dif[0]
	difn=dif[0]
	for(var i=0;i<dif.length;i++){
		if(difx<dif[i]){
			difx=dif[i]
			difss=yearstart[i]
			difse=yearend[i]
		}
		if(difn>dif[i]){
			difn=dif[i]
			difns=yearstart[i]
			difne=yearend[i]
		}
	}

	if(status=="freight"){
	$("#freightfx").html("");
	var txt = "";
		txt += `
		预测2020年货运走势图，其中运量最多的是${maxyear}月，为${max} ${unit}，最少的是${minyear}月，为${min} ${unit} <br/>`
		if(difx>0){
			txt += `${difss}月到${difse}月增长最多，增长了${difx.toFixed(2)} ${unit} <br/>`
		}
		if(difn<0){
			txt += `${difns}月到${difne}月下降最多，下降了${Math.abs(difn).toFixed(2)} ${unit} <br/>`
		}
		$("#freightfx").append(txt);}
	else {
		$("#passengerfx").html("");
		var txt = "";
			txt += `
			预测2020年客运走势图，其中运量最多的是${maxyear}月，为${max} ${unit}，最少的是${minyear}月，为${min} ${unit} <br/>`
			if(difx>0){
				txt += `${difss}月到${difse}月增长最多，增长了${difx.toFixed(0)} ${unit} <br/>`
			}
			if(difn<0){
				txt += `${difns}月到${difne}月下降最多，下降了${Math.abs(difn).toFixed(0)} ${unit} <br/>`
			}
		$("#passengerfx").append(txt);
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
