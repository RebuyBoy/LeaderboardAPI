package com.leaderboard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "leaderboard_date")
public class DateLB {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "date_generator")
    @SequenceGenerator(name = "date_generator", sequenceName = "date_sq", allocationSize = 1)
    private int id;
    private LocalDate date;

    public DateLB() {
    }

    public DateLB(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setTimestamp(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DateLB{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }

}