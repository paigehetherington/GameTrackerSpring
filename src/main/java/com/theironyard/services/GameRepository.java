package com.theironyard.services;

import com.theironyard.enitities.Game;
import com.theironyard.enitities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by vajrayogini on 3/8/16.
 */
public interface GameRepository extends CrudRepository<Game, Integer> {  //methods written here to communicate just with game table -- interface!
    List<Game> findByUser(User user);
    List<Game> findByUserAndGenre(User user, String genre);  //setting query param
    List<Game> findByUserAndGenreAndReleaseYear(User user, String genre, int releaseYear);
    List<Game> findByUserAndGenreAndReleaseYearIsGreaterThanEqual(User user, String genre, int minReleaseYear); //look up in query creation table. link on slack

    Game findFirstByGenre(String genre);
    int countByGenre(String genre);
    List<Game> findByGenreOrderByNameAsc(String genre);

    @Query("SELECT g FROM Game g WHERE g.platform LIKE ?1%") //strict sql syntax, only for complex queries
    List<Game> findByPlatformStartsWith(String platform);
}
