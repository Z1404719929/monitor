
$(function(){
	img();
	teststatus();

})

$(".service").on("click", function(){
	var cc=$(".service").val();
	console.log(cc);
	$(".all").html("");
	var txt="";
	txt +=`<table class="layui-table">
  <colgroup>
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th>进程名</th>
      <th>端口号</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody class="tbody">
  </tbody>
</table>`
	$(".all").append(txt);
	
	
	
})

function teststatus(){
	post="8081";
	local=local1+$(".cb").val()
	var command="bash ~/sh/webskjps.sh";
	$.ajax({
		// 请求类型
		type:"post",
		// 请求路径
		url:"monitor_main/linklinux",
		// 请求参数
		data:{
			local:local,
			command:command,
		},
		// 返回数据类型
		dataType:"json",
		// 请求成功后调用函数
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
	var linux=new Array()	// 存linux拿到的信息

	// 获取linux信息
	ws.onmessage = function(event) {
	linux[i]=event.data;
	i=i+1;
	var a=linux[i-1].split(" ");
	console.log(a[0]);
	console.log(a[1]);
	}
}
}
































// 显示头像
function img(){
	var userid=sessionStorage.getItem("id");
	var username=sessionStorage.getItem("name");
	$(".user").html("");
	var txt="";
	txt +=`<img src="/headImg?id=${userid}"  style="
    width: 40px;
    height: 40px;
    border-radius: 50px;">${username}`
	$(".user").append(txt);
}
function defaultImg(img){
	var id=sessionStorage.getItem("id")
	img.src="images/user-lg.png";
}

