package com.leaderboard.repository;

import com.leaderboard.entity.DateLB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Optional;

public interface DateRepository extends JpaRepository<DateLB, Integer> {
    Optional<DateLB> getByTimestamp(Timestamp timestamp);
}
