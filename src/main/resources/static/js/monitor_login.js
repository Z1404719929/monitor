//载入时生成图片验证码
$(function() {
	var img = document.getElementsByClassName("checkimg")[0];
	img.src = "imgGetCode";
})
//切换验证码
function imgChange() {
	var img = document.getElementsByClassName("checkimg")[0];
	var time = new Date().getTime();
	img.src = "imgGetCode?t=" + time;
}
