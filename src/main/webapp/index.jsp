<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<script src="js/jquery-2.1.4.min.js"></script>
<title>登录界面</title>
</head>
<body>
    <center>
        <h1 style="color:red">登录</h1>
                <table border="0">
                    <tr>
                        <td>账号：</td>
                        <td><input type="text" name="username"></td>
                    </tr>
                    <tr>
                        <td>密码：</td>
                        <td><input type="text" name="password">
                        </td>
                    </tr>
                </table>
            <br>
                <input type="submit" value="登录" style="color:#BC8F8F">
                <input type="submit" value="注册" style="color:#BC8F8F"> <br>
                
                                                    第三方登录<br>------------------------------<br>
                <img id="qq" src="image/qq.jpg" width="30px" height="40px" style="cursor:pointer;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <img  id="weibo" src="image/weibo.jpg" width="35px" height="45px" style="cursor:pointer;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <img  id="weixin" src="image/weixin.jpg" width="40px" height="50px" style="cursor:pointer;"/>&nbsp;&nbsp;
                <br><hr>
				注:不用第三方登录默认账号和密码如下<br>
				账号默认：admin<br>
				密码默认：123456<br>
				

</body>
<script type="text/javascript">
$(function(){
	
	$("#weibo").click(function(){
		$.ajax({
			url : "${pageContext.request.contextPath}/oauth/weibo.action",
			dataType:"text",
			success : function(result) {
				location.href=result;
			},
			error : function(result) {
				console.log(result); 
				alert("errorr");
			},
			
		});
	});

	$("#qq").click(function(){
		$.ajax({
			url : "${pageContext.request.contextPath}/oauth/qq.action",
			dataType:"text",
			success : function(result) {
				location.href=result;
			},
			error : function(result) {
				console.log(result); 
				alert("errorr");
			},
			
		});
	});

	$("#weixin").click(function(){
	});

});

</script>
</html>