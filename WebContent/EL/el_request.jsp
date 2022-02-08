<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    UserVO vo=new UserVO();
    vo.setId("bbb123");
    vo.setName("이순신");
    vo.setEmail("hhh12@gmail.com");
    
    request.setAttribute("vo", vo);
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>