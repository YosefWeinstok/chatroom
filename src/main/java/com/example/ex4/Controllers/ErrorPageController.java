package com.example.ex4.Controllers;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Error controller
 */
@Controller
public class ErrorPageController implements ErrorController {
    /**
     * Handles the error mapping
     * @return errorPage.html
     */
    @RequestMapping("/error")
    public String errorPage(){
        return "errorPage";
    }
}
