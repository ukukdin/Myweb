<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${vo.id }<br>
	${vo.name}<br>
	${auth }<br>
	<!-- sessionScope 값을 명시하는편이 좋습니다. -->
	${sessionScope.vo.id}<br>
	${sessionScope.vo.name}<br>
	${sessionScope.auth}<br>
</body>
</html>