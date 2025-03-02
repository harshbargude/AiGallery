package com.aigallery.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aigallery.entities.User;
import com.aigallery.forms.LoginForm;
import com.aigallery.forms.UserForm;
import com.aigallery.helpers.Message;
import com.aigallery.helpers.MessageType;
import com.aigallery.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PageController {

    private UserService userService;

    public PageController(UserService theUserService){
        this.userService = theUserService;
    }

    @GetMapping("/")
    public String goToHome() {
        return "redirect:/home";
    }
    

    @RequestMapping("/home")
    public String home(Model model) {
        // System.out.println("Home page handler!");

        // Sending data to view
        model.addAttribute("name", "harsh");
        model.addAttribute("githybRepo", "https://github.com/harshbargude");
        model.addAttribute("Age", "20");

        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        // System.out.println("About page");

        return "about";
    }

    @RequestMapping("/services")
    public String services(Model model) {
        // System.out.println("About page");

        return "services";
    }

    @RequestMapping("/contact")
    public String contact() {

        return new String("contact");
    }

    // this is showing login
    @GetMapping("/login")
    public String login(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }
    // do registration / process registration [This i register page]
    @RequestMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @RequestMapping(value = "/register-user", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute UserForm userForm, BindingResult theBindingResult, HttpSession httpSession) {

        // here you can process the form data
        if(theBindingResult.hasErrors()){
            System.out.println(theBindingResult.toString());
            theBindingResult.toString();
            return "/register";
        }


        // i stored the comming model from register form in this object
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .enabled(false)
        // .provider(Providers.SELF)
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setEnabled(false);

        userService.saveUser(user);

        // add message
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        httpSession.setAttribute("message", message);
        System.out.println("UserForm data: " + userForm.toString());
        return "redirect:/login";
    }

    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute LoginForm loginForm,BindingResult theBindingResult, HttpSession httpSession) {
        if(theBindingResult.hasErrors()){
            System.out.println(theBindingResult.toString());
            theBindingResult.toString();
            return "/login";
        }
        Message message;
        ResponseEntity<User> user = userService.getUserByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        
        if(user.getBody() == null){
            message = Message.builder().content("Invalid Credentials").type(MessageType.red).build();
            httpSession.setAttribute("message", message);
            return "redirect:/login";
        }
        System.out.println(loginForm.toString());
        System.out.println("FOUND USER EMAIL: " + user.getBody().getEmail());
        return "login";
    }
    
}
