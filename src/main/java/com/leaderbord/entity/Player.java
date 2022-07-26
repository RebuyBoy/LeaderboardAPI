package com.leaderbord.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @SequenceGenerator(name = "player_name_generator", sequenceName = "player_name_sq", schema = "leaderboard", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_name_generator")
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//TODO страны могут меняться, проверять и менять в базе и сравнивать только по имени игрока
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}

