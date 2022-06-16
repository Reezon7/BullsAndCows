package com.opencode.bullsandcows.services;

import com.opencode.bullsandcows.entities.exception.GameNotFoundException;
import com.opencode.bullsandcows.entities.game.Game;
import com.opencode.bullsandcows.entities.game_info.GameInfo;
import com.opencode.bullsandcows.entities.game_info.GameInfoKey;
import com.opencode.bullsandcows.entities.user.User;
import com.opencode.bullsandcows.repositories.GameInfoRepo;
import com.opencode.bullsandcows.repositories.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@Service
public class GameService {
    @Value("${game.difficulty}")
    private String gameDifficulty;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private GameInfoRepo gameInfoRepo;

    public void saveGame(String nickname, Game game) {
        try {
            User user = userService.findUser(nickname);
            game.setUser(user);

            ArrayList<String> steps = game.getSteps();
            ArrayList<String> timings = game.getTimings();

            gameRepo.save(game);


            for (int i = 0; i < steps.size(); i++) {
                GameInfo gameInfo = new GameInfo(
                        new GameInfoKey(game.getId(), i + 1),
                        steps.get(i), timings.get(i)
                );

                gameInfo.setGame(game);
                gameInfoRepo.save(gameInfo);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Game getGame(int id) {
        Optional<Game> game = gameRepo.findById(id);
        return game.orElse(null);
    }

    public Game deleteGame(int id) {
        Game game = gameRepo.findById(id).orElseThrow(
                () -> new GameNotFoundException("Game was not found!"));
        gameRepo.delete(game);
        return game;
    }

    public void updateGame(Game game) {
        gameRepo.save(game);
    }

    public String generateNumber() {

        ArrayList<Integer> digits = new ArrayList<>();
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Arrays.stream(array).forEach(digits::add);
        StringBuilder number = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(digits.size());
            number.append(digits.get(randomIndex));
            digits.remove(randomIndex);
            if (i == 0) digits.add(0);
        }
        number.append(this.gameDifficulty);
        return number.toString();
    }
}
