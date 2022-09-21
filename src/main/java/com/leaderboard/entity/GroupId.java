package com.leaderboard.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class GroupId {

    @Id
    @SequenceGenerator(name = "group_id_generator", sequenceName = "group_id_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_generator")
    private int id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private GameType gameType;
    private String promotionGroupId;

    private GroupId(Builder builder) {
        this.date = builder.date;
        this.gameType = builder.gameType;
        this.promotionGroupId = builder.promotionGroupId;
    }

    public GroupId() {
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPromotionGroupId() {
        return promotionGroupId;
    }

    public GameType getGameType() {
        return gameType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupId groupId1 = (GroupId) o;
        return promotionGroupId.equals(groupId1.promotionGroupId) && date.equals(groupId1.date) && gameType.equals(groupId1.gameType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, promotionGroupId);
    }

    @Override
    public String toString() {
        return "GroupId{" +
                "id=" + id +
                ", date=" + date +
                ", gameType=" + gameType +
                ", promotionGroupId='" + promotionGroupId + '\'' +
                '}';
    }

    public static final class Builder {

        private LocalDate date;
        private GameType gameType;
        private String promotionGroupId;

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder gameType(GameType gameType) {
            this.gameType = gameType;
            return this;
        }

        public Builder promotionGroupId(String promotionGroupId) {
            this.promotionGroupId = promotionGroupId;
            return this;
        }

        public GroupId build() {
            return new GroupId(this);
        }

    }

}
