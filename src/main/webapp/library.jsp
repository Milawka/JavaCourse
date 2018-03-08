<%--
  Created by IntelliJ IDEA.
  book: mila
  Date: 07.03.18
  Time: 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<table style="width:100%">
    <caption>Books</caption>

    <tr>
        <th>Name</th>
        <th>Author</th>
        <th>Reference</th>
    </tr>
<c:forEach items="${requestScope.list}" var="book">
    <tr>
    <td><c:out value="${book.name}"></c:out></td>
    <td><c:out value="${book.author}"></c:out></td>
        <td><a href='<c:out value="${book.reference}"/>'>read</a></td>
    </tr>
</c:forEach>
</table>

</body>
</html>
