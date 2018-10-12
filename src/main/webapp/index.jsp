<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lego House</title>
    </head>
    <body>
        <h2>Login</h2>
        <table>
            <tr>
                <td>
                    <form name="login" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="login">
                        Email:<br>
                        <input type="text" name="email" value="test@test.dk">
                        <br><br>
                        Password:<br>
                        <input type="password" name="password" value="1234">
                        <br><br>
                        <input type="submit" value="Login">
                    </form>
                </td>
            </tr>
        </table>
        <br>
        <h2>Opret ny bruger</h2>
        <table>
            <tr>
                <td>
                    <form name="register" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="register">
                        Email:<br>
                        <input type="text" name="email" value="test@test.dk">
                        <br><br>
                        Password:<br>
                        <input type="password" name="password1" value="1234">
                        <br><br>
                        Gentag Password:<br>
                        <input type="password" name="password2" value="1234">
                        <br><br>
                        <input type="submit" value="Opret bruger">
                    </form>
                </td>
            </tr>
        </table>
        <% String error = (String) request.getAttribute( "error");
           if ( error != null) { 
               out.println("<H2>Fejl!</h2>");
               out.println(error);
           }
        %>
    </body>
</html>
