/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.BrickList;
import FunctionLayer.CreateOrderException;
import FunctionLayer.LogicFacade;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author morte
 */
public class Order extends Command {

    public Order() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CreateOrderException {
        
        User user = (User) request.getSession().getAttribute("user");
        int height = Integer.parseInt(request.getParameter("heigth"));
        int length = Integer.parseInt(request.getParameter("length"));
        int wide = Integer.parseInt(request.getParameter("wide"));
        
        LogicFacade.createOrder(user, length, wide, height);
        BrickList brickList = LogicFacade.calcBrickList(length, wide, height);
        
        request.setAttribute("bricklist", brickList);
        request.setAttribute("success", "Bestilling er OK og gemt i databasen!");
        
        return "orderpage";
    }
    
}
