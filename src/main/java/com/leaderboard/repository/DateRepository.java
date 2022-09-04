package com.leaderboard.repository;

import com.leaderboard.entity.DateLB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DateRepository extends JpaRepository<DateLB, Integer> {
    Optional<DateLB> getByDate(LocalDate date);
}
