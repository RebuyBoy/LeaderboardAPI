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

    @Query("SELECT DISTINCT r.provider FROM Result r")
    List<Provider> getDistinctByProvider();

    @Query("SELECT DISTINCT r.gameType FROM Result r WHERE r.provider = ?1 ORDER BY r.gameType")
    List<GameType> getGameTypeDistinctByProvider(Provider provider);

    @Query("SELECT DISTINCT r.stake FROM Result r WHERE r.provider = ?1 AND r.gameType = ?2")
    List<Stake> getStakeDistinctByProviderAndGameType(Provider provider, GameType gameType);

    @Query("SELECT MAX(d.date) FROM Result r JOIN r.date d WHERE r.provider = ?1")
    LocalDate getLastUpdateByProvider(Provider provider);

}

