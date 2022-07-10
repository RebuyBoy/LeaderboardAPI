package com.leaderbord.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "player")
@Data
public class Player {
    @Id
    @SequenceGenerator(name = "player_name_generator", sequenceName = "player_name_sq", schema = "leaderboard", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_name_generator")
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;
}
//TODO страны могут меняться, проверять и менять в базе и сравнивать только по имени игрока