package com.leaderbord.service.interfaces;


import com.leaderbord.dto.GGPlayerDTO;
import com.leaderbord.entity.PlayerResult;

import java.util.List;

public interface PlayerResultService {
    void addAll(List<GGPlayerDTO> players);

    void saveAll(List<PlayerResult> collect);
}
