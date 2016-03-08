package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by vajrayogini on 3/8/16.
 */
@Controller
public class GameTrackerController {
    @Autowired //spring automatically creates repository that has bunch of methods for communicating with table
    GameRepository games;


    @RequestMapping(path = "/", method = RequestMethod.GET)
        public String home(Model model)  {
        model.addAttribute("games", games.findAll());  //select
        return "home";
    }

    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(String gameName, String gamePlatform, int gameYear) { //match html
       Game game = new Game(gameName, gamePlatform, gameYear);
        //insert in table by creating respository -->new java class, select interface
        games.save(game);
        // to edit: games.findOne();
        //to delete: games.delete
        return "redirect:/";



    }


}
