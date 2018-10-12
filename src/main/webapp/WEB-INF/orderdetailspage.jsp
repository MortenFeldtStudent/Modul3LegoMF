<%-- 
    Document   : customerpage
    Created on : Aug 22, 2017, 2:33:37 PM
    Author     : kasper
--%>

<%@page import="FunctionLayer.BrickList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lego House</title>
    </head>
    <body>
        <% int order_id = (int) request.getAttribute("order_id"); %>
        <% String email = (String) request.getAttribute("email"); %>
        <% String orderDate = (String) request.getAttribute("orderdate"); %>
        <% String shippedDate = (String) request.getAttribute("shippeddate"); %>
        <% BrickList brickList = (BrickList) request.getAttribute("bricklist"); %>

        <h1>Bestilling af Lego Hus</h1>

        <table border="1">
            <tr>
                <th>BestillingsID</th>
                <th>Username</th>
                <th>OrderDate</th>
                <th>ShippedDate</th>
                <th>Højde (Antal klodser)</th>
                <th>Længde (Antal prikker)</th>
                <th>Dybde (Antal brikker)</th>
            </tr>
            <tr>
                <%
                    out.println("<td>" + order_id + "</td>");
                    out.println("<td>" + email + "</td>");
                    out.println("<td>" + orderDate + "</td>");
                    out.println("<td>" + shippedDate + "</td>");
                    out.println("<td>" + brickList.getHeight() + "</td>");
                    out.println("<td>" + brickList.getLength() + "</td>");
                    out.println("<td>" + brickList.getWide() + "</td>");
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
