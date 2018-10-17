package PresentationLayer;

import FunctionLayer.BrickList;
import FunctionLayer.House;
import FunctionLayer.OrderException;
import FunctionLayer.LogicFacade;
import FunctionLayer.OrderDetails;
import FunctionLayer.User;
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
        House house = LogicFacade.createHouse(orderDetails.getLength(), orderDetails.getWide(), orderDetails.getHeight(), orderDetails.getDoor().getLength(), orderDetails.getDoor().getHeight(), orderDetails.getWindow().getLength(), orderDetails.getWindow().getHeight());
        BrickList brickList = LogicFacade.calcBrickList(house);

        request.setAttribute("door", house.getDoor());
        request.setAttribute("window", house.getWindow());

        request.setAttribute("bricklist", brickList);
        request.setAttribute("order_id", orderDetails.getOrder_id());
        request.setAttribute("email", orderDetails.getUsername());
        request.setAttribute("orderdate", orderDetails.getOrderDate());
        request.setAttribute("shippeddate", orderDetails.getShippedDate());
        request.setAttribute("success", "Bestilling er OK og hentet fra databasen!");

        return "orderdetailspage";
    }

}
