package com.leaderbord.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "date")
public class DateLB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "date")
    @SequenceGenerator(name = "date_generator", sequenceName = "date_sq", schema = "leaderboard", allocationSize = 1)
    private int id;
    private Timestamp timestamp;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp date) {
        this.timestamp = date;
    }
}