package com.leaderboard.service.interfaces;

import com.leaderboard.entity.GroupId;

import java.util.Optional;

public interface GroupIdService {

    GroupId saveIfNotExists(GroupId groupId);

    Optional<GroupId> getByGroupId(GroupId groupId);

}
