<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 주소 추가</title>
</head>
<body>
	<h1>주소록 Servlet</h1>
	<h3>새 주소 등록</h3>
	
	<form name="addform" method="post" action="<%=request.getContextPath() %>/ser">
	<input type="hidden" name="a" value="add">
	
	<label for="name">이름</label>
	<input type="text" name="name"><br/>
	<label for="hp">휴대전화</label>
	<input type="text" name="hp"><br>
	<label for="tel">집전화</label>
	<input type="text" name="tel"><br>
	
	<input type="submit" value="주소등록">
	
	</form>
	
	<a href="<%=request.getContextPath() %>/">목록 보기</a>
</body>
</html>