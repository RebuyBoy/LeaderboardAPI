package com.leaderbord.service.interfaces;

import com.leaderbord.entity.DateLB;
import com.leaderbord.entity.PlayerResult;

import java.util.List;

public interface LeaderboardCollectorService {
    List<PlayerResult> collect(DateLB date);

}
