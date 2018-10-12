/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.BrickList;
import FunctionLayer.OrderException;
import FunctionLayer.LogicFacade;
import FunctionLayer.OrderDetails;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author morte
 */
public class OrderFromDB extends Command {

    public OrderFromDB() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderException {
        
        User user = (User) request.getSession().getAttribute("user");
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        
        OrderDetails orderDetails = LogicFacade.getOrderFromDB(user, order_id);
        BrickList brickList = LogicFacade.calcBrickList(orderDetails.getLength(), orderDetails.getWide(), orderDetails.getHeight());
        
        request.setAttribute("bricklist", brickList);
        request.setAttribute("order_id", orderDetails.getOrder_id());
        request.setAttribute("email", orderDetails.getUsername());
        request.setAttribute("orderdate", orderDetails.getOrderDate());
        request.setAttribute("shippeddate", orderDetails.getShippedDate());
        request.setAttribute("success", "Bestilling er OK og hentet fra databasen!");
        
        return "orderdetailspage";
    }
    
}
