package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.OrderException;
import FunctionLayer.Orders;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageGoTo extends Command {

    public PageGoTo() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException {
        User user = (User) request.getSession().getAttribute("user");

        if (!user.getRole().equals("customer")) {
            Orders orders = LogicFacade.getOrders(user);
            Orders ordersNotShipped = LogicFacade.getOrdersNotShipped();

            request.setAttribute("orders", orders);
            request.setAttribute("ordersNotShipped", ordersNotShipped);
        }
        return user.getRole() + "page";
    }

}
