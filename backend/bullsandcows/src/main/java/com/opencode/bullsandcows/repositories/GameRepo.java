package com.opencode.bullsandcows.repositories;

import com.opencode.bullsandcows.entities.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends JpaRepository<Game, Integer> {

}
