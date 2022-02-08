<%@page import="com.myweb.user.model.UserDAO"%>
<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String id =request.getParameter("id");
String pw =request.getParameter("pw");
String name = request.getParameter("name");
String email=request.getParameter("email");
String address=request.getParameter("address"); 

UserDAO dao=UserDAO.getInstance();
UserVO vo=new UserVO(id,pw,name,email,address,null);
int result=dao.update(vo);

if(result==1){
session.setAttribute("UserVO", vo);
%>
<script>
alert("회원정보가 수정되었습니다.");
location.href="mypage.jsp";
</script>

<% 
}else{
	%>
	<script>
	alert("회원정보 수정에 실패했습니다.");
	location.href="mypage.jsp";
	
	</script>
	
<%}%>