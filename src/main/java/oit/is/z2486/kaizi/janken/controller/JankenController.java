package oit.is.z2486.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import oit.is.z2486.kaizi.janken.model.Entry;

@Controller
@RequestMapping("/janken")
public class JankenController {

  @Autowired
  private Entry room;

  @GetMapping("/janken")
  public String janken() {
    return "janken"; // janken.htmlを返す
  }

  @GetMapping("/janken_gu/{hand}")
  public String janken_gu(@PathVariable("hand") String hand, ModelMap model) {
    model.addAttribute("result", "draw");
    model.addAttribute("myhand", hand);
    return "janken";
  }

  @GetMapping("/janken_choki/{hand}")
  public String janken_choki(@PathVariable("hand") String hand, ModelMap model) {
    model.addAttribute("result", "You Lose...");
    model.addAttribute("myhand", hand);
    return "janken";
  }

  @GetMapping("/janken_pa/{hand}")
  public String janken_pa(@PathVariable("hand") String hand, ModelMap model) {
    model.addAttribute("result", "You Win!");
    model.addAttribute("myhand", hand);
    return "janken";
  }

  @GetMapping()
  public String janken(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.room.addUser(loginUser);
    model.addAttribute("room", this.room);

    return "janken.html";
  }
}
