package com.darthjaxx.sweater.controller;

import com.darthjaxx.sweater.domain.Message;
import com.darthjaxx.sweater.domain.User;
import com.darthjaxx.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    @Autowired
    MessageRepo messageRepo;

    @GetMapping(path = "/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping(path = "/main")
    public String main(Model model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Model model
    ) {
        Message message = new Message(text, tag, user);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(
            @RequestParam String filter,
            Model model
    ) {
        Iterable<Message> messages;
        if(filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.addAttribute("messages", messages);
        return "main";
    }

}
