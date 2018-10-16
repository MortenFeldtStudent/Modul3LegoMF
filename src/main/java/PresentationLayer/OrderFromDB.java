package PresentationLayer;

import FunctionLayer.BrickList;
import FunctionLayer.Door;
import FunctionLayer.OrderException;
import FunctionLayer.LogicFacade;
import FunctionLayer.OrderDetails;
import FunctionLayer.User;
import FunctionLayer.Window;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderFromDB extends Command {

    public OrderFromDB() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        
        OrderDetails orderDetails = LogicFacade.getOrderFromDB(user, order_id);
        Door door = LogicFacade.createDoor(3, 6);
        Window window = LogicFacade.createWindow(5, 5);
        BrickList brickList = LogicFacade.calcBrickList(orderDetails.getLength(), orderDetails.getWide(), orderDetails.getHeight(), door, window);
        
        request.setAttribute("door", door);
        request.setAttribute("window", window);
        
        request.setAttribute("bricklist", brickList);
        request.setAttribute("order_id", orderDetails.getOrder_id());
        request.setAttribute("email", orderDetails.getUsername());
        request.setAttribute("orderdate", orderDetails.getOrderDate());
        request.setAttribute("shippeddate", orderDetails.getShippedDate());
        request.setAttribute("success", "Bestilling er OK og hentet fra databasen!");
        
        return "orderdetailspage";
    }
    
}
