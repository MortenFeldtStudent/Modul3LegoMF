<%-- 
    Document   : customerpage
    Created on : Aug 22, 2017, 2:33:37 PM
    Author     : kasper
--%>

<%@page import="FunctionLayer.Orders"%>
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
        <% Orders orders = (Orders) request.getSession().getAttribute("orders");%>
        <h1>Bestil Lego Hus</h1>

        <table>
            <tr>
                <td>
                    <form name="order" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="order">
                        Højde (Antal klodser):<br>
                        <input type="text" name="heigth" value="10">
                        <br><br>
                        Længde (Antal prikker):<br>
                        <input type="text" name="length" value="20">
                        <br><br>
                        Dybde (Antal prikker):<br>
                        <input type="text" name="wide" value="20">
                        <br><br>
                        Dør - Højde (Antal klodser):<br>
                        <input type="text" name="doorHeight" value="6">
                        <br><br>
                        Dør - Længde (Antal prikker):<br>
                        <input type="text" name="doorLength" value="3">
                        <br><br>
                        Vindue - Højde (Antal klodser):<br>
                        <input type="text" name="windowHeight" value="5">
                        <br><br>
                        Vindue - Længde (Antal prikker):<br>
                        <input type="text" name="windowLength" value="5">
                        <br><br>
                        <input type="submit" value="Bestil">
                    </form>
                </td>
            </tr>
        </table>
        <br><br><br>

        <%
            if (orders != null) {
        %>
        <h1>Tidligere bestillinger</h1>
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
        <h1>Ingen tidligere bestillinger!</h1>
        <%
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
