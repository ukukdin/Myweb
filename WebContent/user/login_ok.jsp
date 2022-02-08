<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@page import="sun.security.mscapi.CKeyPairGenerator.RSA"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String id=request.getParameter("id");
String pw=request.getParameter("pw");

UserDAO dao= UserDAO.getInstance();
UserVO vo= dao.login(id, pw);


if(vo==null){
%>	
	<script >
	alert("아이디 비밀번호를 확인하세요");
	history.go(-1);
	</script>
	
<%
}else{
	session.setAttribute("UserVO", vo);
	response.sendRedirect("mypage.jsp");
	%>
<%} %>
	
	



