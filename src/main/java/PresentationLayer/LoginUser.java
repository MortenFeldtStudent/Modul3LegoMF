package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginUserException;
import FunctionLayer.OrderException;
import FunctionLayer.Orders;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginUser extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginUserException, OrderException {
        String email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        User user = LogicFacade.login( email, password );
        Orders orders = LogicFacade.getOrders(user);
        HttpSession session = request.getSession();
        session.setAttribute( "user", user );
        session.setAttribute( "role", user.getRole() );
        session.setAttribute("orders", orders);
        return user.getRole() + "page";
    }

}
