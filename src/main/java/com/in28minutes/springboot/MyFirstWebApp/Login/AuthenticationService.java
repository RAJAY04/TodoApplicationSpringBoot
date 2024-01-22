package com.in28minutes.springboot.MyFirstWebApp.Login;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service//as it contains buisness logic and we want spring to manage this , (Bean) by creating its instance automatically
public class AuthenticationService {
    //we can actually write this logic in login controller itself but its not a good practice
    //in procuction each class should be handling 1 responsibility(just adhere to single responsibility principle)
    public boolean authenticate(String username,String password){
        boolean isValidUserName = username.equalsIgnoreCase("Ajay");
        boolean isValidPassowrd = password.equalsIgnoreCase("dummy");
        return isValidUserName && isValidPassowrd;
    }

}
