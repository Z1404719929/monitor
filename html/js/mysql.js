//数据库备份
$(".backups").on("click", function () {
	console.log("数据库备份")
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/mysql/backups",
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
			if (data.status == 0) {
				alert("备份成功")
			} else {
				alert("备份失败")
			}
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
})

//数据库还原
$(".reduction").on("click", function () {
	console.log("数据库还原")
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/mysql/reduction",
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
			if (data.status == 0) {
				alert("还原成功")
			} else {
				alert("还原失败")
			}
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
})

//退出登录
$(".exit").on("click", function () {
	sessionStorage.clear();
	location.href = "index.html"
})