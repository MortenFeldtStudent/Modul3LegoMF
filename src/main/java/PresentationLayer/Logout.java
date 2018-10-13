package PresentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends Command {

    public Logout() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {  
        request.getSession().invalidate();
        return "index";
    }
    
}
