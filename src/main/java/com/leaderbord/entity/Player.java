package com.leaderbord.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "player_name")
@Data
public class Player {
    @Id
    @SequenceGenerator(name = "player_name_generator", sequenceName = "player_name_sq", schema = "leaderboard", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_name_generator")
    private int id;
    @JsonProperty("nickname")
    private String name;
}