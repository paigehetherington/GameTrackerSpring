package com.theironyard;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by vajrayogini on 3/8/16.
 */
@Entity
public class Game {
    @Id //makes primary key
    @GeneratedValue //makes autoincrement
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String platform;

    @Column(nullable = false)
    String genre;

    int releaseYear; //primitive type, can't contain null

    @ManyToOne //many games to one user. many(whatever listed first) refers to class, one to user. To join user to games
    User user;

    public Game() {
    }

    public Game(String name, String platform, String genre, int releaseYear) {
        this.name = name;
        this.platform = platform;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }
}
