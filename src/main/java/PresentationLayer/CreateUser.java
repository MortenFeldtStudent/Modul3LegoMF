package PresentationLayer;

import FunctionLayer.CreateUserException;
import FunctionLayer.LogicFacade;
import FunctionLayer.User;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateUser extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws CreateUserException, NoSuchAlgorithmException {
        String email = request.getParameter( "email" );
        String password1 = request.getParameter( "password1" );
        String password2 = request.getParameter( "password2" );
        if ( password1.equals( password2 ) ) {
            User user = LogicFacade.createUser( email, password1 );
            HttpSession session = request.getSession();
            session.setAttribute( "user", user );
            session.setAttribute( "role", user.getRole() );
            return user.getRole() + "page";
        } else {
            throw new CreateUserException("Passwords er ikke ens!" );
        }
    }

}
