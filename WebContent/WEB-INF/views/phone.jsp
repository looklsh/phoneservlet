<%@page import="java.io.PrintWriter"%>
<%@page import="com.bit.phoneservlet.vo.PhoneBookVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 

	ServletContext context = getServletContext();
	String dbuser = context.getInitParameter("dbuser");
	String dbpass = context.getInitParameter("dbpass");
	%>    <%--이 코드가 필요한가? --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 Servlet</title>
</head>
<body>
	<h1>주소록 Servlet</h1>
	<h3>목록
	<%
	String name = (String)request.getAttribute("name");
	if(name != null){%>
		(검색어:<%=name %>)
		
	<% }%>
	</h3>
	
	<form>
	<label for="name">검색어</label>
	<input type="text" name="name">
	<input type="submit" value="검색"><br>
	</form>
	
	
	
	<table border="1">
	<thead>
	<tr>
	<th>이름</th>
	<th>휴대전화</th>
	<th>전화번호</th>
	<th>도구</th>
	</tr>
	</thead>
	
	<tbody>
	<%
	List<PhoneBookVO> list = (List<PhoneBookVO>)request.getAttribute("list");
			for(PhoneBookVO vo:list){
	%>
	<tr>
	
	<td><%=vo.getName() %></td>
	<td><%=vo.getHp() %></td>
	<td><%=vo.getTel() %></td>
	<td>
	<form method="post" action="<%= request.getContextPath() %>/ser">
	<input type="hidden" name="a" value="delete">
	<input type="hidden" name="id" value="<%=vo.getId() %>">
	<button type="submit">삭제</button>		
	</form>
	</td>
	
	</tr>
	<%} %>
	
	
	</tbody>
	
	</table>
	<a href="<%=request.getContextPath() %>/ser?a=addform">새 주소 추가</a>
	
	
</body>
</html>