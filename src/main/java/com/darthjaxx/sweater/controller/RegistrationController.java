package com.darthjaxx.sweater.controller;

import com.darthjaxx.sweater.domain.Role;
import com.darthjaxx.sweater.domain.User;
import com.darthjaxx.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("title", "Registration");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {

        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            model.addAttribute("user", user);
            return "registration";
        } else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepo.save(user);

            return "redirect:/login";
        }

    }

}
