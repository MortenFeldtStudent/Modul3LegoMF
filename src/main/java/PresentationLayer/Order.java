package PresentationLayer;

import FunctionLayer.BrickList;
import FunctionLayer.Door;
import FunctionLayer.DoorWindowException;
import FunctionLayer.House;
import FunctionLayer.OrderException;
import FunctionLayer.LogicFacade;
import FunctionLayer.OrderDetails;
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
    String execute(HttpServletRequest request, HttpServletResponse response) throws DoorWindowException, OrderException {
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
        
        OrderDetails orderDetails = LogicFacade.createOrder(user, length, wide, height);
        House house = LogicFacade.createHouse(length, wide, height, doorLength, doorHeight, windowLength, windowHeight);
        LogicFacade.houseCheckDoorWindow(house);
        BrickList brickList = LogicFacade.calcBrickList(house);
        
        orders = LogicFacade.isThisFirstOrder(orders);
        orders = LogicFacade.addOrderIdToOrders(orders, orderDetails.getOrder_id());
        
        session.setAttribute("orders", orders);
        
        request.setAttribute("door", house.getDoor());
        request.setAttribute("window", house.getWindow());
        request.setAttribute("bricklist", brickList);
        request.setAttribute("order_id", orderDetails.getOrder_id());
        request.setAttribute("email", orderDetails.getUsername());
        request.setAttribute("orderdate", orderDetails.getOrderDate());
        request.setAttribute("shippeddate", orderDetails.getShippedDate());
        request.setAttribute("success", "Bestilling er OK og gemt i databasen!");
        
        return "orderdetailspage";
    }
    
}
