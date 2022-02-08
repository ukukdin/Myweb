<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

String id =request.getParameter("id");
String pw = request.getParameter("pw");
String name = request.getParameter("name");
String email = request.getParameter("email");
String address=request.getParameter("address");

//중복검사 간단하게 한느방법 없으면 insert 작업으로 ㄱㄱ

UserDAO dao=UserDAO.getInstance();
int result = dao.idCheck(id);

if(result==1){//아이디 중복이 있는경우
%>
<script>
alert("중복된 아이디 입니다.");//경고창
history.go(-1);//뒤로가기
</script>
<%	
}else{//아이디 중복이 없는경우
	//insert 작업
	UserVO vo=new UserVO(id,pw,name,email,address,null);
	int result2=dao.insert(vo);

	if(result2==1){// 성공
		%>
		<script>
		alert("회원가입을 축하합니다.")
		location.href="login.jsp";
		</script>
		<%		
	}else{//실패
		%>
		<script>
		alert("중복된 아이디 입니다.");//경고창
		history.go(-1);//뒤로가기
		</script>
	
		
<%		
	}
}
%>


