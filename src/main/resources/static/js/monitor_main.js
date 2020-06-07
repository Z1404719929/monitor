var memory=new Array(60)	//存60秒内存使用量
var disk1;					//存磁盘使用量
var disk2;					//存磁盘未使用量
var xAxis = new Array(60);	//横坐标
var local1="192.168.1.";				//主机ip
var local="192.168.1.130"
var nn1="130";var nn2="131";var dn1="50";var dn2="51";var dn3="52";$(".cb").val(nn1);var post="1300";
var status="关闭";

var num=60;
memory.fill(0);	//开始时是0
for(var i=0;i<xAxis.length;i++){
  xAxis[i] = num;	//横坐标60-0
  num--;
}


$(function(){
	img();
	linklinux();
	defaultimage();
})




//连接主机的回调函数，每隔一秒访问一次
setInterval(function(){
			var i=0;
			var ws = new WebSocket('ws://'+local+':'+post+"/");
			console.log("111",ws)
			var linux=new Array()	//存linux拿到的信息

			//获取linux信息
			ws.onmessage = function(event) {
			  linux[i]=event.data;
			  i=i+1;
//			  console.log("5555",linux.length)
			  if(linux.length==22){
				  changetable();
			  var arr1=linux[9].split("=");	//内存使用量
				var arr2=linux[8].split("=");	//内存总量
				
				memory.shift();		//移除首选项
				memory.push(arr1[1]);	//末位插入
				
				var arr3=linux[14].split("=");	//盘符名称
				var arr4=linux[15].split("=");	//分区总大小
				var arr5=linux[18].split("=");	//分区使用量
				var arr6=linux[16].split("=");	//分区未使用
				
				arr4[1]=Math.ceil(arr4[1]/1024/1024);	//G做单位
				arr5[1]=Math.ceil(arr5[1]/1024/1024);
				arr6[1]=Math.ceil(arr6[1]/1024/1024);

				disk1=arr5[1];
				disk2=arr6[1];
					
				//调用echarts图片
				echartsimg(arr2[1],arr4[1]);
			}
			}
		},1000)
	
		




function linklinux(){
	var command="bash ~/sh/webskstart.sh";
	$.ajax({
		//请求类型
		type:"post",
		//请求路径
		url:"monitor_main/linklinux",
		//请求参数
		data:{
			local:local,
			command:command,
		},
		//返回数据类型
		dataType:"json",
		//请求成功后调用函数
		success:function(data){
			console.log("成功后返回的数据",data);
	 	},
		error:function(data){
			console.log("失败后返回的数据",data);
		}
	})
}




//显示头像
function img(){
	var userid=sessionStorage.getItem("id");
	var username=sessionStorage.getItem("name");
	$(".user").html("");
	var txt="";
	txt +=`<img src="/headImg?id=${userid}"  style="
    width: 50px;
    height: 50px;
    border-radius: 40px;">${username}`
	$(".user").append(txt);
}

//默认没有连接时显示的图片
function defaultimage(){		
	//显示内存折线图
	memory.fill(0);
	echartsimg(100,100);
}

function echartsimg(arr2,arr4){
	var main_image1=echarts.init(document.querySelector('#main-image1'));
	option = {
			title:{
				text:'内存使用量',
				textStyle:{
					color:'black',
					 fontSize:30					 
				},
				left:'15%',
			},
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: xAxis,
		        name:'秒',
		        nameLocation:'end',
		        nameTextStyle:{
		        	fontSize:20,
		        }
		    },
		    yAxis: {
		        type: 'value',
		        max: arr2,
		        name:'B',
		        nameTextStyle:{
		        	fontSize:20,
		        }
		    },
		    makepoint:{},
		    series: [{
		        data: memory,
		        type: 'line',
		        areaStyle: {
		        	normal: {
						color: '#61a0a8' //改变区域颜色
					}
		        },
		    itemStyle: {
				normal: {
					color: '#61a0a8', //改变折线点的颜色
					lineStyle: {
						color: '#61a0a8', //改变折线颜色

					},
				}
			},
		    }]
		};
	main_image1.setOption(option);
	
	//显示磁盘饼图
	var main_image2=echarts.init(document.querySelector('#main-image2'));
	option = {
		    title: {
		        text: '磁盘使用情况',
		        subtext:'总量'+(disk1+disk2)+"GB",
		        left: 'center'
		    },
		    tooltip: {
		        trigger: 'item',
		        formatter: '{a} <br/>{b} : {c} ({d}%)'
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: ['已使用', '未使用']
		    },
		    series: [
		        {
		            name: '单位GB',
		            type: 'pie',
		            radius: '55%',
		            center: ['50%', '60%'],
		            data: [
		                {value: disk1, name: '已使用', itemStyle: {normal: {color: '#d48265'}}}, 	//改变折线点的颜色
		                {value: disk2, name: '未使用', itemStyle: {normal: {color: '#749f83'}}},
		            ],
		            emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		    ]
		};
	main_image2.setOption(option);
}




