package PresentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends Command {

    public Logout() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {  
        HttpSession session = request.getSession();
        session.invalidate();
        return "index";
    }
    
}
