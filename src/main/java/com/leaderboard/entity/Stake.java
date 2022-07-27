package com.leaderboard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "stake")
public class Stake {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stake_generator")
    @SequenceGenerator(name = "stake_generator", sequenceName = "stake_sq", schema = "leaderboard", allocationSize = 1)
    private int id;
    private BigDecimal stakeEquivalent;

    public Stake() {
    }

    public Stake(BigDecimal stakeEquivalent) {
        this.stakeEquivalent = stakeEquivalent;
    }

    public BigDecimal getStakeEquivalent() {
        return stakeEquivalent;
    }

    public void setStakeEquivalent(BigDecimal stake) {
        this.stakeEquivalent = stake;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stake stake1 = (Stake) o;
        return Objects.equals(stakeEquivalent, stake1.stakeEquivalent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stakeEquivalent);
    }

    @Override
    public String toString() {
        return "Stake{" +
                "id=" + id +
                ", stake=" + stakeEquivalent +
                '}';
    }
}