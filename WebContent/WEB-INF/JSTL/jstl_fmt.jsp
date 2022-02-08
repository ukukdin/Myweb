<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h>
<h3>formatNumber, parseNumber, formatDate, parseDate</h3>


<!-- 
format:형식변경
parse:형변환
 -->
<c:set var="d01" value="2020"/>
<fmt:formatNumber var="a" value="${d01}" pattern="000000"/>
<fmt:formatNumber var="a1" value="${d01}" pattern="0000.00"/>
${a }<br>
${a1 }
<h3>formatDate</h3>
<c:set var="d02" value="<%=new Date() %>"/>
<fmt:formatDate var="a3" value="${d02 }" pattern="yyyyMMdd"/>
<fmt:formatDate var="a4" value="${d02 }" pattern="yyyy년MM월dd일"/>
<fmt:formatDate var="a5" value="${d02 }" pattern ="yyyy-MM-dd HH:mm:ss"/>
${a3 }<br>
${a4 }<br>
${a5 }<br>
<hr>

<h3>parseDate -> 문자를 날짜로 변환</h3>
<c:set var="d03" value="2020/01/26"/>
<fmt:parseDate var="a6" value="${d03 }" pattern="yyyy/MM/dd"/>
<c:set var="d04"  value="2020/01/26 23:12:44"/>
<fmt:parseDate var="a7" value="${d04 }" pattern="yyyy/MM/dd HH:mm:SS"/>
${a6}<br>
${a7 }<br>

<hr>

<h3>parseNumber -> 형변환한다.</h3>
<c:set var ="d05" value="1.123"></c:set>
<c:set var ="d06" value="1.123abc"></c:set>
<fmt:parseNumber var="a8" value="${ d05}" pattern="0.000"/>
<fmt:parseNumber var="a9" value="${ d06}" type="number"/>
${a8 }<br>
${a9 }
<hr>
<h3>아래의 값을 2020년 05월03일 형식으로 출력</h3>
<c:set var="time_a" value="2020-05-03"/>

<fmt:parseDate var="b1" value="${time_a }" pattern="yyyy-MM-dd"/>
<fmt:formatDate var="b2" value="${b1 }" pattern="yyyy년MM월dd일"/>

${b2}
</body>
</html>