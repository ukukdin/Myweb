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
<!-- ifelse -->


<c:choose>
	<c:when test="${param.name eq '홍길동' }">길동아~</c:when>
	<c:when test="${param.name eq '이순신' }">순신아</c:when>
	<c:otherwise>둘다 내자식이 아니여</c:otherwise><br>
</c:choose>


<c:choose>

<c:when test="${param.age >=20 }">성인
</c:when>
<c:otherwise>미자</c:otherwise>
</c:choose>
</body>
</html>