package com.leaderbord.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "date")
@Data
public class DateLB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "date")
    @SequenceGenerator(name = "date_generator", sequenceName = "date_sq", schema = "leaderboard", allocationSize = 1)
    private int id;
    private Timestamp date;
}