package com.in28minutes.springboot.MyFirstWebApp.Hello;
//this class should be in a subpackage where the application class is present

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//we must specify one or the other annotation like component or controoler to say that this
//class handles web request
@Controller//if we dont spefiy spring about this bean then whitelabel error page is shown
public class SayHelloController {
    //we want the response to be hello! what are you learning today? when someone
    //hits on to "say-hello" url
    @RequestMapping("say-hello")
    @ResponseBody//this just returns the message body below as it is, else spring wont
    //by default what string does is it will look for a view with string name and does not return the string as it is
    public String sayHello(){
        return "hello! what are you learning today?";
    }
    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml(){//lets hardcode some html
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title> my first html page </title>");
        sb.append("<head> my first html page with body </body>");
        sb.append("<body>");
        sb.append("<html>");
        return sb.toString();
    }
    @RequestMapping("say-hello-jsp")//remove response body for view
    public String sayHelloJsp(){
        return "SayHello";//this is name of the jsp file
    }
    //note that also u need jasper dependency in pom.xml
    //its getting complicated so we ll make use of views so that whenever the url is hit it should
    //see sayHello.jsp (jsp-Java server pages is popular even tday)
}
