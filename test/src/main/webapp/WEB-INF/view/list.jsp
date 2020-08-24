<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
     <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table border=1>
<tr>
	<th>id</th><th>productName</th><th>amount</th><th>price</th><th>remark</th>
</tr>
<c:forEach items="${list}" var="l" varStatus="i">
<tr>
<td>${l.id}</td><td>${l.productName}</td><td>${l.amount}</td><td>${l.price}</td><td>${l.remark}</td>
</tr>
</c:forEach>
</table>
</body>
</html>