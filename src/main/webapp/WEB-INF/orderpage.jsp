<%-- 
    Document   : customerpage
    Created on : Aug 22, 2017, 2:33:37 PM
    Author     : kasper
--%>

<%@page import="FunctionLayer.Door"%>
<%@page import="FunctionLayer.Window"%>
<%@page import="FunctionLayer.BrickList"%>
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
        
        <% BrickList brickList = (BrickList) request.getAttribute("bricklist"); %>
        <% Door door = (Door) request.getAttribute("door"); %>
        <% Window window = (Window) request.getAttribute("window"); %>

        <h1>Bestilling af Lego Hus</h1>
        
        <table border="1">
            <tr>
                <th>Højde (Antal klodser)</th>
                <th>Længde (Antal prikker)</th>
                <th>Dybde (Antal brikker)</th>
                <th>Dør</th>
                <th>Vindue</th>
            </tr>
            <tr>
                <%
                        out.println("<td>" + brickList.getHeight() + "</td>");
                        out.println("<td>" + brickList.getLength() + "</td>");
                        out.println("<td>" + brickList.getWide() + "</td>");
                        if(brickList.isDoor()){
                            out.println("<td>Ja</td>");
                        } else {
                            out.println("<td>Nej</td>");
                        }
                        if(brickList.isWindow()){
                            out.println("<td>Ja</td>");
                        } else {
                            out.println("<td>Nej</td>");
                        }
                %>
            </tr>
        </table>
            <br>
            <br>
        <table border="1">
            <tr>
                <th>Type klods</th>
                <th>Side 1 (For)</th>
                <th>Side 2 (Bag)</th>
                <th>Side 3 (Side)</th>
                <th>Side 4 (Side)</th>
                <th>Ialt (Alle lag)</th>
            </tr>
            <tr>
                <td>2x4</td>
                <%
                    for (int i = 0; i < brickList.getListBricks2x4().length; i++) {
                        out.println("<td>" + brickList.getListBricks2x4()[i] + "</td>");
                    }
                %>
            </tr>
            <tr>
                <td>2x2</td>
                <%
                    for (int i = 0; i < brickList.getListBricks2x2().length; i++) {
                        out.println("<td>" + brickList.getListBricks2x2()[i] + "</td>");
                    }
                %>
            </tr>
            <tr>
                <td>2x1</td>
                <%
                    for (int i = 0; i < brickList.getListBricks2x1().length; i++) {
                        out.println("<td>" + brickList.getListBricks2x1()[i] + "</td>");
                    }
                %>
            </tr>
        </table>
                <% if(brickList.isDoor()){ %>
                <br>
            <br>
        <table border="1">
            <tr>
                <th>Type</th>
                <th>Højde (Antal klodser)</th>
                <th>Længde (Antal prikker)</th>
            </tr>
            <tr>
                <td>Dør</td>
                <%
                        out.println("<td>" + door.getHeight() + "</td>");
                        out.println("<td>" + door.getLength() + "</td>");
                %>
            </tr>
        </table>
            <% } %>
            <% if(brickList.isWindow()){ %>
                <br>
            <br>
        <table border="1">
            <tr>
                <th>Type</th>
                <th>Højde (Antal klodser)</th>
                <th>Længde (Antal prikker)</th>
            </tr>
            <tr>
                <td>Vindue</td>
                <%
                        out.println("<td>" + window.getHeight() + "</td>");
                        out.println("<td>" + window.getLength() + "</td>");
                %>
            </tr>
        </table>
            <% } %>
            <br>
            <br>
        <table>
            <tr>
                <td>
                    <form name="customer" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="customer">
                        <input type="submit" value="Tilbage">
                    </form>
                </td>
            </tr>
        </table>
        <%
            String success = (String) request.getAttribute("success");
            if (success != null) {
                out.println("<H2>Bestilling OK!</h2>");
                out.println(success);
            }
        %>
    </body>
</html>
