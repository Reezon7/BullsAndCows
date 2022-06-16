package com.opencode.bullsandcows.services;

import com.opencode.bullsandcows.entities.exception.UserNotFoundException;
import com.opencode.bullsandcows.entities.game.Game;
import com.opencode.bullsandcows.entities.game_info.GameInfo;
import com.opencode.bullsandcows.entities.user.User;
import com.opencode.bullsandcows.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveUser(String nickname){
        Optional<User> user = userRepo.findByNickname(nickname);

        if (user.isEmpty()){
            userRepo.save(new User(nickname));
        }
    }

    public User findUser(String nickname){
        return userRepo.findByNickname(nickname).orElseThrow(
                () -> new UserNotFoundException("User was not found!")
        );
    }

    public User deleteUser(String nickname){
        User user = userRepo.findByNickname(nickname).orElseThrow(
                () -> new UserNotFoundException("User was not found!")
        );
        userRepo.delete(user);
        return user;
    }

    public ArrayList<Game> getUserGames(String nickname){
        User user = userRepo.findByNickname(nickname).orElseThrow(
                () -> new UserNotFoundException("User was not found!")
        );
        Set<Game> games = user.getGames();

        ArrayList<Game> resultSetOfGames = new ArrayList<>();

        for (Game game : games){
            Set<GameInfo> gamesInfo = game.getGamesInfo();

            Game temp = new Game(game.getResult(),
                    game.getNumber(),
                    game.getAttempts(),
                    game.getTotalTime(),
                    game.getTimings());

            for (GameInfo gi : gamesInfo){
                temp.getSteps().add(gi.getShot());
                temp.getTimings().add(gi.getTime());
            }
            resultSetOfGames.add(temp);
        }

        return resultSetOfGames;
    }
}
