package oit.is.z2486.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Sample21Controller {

  @GetMapping("/janken")
  public String janken(@RequestParam("username") String username, Model model) {
    model.addAttribute("username", username);
    return "janken"; // janken.htmlを返す
  }
}
