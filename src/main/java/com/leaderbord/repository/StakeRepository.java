package com.leaderbord.repository;

import com.leaderbord.entity.Stake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StakeRepository extends JpaRepository<Stake,Integer> {
}
