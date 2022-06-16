package com.opencode.bullsandcows.controllers;

import com.opencode.bullsandcows.entities.exception.UserNotFoundException;
import com.opencode.bullsandcows.entities.game.Game;
import com.opencode.bullsandcows.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/save-game/{nickname}")
    public ResponseEntity<?> saveGame(@PathVariable String nickname,
                                      @RequestBody Game game){
        try{
            gameService.saveGame(nickname, game);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (UserNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/generate-number")
    public ResponseEntity<?> generateNumber(){
        String number = gameService.generateNumber();
        return new ResponseEntity<>(number, HttpStatus.OK);
    }
}