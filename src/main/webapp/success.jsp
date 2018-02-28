<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:wb="http://open.weibo.com/wb">
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<script src="js/jquery-2.1.4.min.js"></script>
<title>登录成功</title>
</head>
<body>
<center>
	<h1>登录成功!欢迎您：</h1>
	<h1 id="userName"></h1>
	<hr>
	<!--用分享按钮，一个字 好好好 -->
	<!-- http://open.weibo.com/sharebutton -->
	<wb:share-button appkey="3703387386" addition="simple" type="button" default_text="我是分享的文字"  pic="http%3A%2F%2F%E5%95%8A%E5%95%8A%E5%95%8A%E6%93%A6||http%3A%2F%2F%E5%95%8A%E5%95%8A%E5%95%8A"></wb:share-button>
	<h5 id="context"></h5>
</center>
</body>

<script type="text/javascript">
$(function(){
	if(getUrlParam("type")=="weibo"){
		$.ajax({
			url : "${pageContext.request.contextPath}/oauth/weibo/user.action",
			dataType:"json",
			success : function(result) {
				/* $("#userName").html(result.name);  */
				$("#context").html(JSON.stringify(result));
				console.log(result);
			},
			error : function(result) {
				console.log(result);
				alert("error");
			},
			
		});
	}


	if(getUrlParam("type")=="QQ"){
		$.ajax({
			url : "${pageContext.request.contextPath}/oauth/qq/user.action",
			dataType:"json",
			success : function(result) {
				$("#context").html(JSON.stringify(result));
				console.log(result);
			},
			error : function(result) {
				console.log(result);
				alert("error");
			},
			
		});
	}
	
});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

</script>
</html>