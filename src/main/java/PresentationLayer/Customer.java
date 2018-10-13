package PresentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Customer extends Command {

    public Customer() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {        
        return "customerpage";
    }
    
}
