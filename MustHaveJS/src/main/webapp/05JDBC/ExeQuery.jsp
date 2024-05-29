<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="utils.JDBConnect"%>
<%@page import="java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>JDBC</title></head>
<body>
	<h2>회원 목록 조회 테스트</h2>
	<%
	JDBConnect jdbc = new JDBConnect();
	
	String sql = "SELECT id, pass, name, regidate FROM member";
	Statement stmt = jdbc.con.createStatement();
	
	ResultSet rs = stmt.executeQuery(sql);
	while (rs.next()) {
		String id = rs.getString(1);
		String pw = rs.getString(2);
		String name = rs.getString("name");
		java.sql.Date regidate = rs.getDate("regidate");
		
		out.println(String.format("%s %s %s %s", id, pw, name, regidate) + "<br/>");
	}
	
	jdbc.close();
	%>
</body>
</html>