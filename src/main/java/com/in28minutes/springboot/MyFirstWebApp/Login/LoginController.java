package com.in28minutes.springboot.MyFirstWebApp.Login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

    private AuthenticationService authenticationService;//gives null exception so we need to use constructor injection

    public LoginController(AuthenticationService authenticationService) {
        super();
        this.authenticationService = authenticationService;
    }

    //this method handles both get and post method right now , we want it only to handle get so that we can set the
    //post method resulting in another webpage. like when we load the webpage its get method. when we click submit it post
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public static String goToLoginPage() {//@RequestParam String name, ModelMap model argument
        return "Login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String goToWelcomePage(
            @RequestParam String name, @RequestParam String password, ModelMap model) {//take un and pass put them into model and pass to jsp
        if (authenticationService.authenticate(name, password)) {
            model.put("name", name);
            model.put("password", password);
            //Authentication logic name = Ajay pass = dummy
            return "Welcome";
            //in simple way capture data, put it in model , display in view
        }
        model.put("errorMessage","Invalid credentials! please try again");
        return "Login";
    }
}
