package com.opencode.bullsandcows.entities.game_info;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Getter
@Setter
@Embeddable
public class GameInfoKey implements Serializable {
    private int gameId;
    private int step;

    public GameInfoKey(int gameId, int step) {
        this.gameId = gameId;
        this.step = step;
    }

    public GameInfoKey() {
    }
}