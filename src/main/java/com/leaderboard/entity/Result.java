package com.leaderboard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_generator")
    @SequenceGenerator(name = "result_generator", sequenceName = "result_sq", schema = "leaderboard", allocationSize = 1)
    private int id;
    private int rank;

    private BigDecimal prize;
    private BigDecimal point;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "stake_id")
    private Stake stake;

    @ManyToOne
    @JoinColumn(name = "date_id")
    private DateLB date;

    //TODO !!!!!!!!!!!!  add GameType

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public BigDecimal getPrize() {
        return prize;
    }

    public void setPrize(BigDecimal prize) {
        this.prize = prize;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Stake getStake() {
        return stake;
    }

    public void setStake(Stake stake) {
        this.stake = stake;
    }

    public DateLB getDate() {
        return date;
    }

    public void setDate(DateLB date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result that = (Result) o;
        return rank == that.rank
                && Objects.equals(prize, that.prize)
                && Objects.equals(point, that.point)
                && player.equals(that.player)
                && stake.equals(that.stake)
                && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, prize, point, player, stake, date);
    }

    @Override
    public String toString() {
        return "PlayerResult{" +
                "id=" + id +
                ", rank=" + rank +
                ", prize=" + prize +
                ", point=" + point +
                ", player=" + player +
                ", stake=" + stake +
                ", date=" + date +
                '}';
    }
}
