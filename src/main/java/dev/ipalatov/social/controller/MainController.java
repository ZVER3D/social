package dev.ipalatov.social.controller;

import dev.ipalatov.social.domain.User;
import dev.ipalatov.social.repo.MessageRepo;
import java.util.HashMap;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
  private final MessageRepo messageRepo;

  public MainController(MessageRepo messageRepo) {
    this.messageRepo = messageRepo;
  }

  @GetMapping
  public String index(@AuthenticationPrincipal User user, Model model) {
    HashMap<Object, Object> data = new HashMap<>();

    data.put("profile", user);
    data.put("messages", messageRepo.findAll());

    model.addAttribute("frontendData", data);

    return "index";
  }
}
