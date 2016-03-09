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
        public String home(Model model, String genre, Integer releaseYear, String platform)  {
        if (platform != null) {
            model.addAttribute("games", games.findByPlatformStartsWith(platform));
        }

        else if (genre != null && releaseYear != null) {
            model.addAttribute("games", games.findByGenreAndReleaseYearIsGreaterThanEqual(genre, releaseYear));  //select

        }
        else if (genre != null) {
            model.addAttribute("games", games.findByGenre(genre));
        }
        else {
            model.addAttribute("games", games.findAll());
        }

        return "home";
    }

    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(String gameName, String gamePlatform, String gameGenre, int gameYear) { //match html
       Game game = new Game(gameName, gamePlatform, gameGenre, gameYear);
        //insert in table by creating respository -->new java class, select interface
        games.save(game);
        // to edit: games.findOne();
        //to delete: games.delete
        return "redirect:/";

    }




}
