package com.scmanage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scmanage.entities.User;
import com.scmanage.forms.UserForm;
import com.scmanage.helpers.Message;
import com.scmanage.helpers.MessageType;
import com.scmanage.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String goToHome() {
        return "redirect:/home";
    }
    

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler!");

        // Sending data to view
        model.addAttribute("name", "harsh");
        model.addAttribute("githybRepo", "https://github.com/harshbargude");
        model.addAttribute("Age", "20");

        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        System.out.println("About page");

        return "about";
    }

    @RequestMapping("/services")
    public String services(Model model) {
        System.out.println("About page");

        return "services";
    }

    @RequestMapping("/contact")
    public String contact() {

        return new String("contact");
    }

    // this is showing login
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }
    // do registration / process registration [This i register page]
    @RequestMapping("/register")
    public String register(Model model) {
        // defalut data bhi add karsakte hai
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }


    //  To process register 
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {   //@Valid is uset to validate using jakarka validation
        System.out.println("form processing batata");
        // fetch from from
        // User From
        // System.out.println(userForm);

        // Sava to database
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .build();
        // User SavedUser = userService.saveUser(user);
        // System.out.println("user saved" + SavedUser);

        if(rBindingResult.hasErrors()){
            return "register";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setAbout(userForm.getAbout()); // Blank if not using third-party login initially
        user.setEnabled(false); 
        userService.saveUser(user);
        System.out.println("user saved");

        // .profilePic("")
        // UserService

        Message message = Message.builder().content("Registration Successfully").type(MessageType.green).build();
        // add the message
        session.setAttribute("message", message);


        return "redirect:/register";
    }

}
