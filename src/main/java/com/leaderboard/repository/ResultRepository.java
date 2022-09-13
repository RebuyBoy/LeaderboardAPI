package com.leaderboard.repository;

import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Result;
import com.leaderboard.entity.Stake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Integer> {

    @Query("SELECT r FROM Result r JOIN r.date d WHERE d.date BETWEEN ?1 and ?2")
    List<Result> getResultsByDateBetween(LocalDate start, LocalDate end);
    List<Result> getResultsByProviderAndGameType(Provider provider,GameType gameType);
    List<Result> getResultsByProviderAndGameTypeAndStake(Provider provider, GameType gameType, Stake stake);

}

