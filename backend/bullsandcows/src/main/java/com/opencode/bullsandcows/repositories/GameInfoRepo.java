package com.opencode.bullsandcows.repositories;

import com.opencode.bullsandcows.entities.game_info.GameInfo;
import com.opencode.bullsandcows.entities.game_info.GameInfoKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameInfoRepo extends JpaRepository<GameInfo, GameInfoKey> {

}