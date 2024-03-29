//载入时生成图片验证码
$(function () {
	var img = document.getElementsByClassName("checkimg")[0];
	img.src = "/apiz/imgGetCode";
})
//切换验证码
function imgChange() {
	var img = document.getElementsByClassName("checkimg")[0];
	var time = new Date().getTime();
	img.src = "/apiz/imgGetCode?t=" + time;
}

//手机号栏失焦时，验证手机号
$(".cellphone").blur(function(){
var reg=/^1\d{10}$/;			//手机号正则
var cellphone=$('.cellphone').val()
var status=reg.test(cellphone);			//判断
if(status==false){
document.getElementById("label1").style.display="block";
document.getElementById("label1").innerHTML="<font color='red'>手机号格式不符合</font>"
}else{
document.getElementById("label1").style.display="none";
}
})

//密码验证
$(".password1").blur(function(){
	// var reg=/^1\d{10}$/;			//手机号正则
	var password=$('.password').val()
	var password1=$('.password1').val()
	console.log("密码验证",password,password1)
	// var status=reg.test(cellphone);			//判断
	if(password!=password1){
		console.log("密码验证",password,password1)
		document.getElementById("label3").style.display="block";
		document.getElementById("label3").innerHTML="<font color='red'>两次密码不一致</font>"
		document.getElementById("label1").style.display="none";
		document.getElementById("label2").style.display="none";
		document.getElementById("label4").style.display="none";
	}else{
	document.getElementById("label3").style.display="none";
	}
	})


//根据返回状态，提示错误
function checkinfo(status){
switch(status) {
     case 0:
document.getElementById("label1").style.display="block";
document.getElementById("label1").innerHTML="<font color='red'>手机号必须11位</font>"
document.getElementById("label2").style.display="none";
document.getElementById("label3").style.display="none";
document.getElementById("label4").style.display="none";
        break;
     case 1:
document.getElementById("label3").style.display="block";
document.getElementById("label3").innerHTML="<font color='red'>两次密码不一致</font>"
document.getElementById("label1").style.display="none";
document.getElementById("label2").style.display="none";
document.getElementById("label4").style.display="none";
        break;
     case 2:
document.getElementById("label4").style.display="block";
document.getElementById("label4").innerHTML="<font color='red'>验证码错误</font>"
document.getElementById("label1").style.display="none";
document.getElementById("label2").style.display="none";
document.getElementById("label3").style.display="none";
        break;
     case 3:
document.getElementById("label1").style.display="block";
document.getElementById("label1").innerHTML="<font color='red'>手机号已存在</font>"
document.getElementById("label2").style.display="none";
document.getElementById("label3").style.display="none";
document.getElementById("label4").style.display="none";
        break;
case 4:
document.getElementById("label2").style.display="block";
document.getElementById("label2").innerHTML="<font color='red'>选择地区</font>"
document.getElementById("label1").style.display="none";
document.getElementById("label3").style.display="none";
document.getElementById("label4").style.display="none";
        break;
case 5:
document.getElementById("label1").style.display="block";
document.getElementById("label1").innerHTML="<font color='red'>手机号格式不符合</font>"
document.getElementById("label2").style.display="none";
document.getElementById("label3").style.display="none";
document.getElementById("label4").style.display="none";
        break;
     default:
document.getElementById("label1").style.display="none";
document.getElementById("label2").style.display="none";
document.getElementById("label3").style.display="none";
document.getElementById("label4").style.display="none";
}
}


