package com.leaderboard.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_generator")
    @SequenceGenerator(name = "result_generator", sequenceName = "result_sq", allocationSize = 1)
    private int id;
    private int rank;
    private BigDecimal prize;
    private BigDecimal point;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stake_id")
    private Stake stake;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "date_id")
    private DateLB date;

    private GameType gameType;

    public Result() {
    }

    private Result(Builder builder) {
        this.id = builder.id;
        this.rank = builder.rank;
        this.prize = builder.prize;
        this.point = builder.point;
        this.player = builder.player;
        this.stake = builder.stake;
        this.date = builder.date;
        this.gameType = builder.gameType;
    }

    public int getId() {
        return id;
    }

    public int getRank() {
        return rank;
    }

    public BigDecimal getPrize() {
        return prize;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public Player getPlayer() {
        return player;
    }

    public Stake getStake() {
        return stake;
    }

    public DateLB getDate() {
        return date;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setPrize(BigDecimal prize) {
        this.prize = prize;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setStake(Stake stake) {
        this.stake = stake;
    }

    public void setDate(DateLB date) {
        this.date = date;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
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

    public static final class Builder {
        private int id;
        private int rank;
        private BigDecimal prize;
        private BigDecimal point;
        private Player player;
        private Stake stake;
        private DateLB date;
        private GameType gameType;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder rank(int rank) {
            this.rank = rank;
            return this;
        }

        public Builder prize(BigDecimal prize) {
            this.prize = prize;
            return this;
        }

        public Builder point(BigDecimal point) {
            this.point = point;
            return this;
        }

        public Builder player(Player player) {
            this.player = player;
            return this;
        }

        public Builder stake(Stake stake) {
            this.stake = stake;
            return this;
        }

        public Builder date(DateLB date) {
            this.date = date;
            return this;
        }

        public Builder gameType(GameType gameType) {
            this.gameType = gameType;
            return this;
        }

        public Result build() {
            //TODO validation??
            return new Result(this);
        }

    }

}
