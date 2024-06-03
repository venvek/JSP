<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<meta charset="UTF-8">
<head><title>Result</title></head>
<body>
	<h2>Result 페이지</h2>
	<ul>
		<li>페이지 영역 : ${ pageScope.scopeValue }</li>
		<li>리퀘스트 영역 : ${ requestScope.scopeValue }</li>
		<li>세션 영역 : ${ sessionScope.scopeValue }</li>
		<li>애플리케이션 영역 : ${ applcationScope.scopeValue }</li>
	</ul>
	<h3> 영역지정 없이 속성</h3>
	<ul>
		<li>${ scopeValue }</li>
	</ul>
</body>
</html>