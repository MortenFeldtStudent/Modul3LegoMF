package PresentationLayer;

import FunctionLayer.LoginUserException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommand extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException {
        String msg = "Ukendt kommando. Kontakt IT!";
        throw new LoginUserException(msg);
    }

}
