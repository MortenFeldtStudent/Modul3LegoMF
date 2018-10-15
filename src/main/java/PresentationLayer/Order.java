package PresentationLayer;

import FunctionLayer.BrickList;
import FunctionLayer.OrderException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Orders;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Order extends Command {

    public Order() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException {
        HttpSession session = request.getSession();
        Orders orders = (Orders) session.getAttribute("orders");
        User user = (User) session.getAttribute("user");
        int height = Integer.parseInt(request.getParameter("heigth"));
        int length = Integer.parseInt(request.getParameter("length"));
        int wide = Integer.parseInt(request.getParameter("wide"));
        
        int order_id = LogicFacade.createOrder(user, length, wide, height);
        BrickList brickList = LogicFacade.calcBrickList(length, wide, height);
        
        orders = LogicFacade.isThisFirstOrder(orders);
        orders = LogicFacade.addOrderIdToOrders(orders, order_id);
        
        session.setAttribute("orders", orders);
        
        request.setAttribute("bricklist", brickList);
        request.setAttribute("success", "Bestilling er OK og gemt i databasen!");
        
        return "orderpage";
    }
    
}
