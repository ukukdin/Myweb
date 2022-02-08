<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 변수선언(el태그로 참조가 가능합니다. -->


<!-- 출력문 -->
<c:out value="a"/><br>
<c:out value="hello"/><br>
<c:out value="${a} "/><br>
<!-- if문 -->
<c:if test="true">무조건 실행되는 문장</c:if>
<c:if test="${param.name eq '홍길동' }">홍길동 이란 이름입니다.</c:if><br>
<c:if test="${param.name eq '이순신' }">이순신 이란 이름입니다.</c:if><br>

<c:if test="${param.age >=20 }">성인입니다.</c:if><br>
<c:if test="${param.age <20 }">미성년자입니다.</c:if><br>

<hr/>

</body>
</html>