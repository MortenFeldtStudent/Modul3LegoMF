package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.OrderException;
import FunctionLayer.OrderShipException;
import FunctionLayer.Orders;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderToShip extends Command {

    public OrderToShip() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderShipException, OrderException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        int order_id = Integer.parseInt(request.getParameter("order_id"));

        LogicFacade.orderShipped(order_id);
        Orders orders = LogicFacade.getOrders(user);
        Orders ordersNotShipped = LogicFacade.getOrdersNotShipped();

        request.setAttribute("orders", orders);
        request.setAttribute("ordersNotShipped", ordersNotShipped);
        request.setAttribute("success", "Bestilling's ID: " + order_id + " er shipped og gemt i databasen!");

        return "employeepage";
    }

}
