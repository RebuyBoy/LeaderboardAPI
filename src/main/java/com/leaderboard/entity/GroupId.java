package com.leaderboard.entity;

import javax.persistence.Entity;
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
    private String promotionGroupId;

    public GroupId(LocalDate date, String promotionGroupId) {
        this.date = date;
        this.promotionGroupId = promotionGroupId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupId groupId1 = (GroupId) o;
        return promotionGroupId.equals(groupId1.promotionGroupId) && date.equals(groupId1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, promotionGroupId);
    }

    @Override
    public String toString() {
        return "GroupId{" +
                "date=" + date +
                ", promotionGroupId=" + promotionGroupId +
                '}';
    }
}
