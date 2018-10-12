package PresentationLayer;

import FunctionLayer.LoginUserException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 The purpose of UnknownCommand is to...

 @author kasper
 */
public class UnknownCommand extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginUserException {
        String msg = "Ukendt command. Kontakt IT!";
        throw new LoginUserException( msg );
    }

}
