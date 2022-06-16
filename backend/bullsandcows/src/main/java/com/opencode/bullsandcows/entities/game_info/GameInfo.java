package com.opencode.bullsandcows.entities.game_info;

import com.opencode.bullsandcows.entities.game.Game;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "games_info")
public class GameInfo {
    @EmbeddedId
    private GameInfoKey id;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
    @Column(name = "shot")
    private String shot;

    @Column(name = "time")
    private String time;
    public GameInfo(GameInfoKey gameInfoKey, String shot, String time) {
        this.id = gameInfoKey;
        this.shot = shot;
        this.time = time;
    }
    public GameInfo(){

    }

    @Override
    public String toString() {
        return this.getShot().toString();
    }
}