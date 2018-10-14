<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>

<%@page import="FunctionLayer.Orders"%>
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
        <% Orders orders = (Orders) request.getAttribute("orders");%>
        <% Orders ordersNotShipped = (Orders) request.getAttribute("ordersNotShipped");%>
        <%
            if (ordersNotShipped != null) {
        %>
        <h1>Bestillinger i systemet til at shippe</h1>
        <table>
            <tr>
                <td>
                    <form name="ordertoship" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="ordertoship" >
                        <select name="order_id">
                            <%                                for (Integer order_id : ordersNotShipped.getListOrders()) {
                                    out.println("<option value=\"" + order_id + "\">BestillingID: " + order_id + "</option>");
                                }

                            %>
                        </select>
                        <br><br>
                        <input type="submit" value="Ship valgte bestilling">
                    </form>
                </td>
            </tr>
        </table>
        <%                            } else {
        %>
        <h1>Ingen bestillinger i systemet til at shippe!</h1>
        <%
            }
        %>

        <%
            if (orders != null) {
        %>
        <h1>Bestillinger i systemet</h1>
        <table>
            <tr>
                <td>
                    <form name="showorderdetails" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="showorderdetails" >
                        <select name="order_id">
                            <%                                for (Integer order_id : orders.getListOrders()) {
                                    out.println("<option value=\"" + order_id + "\">BestillingID: " + order_id + "</option>");
                                }

                            %>
                        </select>
                        <br><br>
                        <input type="submit" value="Vis valgte bestilling">
                    </form>
                </td>
            </tr>
        </table>
        <%                            } else {
        %>
        <h1>Ingen bestillinger i systemet!</h1>
        <%
            }
        %>


        <%
            String success = (String) request.getAttribute("success");
            if (success != null) {
                out.println("<H2>" + success + "</h2>");
            }
        %>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
                out.println("<H2>Fejl!!</h2>");
                out.println(error);
            }
        %>
    </body>
</html>