$(".startnn").on("click", function(){
	local=local1+$(".cb").val()
	var command="bash ~/sh/startnn.sh";
	$.ajax({
		//请求类型
		type:"post",
		//请求路径
		url:"monitor_main/linklinux",
		//请求参数
		data:{
			local:local,
			command:command,
		},
		//返回数据类型
		dataType:"json",
		//请求成功后调用函数
		success:function(data){
			console.log("成功后返回的数据",data);
	 	},
		error:function(data){
			console.log("失败后返回的数据",data);
		}
	})
})

$(".startzkfc").on("click", function(){
	local=local1+$(".cb").val()
	var command="bash ~/sh/startzkfc.sh";
	$.ajax({
		//请求类型
		type:"post",
		//请求路径
		url:"monitor_main/linklinux",
		//请求参数
		data:{
			local:local,
			command:command,
		},
		//返回数据类型
		dataType:"json",
		//请求成功后调用函数
		success:function(data){
			console.log("成功后返回的数据",data);
	 	},
		error:function(data){
			console.log("失败后返回的数据",data);
		}
	})
})

$(".stop").on("click", function(){
	teststatus()
})



////////////切换主机
$(".namenode1").on("click", function(){
	local=local1+nn1;
	post="1300";
	linklinux();
//	status="关闭"	//默认关闭，连接成功时切换开启
//	changetable();
	defaultimage();
	$('.layui-this').removeClass('layui-this');	//样式改变
	$(this).addClass("layui-this");
	$(".cb").val(nn1)	//值存入cb中
})
$(".namenode2").on("click", function(){
	local=local1+nn2;
	post="1310";
	linklinux();
//	status="关闭"	//默认关闭，连接成功时切换开启
//	changetable();
	defaultimage();
	$('.layui-this').removeClass('layui-this');	//样式改变
	$(this).addClass("layui-this");
	$(".cb").val(nn2)	//值存入cb中
})
$(".datanode1").on("click", function(){
	local=local1+dn1;
	post="5000";
	linklinux();
//	status="关闭"	//默认关闭，连接成功时切换开启
//	changetable();
	defaultimage();
	$('.layui-this').removeClass('layui-this');	//样式改变
	$(this).addClass("layui-this");
	$(".cb").val(dn1)	//值存入cb中
})
$(".datanode2").on("click", function(){
	local=local1+dn2;
	post="5100";
	linklinux();
//	status="关闭"	//默认关闭，连接成功时切换开启
//	changetable();
	defaultimage();
	$('.layui-this').removeClass('layui-this');	//样式改变
	$(this).addClass("layui-this");
	$(".cb").val(dn2)	//值存入cb中
})
$(".datanode3").on("click", function(){
	local=local1+dn3;
	post="5200";
	linklinux();
//	status="关闭"	//默认关闭，连接成功时切换开启
//	changetable();
	changetable();
	defaultimage();
	$('.layui-this').removeClass('layui-this');	//样式改变
	$(this).addClass("layui-this");
	$(".cb").val(dn3)	//值存入cb中
})


function teststatus(){
	post="8081";
	local=local1+$(".cb").val()
	var command="bash ~/sh/webskjps.sh";
	$.ajax({
		//请求类型
		type:"post",
		//请求路径
		url:"monitor_main/linklinux",
		//请求参数
		data:{
			local:local,
			command:command,
		},
		//返回数据类型
		dataType:"json",
		//请求成功后调用函数
		success:function(data){
			console.log("成功后返回的数据",data);
	 	},
		error:function(data){
			console.log("失败后返回的数据",data);
		}
	})
			var i=0;
			var ws = new WebSocket('ws://'+local+':'+post+'/');
			console.log("111",ws)
			var linux=new Array()	//存linux拿到的信息

			//获取linux信息
			ws.onmessage = function(event) {
			  linux[i]=event.data;
			  i=i+1;
			  var a=linux[i-1].split(" ");
			  console.log(a[0]);
			  console.log(a[1]);
			  if(a[1]=="NameNode"||a[1]=="DameNode"){
				  status="开启";
				  changetable();
			  }
			}
}

//表格显示
function changetable(){
	$(".localtable").html("");
	var txt="";
	txt +=` <tr>
      <td  style="font-size: 30px;">运行状态</td>
      <td style="font-size: 30px;">${status}</td>
    </tr>
    <tr>
      <td style="font-size: 30px;">ip地址</td>
      <td style="font-size: 30px;">${local}</td>
    </tr>
    </tr>`
	$(".localtable").append(txt);
}
