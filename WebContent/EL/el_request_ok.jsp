<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
   /* UserVO vo= (UserVO)request.getAttribute("vo");
    String id =vo.getId(); */
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${vo.id }<br>
${vo.name }<br>
${vo.email }<br>

</body>
</html>