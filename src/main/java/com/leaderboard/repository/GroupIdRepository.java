package com.leaderboard.repository;

import com.leaderboard.entity.GameType;
import com.leaderboard.entity.GroupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface GroupIdRepository extends JpaRepository<GroupId,Integer> {

    Optional<GroupId> getByDateAndPromotionGroupIdAndGameType(LocalDate date, String groupId, GameType gameType);

}
