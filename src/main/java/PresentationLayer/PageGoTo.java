package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.OrderException;
import FunctionLayer.Orders;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PageGoTo extends Command {

    public PageGoTo() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (!user.getRole().equals(LogicFacade.getCUSTOMER())) {
            Orders orders = LogicFacade.getOrders(user);
            Orders ordersNotShipped = LogicFacade.getOrdersNotShipped();

            request.setAttribute("orders", orders);
            request.setAttribute("ordersNotShipped", ordersNotShipped);
        }
        return user.getRole() + "page";
    }

}
