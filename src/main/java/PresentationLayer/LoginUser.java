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
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException, OrderException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = LogicFacade.login(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("role", user.getRole());
        if (user.getRole().equals("customer")) {
            Orders orders = LogicFacade.getOrders(user);
            session.setAttribute("orders", orders);
        } else {
            Orders orders = LogicFacade.getOrders(user);
            Orders ordersNotShipped = LogicFacade.getOrdersNotShipped();
            request.setAttribute("orders", orders);
            request.setAttribute("ordersNotShipped", ordersNotShipped);
        }
        return user.getRole() + "page";
    }

}
