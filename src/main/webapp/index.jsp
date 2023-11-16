
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HTML và JSP Example</title>
</head>
<body>
<h1>Chào mừng đến với trang JSP và HTML</h1>

<%-- Biểu thức JSP để hiển thị thời gian hiện tại --%>
<p>Thời gian hiện tại: <%= new java.util.Date() %></p>

<%-- Biểu thức JSP để thực hiện vòng lặp và hiển thị các số --%>
<ul>
    <% for (int i = 1; i <= 5; i++) { %>
    <li>Số: <%= i %></li>
    <% } %>
</ul>
</body>
</html>

