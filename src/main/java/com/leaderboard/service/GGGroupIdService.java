package com.leaderboard.service;

import com.leaderboard.entity.GroupId;
import com.leaderboard.repository.GroupIdRepository;
import com.leaderboard.service.interfaces.GroupIdService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GGGroupIdService implements GroupIdService {
    private final GroupIdRepository groupIdRepository;

    public GGGroupIdService(GroupIdRepository groupIdRepository) {
        this.groupIdRepository = groupIdRepository;
    }

    @Override
    public GroupId saveIfNotExists(GroupId groupId) {
        Optional<GroupId> optionalGroupId = getByGroupId(groupId);
        return optionalGroupId.isEmpty()
                ? groupIdRepository.save(groupId)
                : optionalGroupId.get();
    }

    @Override
    public Optional<GroupId> getByGroupId(GroupId groupId) {
        return groupIdRepository.getByDateAndPromotionGroupIdAndGameType(groupId.getDate(), groupId.getPromotionGroupId(), groupId.getGameType());
    }
}
