package oit.is.z2486.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JankenController {

  @GetMapping("/janken")
  public String janken(@RequestParam("username") String username, Model model) {
    model.addAttribute("username", username);
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


}
