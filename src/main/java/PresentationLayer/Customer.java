/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author morte
 */
public class Customer extends Command {

    public Customer() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {        
        return "customerpage";
    }
    
}