//Demo
layui.use('form', function () {
	var form = layui.form;
	// console.log(去去去去,$('#district').val())
	//监听提交
	form.on('submit(formDemo)', function (data) {
		console.log(465879);
		$.ajax({
			type: "post",
			url: "/apiz/register_register/register",
			//数据传入后端
			data: {
				name: $('.name').val(),
				cellphone:$('.cellphone').val(),
				password: $('.password').val(),
				password1:$('.password1').val(),
				code:$('.code').val(),
				district:$('#district').val(),
			},
			dataType: "json",
			success: function (data) {
				console.log("成功后返回的数据", data);
				//如果成功，弹出注册成功，跳转到登录页，失败弹窗
				if (data.code == 1) {
					alert(data.msg);
					location.href = "register_login.html"
				} else {
					//alert(data.msg);
					checkinfo(data.status)
					$('.code').val("");
					imgChange();
				}
				//				alert(data.msg);
			},
			error: function (data) {
				console.log("失败后返回的数据", data);
			}
		})
		return false;
	});
});


$(function () {
	//省
	$.ajax({
		type: "post",
		url: "/apiz/register_center/province",
		dataType: "json",
		success: function (data) {
			console.log(data.sheng[1].id)
			var province = data.sheng;
			$("#province").html("");
			txt = "";
			//			txt +=`<option value="${province.id}" class="province" name="province">${province.name}</option>`
			for (var i = 0; i < province.length; i++) {
				txt += `
                     <option onclick="provinceid('${province[i].id}')"  value="${province[i].id}">${province[i].name}</option>`
			}
			$("#province").append(txt);
			$("#county").html("");
			txt = "";
			txt += `<option value="110100">北京市</option>`
			$("#county").append(txt);
			$("#district").html("");
			txt = "";
			txt += `<option value="110101">东城区</option>`
			$("#district").append(txt);

			layui.form.render();		//重新渲染表单
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
})


layui.use('form', function () {
	var form = layui.form;
	//监听提交
	form.on("select(province)", function (data) {
		$.ajax({
			type: "post",
			url: "/apiz/register_center/province",
			dataType: "json",
			success: function (data) {
				var province = data.sheng;
				console.log("成功后返回的数据", province, district);
				$("#province").html("");
				txt = "";
				txt += `<option value="" class="province">省</option>`
				for (var i = 0; i < province.length; i++) {
					txt += `
                        <option onclick="provinceid('${province[i].id}')"  value="${province[i].id}">${province[i].name}</option>`
				}
				$("#province").append(txt);
				$("#county").html("");
				txt = "";
				txt += `<option value="">市</option>`
				$("#county").append(txt);
				$("#district").html("");
				txt = "";
				txt += `<option value="">区</option>`
				$("#district").append(txt);
			},
			error: function (data) {
				console.log("失败后返回的数据", data);
			}
		})
	})
});
//市
layui.use('form', function () {
	var form = layui.form;
	//监听提交
	form.on("select(province)", function (data) {
		var id = data.value;			//获取省id
		$.ajax({
			type: "post",
			url: "/apiz/register_center/county",
			data: {
				id: id,
			},
			dataType: "json",
			success: function (data) {
				var county = data.shi;
				console.log("市", county)
				$("#county").html("");
				txt = "";
				txt += `<option value="">市</option>`
				for (var i = 0; i < county.length; i++) {
					txt += `
                            <option class="countyid" value="${county[i].id}">${county[i].name}</option>`
				}
				$("#county").append(txt);
				$("#district").html("");
				txt = "";
				txt += `<option value="">区</option>`
				$("#district").append(txt);
				form.render();	//重新渲染
			},
			error: function (data) {
				console.log("失败后返回的数据", data);
			}
		})
	});
});

//区
layui.use('form', function () {
	var form = layui.form;
	//监听提交
	form.on("select(county)", function (data) {
		var id = data.value;
		$.ajax({
			type: "post",
			url: "/apiz/register_center/district",
			data: {
				id: id,
			},
			dataType: "json",
			success: function (data) {
				var district = data.qu;
				$("#district").html("");
				txt = "";
				txt += `<option value="">区</option>`
				for (var i = 0; i < district.length; i++) {
					txt += `
	                        <option class="districtid" value="${district[i].id}">${district[i].name}</option>`
				}
				$("#district").append(txt);
				form.render();	//重新渲染
			},
			error: function (data) {
				console.log("失败后返回的数据", data);
			}
		})
	});
});