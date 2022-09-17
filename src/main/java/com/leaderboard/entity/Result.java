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
    private BigDecimal points;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;
    private Stake stake;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "date_id")
    private DateLB date;
    private GameType gameType;
    private Provider provider;

    public Result() {
    }

    private Result(Builder builder) {
        this.id = builder.id;
        this.rank = builder.rank;
        this.prize = builder.prize;
        this.points = builder.point;
        this.player = builder.player;
        this.stake = builder.stake;
        this.date = builder.date;
        this.gameType = builder.gameType;
        this.provider = builder.provider;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal point) {
        this.points = point;
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

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result that = (Result) o;
        return rank == that.rank
                && Objects.equals(prize, that.prize)
                && Objects.equals(points, that.points)
                && player.equals(that.player)
                && stake.equals(that.stake)
                && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, prize, points, player, stake, date);
    }

    @Override
    public String toString() {
        return "PlayerResult{" +
                "id=" + id +
                ", rank=" + rank +
                ", prize=" + prize +
                ", point=" + points +
                ", player=" + player +
                ", stake=" + stake +
                ", date=" + date +
                '}';
    }

    public static final class Builder {

        private Provider provider;
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

        public Builder provider(Provider provider) {
            this.provider = provider;
            return this;
        }

        public Result build() {
            //TODO validation??
            return new Result(this);
        }

    }

}
