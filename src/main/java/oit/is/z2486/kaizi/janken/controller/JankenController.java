package oit.is.z2486.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z2486.kaizi.janken.model.User;
import oit.is.z2486.kaizi.janken.model.UserMapper;
import oit.is.z2486.kaizi.janken.model.Match;
import oit.is.z2486.kaizi.janken.model.MatchMapper;

import java.util.ArrayList;

@Controller
public class JankenController {

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  @GetMapping("/janken")
  public String janken(ModelMap model) {
    ArrayList<User> users = this.userMapper.selectAllByUser();
    model.addAttribute("users", users);
    ArrayList<Match> matches = this.matchMapper.selectAllByMatch();
    model.addAttribute("matches", matches);
    return "janken"; // janken.htmlを返す
  }

  @GetMapping("/fight_gu")
  public String janken_gu(@RequestParam("hand") String yourhand, @RequestParam("opponentName") String opppnentName, @RequestParam("opponentId") int opponentId, ModelMap model,
      Principal prin) {
    String loginUser = prin.getName();
    User user = this.userMapper.selectByName(loginUser);
    User opponent = this.userMapper.selectByName(opppnentName);
    model.addAttribute("loginUser", loginUser);
    model.addAttribute("opponent", opponent);

    // データベースに試合結果を挿入
    try {
      matchMapper.insertMatch(user.getId(), opponentId, yourhand, "Gu");
      System.out.println("Inserting match with userId: " + user.getId() + ", opponentId: " + opponentId + ", myHand: "
          + yourhand + ", cpuHand: " + "Gu");

      System.out.println("Match inserted successfully.");
    } catch (Exception e) {
      System.err.println("Error inserting match: " + e.getMessage());
      // 必要に応じてエラーメッセージをモデルに追加して表示することも可能
      model.addAttribute("errorMessage", "試合結果の保存に失敗しました。");
    }

    model.addAttribute("result", "draw");
    model.addAttribute("myhand", yourhand);
    return "wait";
  }

  @GetMapping("/fight_choki")
  public String janken_choki(@RequestParam("hand") String yourhand,  @RequestParam("opponentName") String opppnentName, @RequestParam("opponentId") int opponentId, ModelMap model,
      Principal prin) {
    String loginUser = prin.getName();
    User user = this.userMapper.selectByName(loginUser);
    User opponent = this.userMapper.selectByName(opppnentName);
    model.addAttribute("loginUser", loginUser);
    model.addAttribute("opponent", opponent);

    try {
      matchMapper.insertMatch(user.getId(), opponentId, yourhand, "Choki");
      System.out.println("Inserting match with userId: " + user.getId() + ", opponentId: " + opponentId + ", myHand: "
          + yourhand + ", cpuHand: " + "Choki");

      System.out.println("Match inserted successfully.");
    } catch (Exception e) {
      System.err.println("Error inserting match: " + e.getMessage());
      // 必要に応じてエラーメッセージをモデルに追加して表示することも可能
      model.addAttribute("errorMessage", "試合結果の保存に失敗しました。");
    }

    model.addAttribute("result", "You Lose...");
    model.addAttribute("myhand", yourhand);
    return "match";
  }

  @GetMapping("/fight_pa")
  public String janken_pa(@RequestParam("hand") String yourhand,  @RequestParam("opponentName") String opppnentName, @RequestParam("opponentId") int opponentId,
      ModelMap model,
      Principal prin) {
    String loginUser = prin.getName();
    User user = this.userMapper.selectByName(loginUser);
    User opponent = this.userMapper.selectByName(opppnentName);
    model.addAttribute("loginUser", loginUser);
    model.addAttribute("opponent", opponent);

    try {
      matchMapper.insertMatch(user.getId(), opponentId, yourhand, "Pa");
      System.out.println("Inserting match with userId: " + user.getId() + ", opponentId: " + opponentId + ", myHand: "
          + yourhand + ", cpuHand: " + "Pa");

      System.out.println("Match inserted successfully.");
    } catch (Exception e) {
      System.err.println("Error inserting match: " + e.getMessage());
      // 必要に応じてエラーメッセージをモデルに追加して表示することも可能
      model.addAttribute("errorMessage", "試合結果の保存に失敗しました。");
    }

    model.addAttribute("result", "You Win!");
    model.addAttribute("myhand", yourhand);
    return "match";
  }



  @GetMapping("/match")
  public String match(ModelMap model, @RequestParam("id") int id, Principal prin) {
    String loginUser = prin.getName();
    User opponent = this.userMapper.selectById(id);
    model.addAttribute("loginUser", loginUser);
    model.addAttribute("opponent", opponent);
    return "match";
  }
}
