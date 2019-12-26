<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<base href="<%=basepath %>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Insert title here</title>
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
</head>
<body>
 用户名<input type="text" value="" name="username" id="username"><br>
    密码<input type="password" value="" name="password" id="password"><br><br>
    <img alt="验证码" src="/user/check" id="img"><br><br>
    验证码<input type="text" value="" name="judge" id="judge"><br>
    <input type="button" value="提交" id="sub"><br>

<script type="text/javascript">
  $(function(){
            $("#img").click(function(){
         var url = "/user/check?number="+Math.random();  
                $("#img").attr("src",url);
            })
            $("#sub").click(function(){
                $.ajax({
                    url:'/submit',
                    dataType:'json',
                    type:'post',
                    data:{
                        username:$("#username").val(),
                        password:$("#password").val(),
                        judge:$("#judge").val()
                    },
                    success:function(res){
                        alert('登录成功');
                        if(res.code==500){
                            var url = "/user/check?number="+Math.random();  
                            $("#img").attr("src",url);
                        }
                    },
                    error:function(res){
                        alert("网络错误");
                    }
                })
            })
        })
    </script>
    
</body>
</html>
