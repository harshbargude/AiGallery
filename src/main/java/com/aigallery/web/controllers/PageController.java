package com.aigallery.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.aigallery.services.UserService;
import com.aigallery.web.dto.forms.UserForm;


import org.springframework.web.bind.annotation.GetMapping;


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

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }



    // @PostMapping("/authenticate")
    // public String authenticate(@ModelAttribute LoginForm loginForm,BindingResult theBindingResult, HttpSession httpSession) {
    //     if(theBindingResult.hasErrors()){
    //         System.out.println(theBindingResult.toString());
    //         theBindingResult.toString();
    //         return "/login";
    //     }
    //     Message message;
    //     ResponseEntity<User> user = userService.getUserByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        
    //     if(user.getBody() == null){
    //         message = Message.builder().content("Invalid Credentials").type(MessageType.red).build();
    //         httpSession.setAttribute("message", message);
    //         return "redirect:/login";
    //     }
    //     System.out.println(loginForm.toString());
    //     System.out.println("FOUND USER EMAIL: " + user.getBody().getEmail());
    //     return "login";
    // }
    
}
