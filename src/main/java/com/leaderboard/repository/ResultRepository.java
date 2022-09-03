package com.leaderboard.repository;

import com.leaderboard.entity.DateLB;
import com.leaderboard.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Integer> {

    List<Result> getResultsByDateAfter(DateLB start);
    List<Result> getResultsByDateBetween(DateLB start, DateLB end);
}
