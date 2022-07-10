package com.leaderbord.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "player_result")
public class PlayerResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rank;

    private BigDecimal prize;
    private BigDecimal point;

    @ManyToOne
    @JoinColumn(name = "player_ID")
    private Player playerName;

    @ManyToOne
    @JoinColumn(name = "stake_ID")
    private Stake stake;

    @ManyToOne
    @JoinColumn(name = "date_ID")
    private DateLB date;
}
