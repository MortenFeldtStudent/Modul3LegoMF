package PresentationLayer;

import FunctionLayer.BrickList;
import FunctionLayer.Door;
import FunctionLayer.OrderException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Orders;
import FunctionLayer.User;
import FunctionLayer.Window;
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
        
        int doorHeight = Integer.parseInt(request.getParameter("doorHeight"));
        int doorLength = Integer.parseInt(request.getParameter("doorLength"));
        int windowHeight = Integer.parseInt(request.getParameter("windowHeight"));
        int windowLength = Integer.parseInt(request.getParameter("windowLength"));
        
        int order_id = LogicFacade.createOrder(user, length, wide, height);
        Door door = LogicFacade.createDoor(doorLength, doorHeight);
        Window window = LogicFacade.createWindow(windowLength, windowHeight);
        BrickList brickList = LogicFacade.calcBrickList(length, wide, height, door, window);
        
        orders = LogicFacade.isThisFirstOrder(orders);
        orders = LogicFacade.addOrderIdToOrders(orders, order_id);
        
        session.setAttribute("orders", orders);
        
        request.setAttribute("door", door);
        request.setAttribute("window", window);
        request.setAttribute("bricklist", brickList);
        request.setAttribute("success", "Bestilling er OK og gemt i databasen!");
        
        return "orderpage";
    }
    
}
