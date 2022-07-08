package com.leaderbord.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@RequiredArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "Player")
public class PlayerResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rank;

    private BigDecimal prize;
    private BigDecimal point;

    @ManyToOne
    @JoinColumn(name = "player_name_ID")
    private Player playerName;

    @ManyToOne
    @JoinColumn(name = "stake_ID")
    private Stake stake;

    @ManyToOne
    @JoinColumn(name = "date_ID")
    private DateLB date;

    @ManyToOne
    @JoinColumn(name = "country_ID")
    private Country country;

}
