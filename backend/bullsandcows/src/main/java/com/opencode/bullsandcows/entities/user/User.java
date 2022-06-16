package com.opencode.bullsandcows.entities.user;

import com.opencode.bullsandcows.entities.game.Game;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nickname")
    private String nickname;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    private Set<Game> games;

    public User() {
    }

    public User(String nickname) {
        this.nickname = nickname;
    }


}