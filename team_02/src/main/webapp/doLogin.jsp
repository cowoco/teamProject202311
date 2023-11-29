<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인체크 -doLogin</title>
	</head>
	<body>
		<c:if test="${result==1}">
			<script>
				alert("로그인되었습니다.");
				location.href="a_main.do";
			</script>
		</c:if>
		<c:if test="${result!=1}">
			<script>
				alert("아이디 또는 패스 워드가 일치하지 않습니다. 다시 입력해주세요.");
				location.href="a_login.do";
			</script>
		</c:if>
		
	
	</body>
</html>