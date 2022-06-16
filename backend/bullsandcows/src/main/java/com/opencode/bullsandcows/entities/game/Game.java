package com.opencode.bullsandcows.entities.game;

import com.opencode.bullsandcows.entities.game_info.GameInfo;
import com.opencode.bullsandcows.entities.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "result")
    private String result;

    @Column(name = "number")
    private String number;

    @Column(name = "total_time")
    private String totalTime;

    @Column(name = "attempts")
    private int attempts;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Transient
    private ArrayList<String> steps = new ArrayList<>();

    @Transient
    private ArrayList<String> timings = new ArrayList<>();

    @OneToMany(
            mappedBy = "game",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Set<GameInfo> gamesInfo;

    public Game() {
    }

    public Game(String result, String number, int attempts,
                String totalTime, ArrayList<String> timings) {
        this.result = result;
        this.number = number;
        this.attempts = attempts;
        this.totalTime = totalTime;
        this.timings = timings;
    }
}
