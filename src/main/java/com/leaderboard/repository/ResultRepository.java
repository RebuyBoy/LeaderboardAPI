package com.leaderboard.repository;

import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Result;
import com.leaderboard.entity.Stake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {

    @Query("SELECT r FROM Result r " +
            "JOIN r.date d " +
            "WHERE r.stake = ?5" +
            "AND r.provider = ?3" +
            "AND r.gameType = ?4" +
            "AND d.date BETWEEN ?1 AND ?2")
    List<Result> getResultsByDateBetween(LocalDate start, LocalDate end, Provider provider, GameType gameType, Stake stake);
    List<Result> getResultsByProviderAndGameTypeAndStake(Provider provider, GameType gameType, Stake stake);

}

