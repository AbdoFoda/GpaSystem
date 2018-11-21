<%--
  Created by IntelliJ IDEA.
  User: Abdo
  Date: 11/21/18
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String id = (String) session.getAttribute("id");
        String name = (String) session.getAttribute("name");
        String address = (String) session.getAttribute("address");
        float oldGpa = (float) session.getAttribute("oldGpa");
        float newGpa = (float)session.getAttribute("newGpa");
        out.println("Student ID :"+id+"<br>");
        out.println("Student Name :"+name+"<br>");
        out.println("Student Address :"+address+"<br>");
        out.println("Old Gpa :"+oldGpa+"<br>");
        out.println("New Gpa"+newGpa);
    %>

</body>
</html>
