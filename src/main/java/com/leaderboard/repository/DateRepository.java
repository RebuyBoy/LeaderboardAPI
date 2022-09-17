package com.leaderboard.repository;

import com.leaderboard.entity.DateLB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DateRepository extends JpaRepository<DateLB, Integer> {

    Optional<DateLB> getByDate(LocalDate date);

}
