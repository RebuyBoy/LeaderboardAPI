package com.leaderbord.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "stake")
@Data
public class Stake {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "date")
    @SequenceGenerator(name = "date_generator", sequenceName = "date_sq", schema = "leaderboard", allocationSize = 1)
    private int id;
    private BigDecimal stake;
}