package PresentationLayer;

import FunctionLayer.CreateUserException;
import FunctionLayer.LoginUserException;
import FunctionLayer.OrderException;
import FunctionLayer.OrderShipException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new LoginUser());
        commands.put("register", new CreateUser());
        commands.put("order", new Order());
        commands.put("customer", new PageGoTo());
        commands.put("showorderdetails", new OrderFromDB());
        commands.put("logout", new Logout());
        commands.put("ordertoship", new OrderToShip());
    }

    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws LoginUserException, CreateUserException, OrderException, OrderShipException, NoSuchAlgorithmException;
}
