package com.opencode.bullsandcows.controllers;

import com.opencode.bullsandcows.entities.game.Game;
import com.opencode.bullsandcows.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/save-user")
    public ResponseEntity<?> saveUser(@RequestParam String nickname){
        try{
            userService.saveUser(nickname);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<?> saveGame(@PathVariable String nickname){
        ArrayList<Game> games = userService.getUserGames(nickname);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
}
