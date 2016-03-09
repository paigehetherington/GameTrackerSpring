package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by vajrayogini on 3/8/16.
 */
@Controller
public class GameTrackerController {
    @Autowired //spring automatically creates repository that has bunch of methods for communicating with table
    GameRepository games;

    @Autowired
    UserRepository users;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, String genre, Integer releaseYear, String platform)  {
        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByName(userName);
        if (user != null) {
            model.addAttribute("user", user);
        }

        if (platform != null) {
            model.addAttribute("games", games.findByPlatformStartsWith(platform));
        }

        else if (genre != null && releaseYear != null) {
            model.addAttribute("games", games.findByUserAndGenreAndReleaseYearIsGreaterThanEqual(user, genre, releaseYear));  //select

        }
        else if (genre != null) {
            model.addAttribute("games", games.findByUserAndGenre(user, genre));
        }
        else {
            model.addAttribute("games", games.findByUser(user));
        }

        return "home";
    }

    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(HttpSession session, String gameName, String gamePlatform, String gameGenre, int gameYear) { //match html
       Game game = new Game(gameName, gamePlatform, gameGenre, gameYear);
        //insert in table by creating respository -->new java class, select interface
        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByName(userName);
        game.user = user;
        games.save(game);
        // to edit: games.findOne();
        //to delete: games.delete
        return "redirect:/";

    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        User user = users.findFirstByName(userName);
        if (user == null) {
            user = new User(userName);
            users.save(user);
        }
        session.setAttribute("userName", userName);
        return "redirect:/";

    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession sesion) {
        sesion.invalidate();
        return "redirect:/";
    }





}
