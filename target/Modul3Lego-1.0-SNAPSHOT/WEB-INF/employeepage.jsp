<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>

<%@page import="FunctionLayer.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lego House</title>
    </head>
    <body>
        <form name="logout" action="FrontController" method="POST">
            <input type="hidden" name="command" value="logout">
            <input type="submit" value="Logud">
        </form>
        <%-- <h1>Hello <%=request.getParameter( "email")%> </h1> --%>
        <h1>Hello <%= ((User) session.getAttribute("user")).getEmail()%> </h1>
        You are now logged in as a EMPLOYEE of our wonderful site.
    </body>
</html>
