//用于显示统计图的js
var status="freight"
var trendname="货运统计图"
var month;		//存月份
var yeardata;	//存数据和
var unit;		//单位


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
	// 	start="2007"
	// 	end="2020"
	// }
	$(".selectair").html("");
	var txt = "";
	txt += `显示 ${airportname} 机场07-20年数据`
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
		url: "/apiz/data_statistics/freight_year",
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
			console.log("成功后返回的数据111", data);
			status="freight"
			trendname="货运统计图"
			unit="吨";
			month=data.month;
			yeardata=data.data;
			console.log(yeardata,month);
			echarts_trend();
			console.log("成功后返回的数据");
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
			// alert("查询条件有误")
		}
	})
}
//查询客运机场分布
function passengertrend() {
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/data_statistics/passenger_year",
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
			unit="人次"
			month=data.month;
			yeardata=data.data;
			console.log("258",yeardata,month);
			echarts_trend()
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}




function echarts_trend(){
	var container = echarts.init(document.getElementById(status));
var data = genData(50);
option = {
    title: {
        text: trendname,
        subtext:'单位：'+unit,
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
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
            name: '年份',
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
    for (var i = 0; i < yeardata.length; i++) {
        legendData.push(month[i]);
        seriesData.push({
            name: month[i],
            value: yeardata[i]
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








// function echarts_trend(){
// 	var container = echarts.init(document.getElementById(status));
// 	option = {
// 		title: {
// 			text: trendname,
// 		},
// 		xAxis: {
// 			type: 'category',
// 			data: month,
// 			name: '月',
// 		},
// 		yAxis: {
// 			type: 'value',
// 			name: unit,
// 		},
// 		tooltip: {
// 			trigger: 'axis'
// 		},
// 		series: [{
// 			data: yeardata,
// 			type: 'bar',
// 			showBackground: true,
// 			backgroundStyle: {
// 				color: 'rgba(220, 220, 220, 0.8)'
// 			}
// 		}]
// 	};
// 	container.setOption(option,true);	

// }



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
