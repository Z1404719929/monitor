//用于显示走势图的js
var status="freight"
var trendname="货运走势图"
var unit="吨"
var year;
var sum;
var trenddata=[];


$(function () {
	login();
	img();
	freighttrend();
	passengertrend();
})

//文字分析
function data_ana(){
	var max=sum[0];
	var min=sum[0];
	var maxyear=year[0];
	var minyear=year[0];
	var dif = new Array()	//差
	var difx,difn;	//存差最大最小值
	var yearstart = new Array();
	var yearend = new Array();
	for(var i=0;i<sum.length;i++){
		//求最大最小值
		if(max<sum[i]){
			max=sum[i];
			maxyear=year[i];
		}
		if(min>sum[i]){
			min=sum[i]
			minyear=year[i];
		}
		//差的值
		if(i<sum.length-1){
				dif[i]=sum[i+1]-sum[i]
				yearstart[i]=year[i]
				yearend[i]=year[i+1]
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
	// console.log("差最小最大",dif1[0],dif1[dif1.length-1]);
	if(status=="freight"){
	$("#freightfx").html("");
	var txt = "";
		txt += `
		表示07年到19年俄罗斯所有机场${trendname}的变化，其中货运量最多的是${maxyear}月，为${max} ${unit}，最少的是${minyear}月，为${min} ${unit} <br/>`
		if(difx>0){
			txt += `${difss}年到${difse}年增长最多，增长了${difx.toFixed(2)} ${unit} <br/>`
		}
		if(difn<0){
			txt += `${difns}年到${difne}年下降最多，下降了${Math.abs(difn).toFixed(2)} ${unit} <br/>`
		}
		$("#freightfx").append(txt);}
	else {
		$("#passengerfx").html("");
		var txt = "";
		txt += `
		表示07年到19年俄罗斯所有机场${trendname}的变化，其中客运量最多的是${maxyear}月，为${max} ${unit}，最少的是${minyear}月，为${min} ${unit} <br/>`
		if(difx>0){
			txt += `${difss}年到${difse}年增长最多，增长了${difx.toFixed(0)} ${unit} <br/>`
		}
		if(difn<0){
			txt += `${difns}年到${difne}年下降最多，下降了${Math.abs(difn).toFixed(0)} ${unit} <br/>`
		}
		$("#passengerfx").append(txt);
	}
}


// //点击货运机场
// $("#freighttrend").on("click", function () {
// 	// status="freight"
// 	// console.log("111")
// 	freighttrend();
// })
// //点击客运机场
// $("#passengetrend").on("click", function () {
// 	// status="passenger"
// 	// console.log(sdadas)
// 	passengertrend();
// })

//点击搜索
// $("#select").on("click", function () {
// 	airportname=$('#airportname').val()
// 	if(airportname==""){
// 		airportname="所有"
// 	}
// 	$(".selectair").html("");
// 	var txt = "";
// 	txt += `显示 ${airportname} 机场07-20年2月数据`
// 	$(".selectair").append(txt);
// 	freighttrend();
// 	passengertrend();
// })



//查询货运机场
function freighttrend() {
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/airtrend/freight_year",
		//请求参数
		data: {
			airportname:$('#airportname').val()
		},
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据111", data);
			status="freight"
			trendname="货运走势图"
			unit="吨"
			year=data.year;
			sum=data.sum;
			data_ana();
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
		url: "/apiz/airtrend/passenger_year",
		//请求参数
		data: {
			airportname:$('#airportname').val()
		},
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
			status="passenger"
			trendname="客运走势图"
			unit="人次"
			year=data.year;
			sum=data.sum;
			data_ana();
			echarts_trend()
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
			data: year,
			name: '年',
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
			data: sum,
			type: 'line',
			itemStyle: {
				normal:{
					color:'#d48265'
				}
			}
		}]
	};
	container.setOption(option,true);	

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
