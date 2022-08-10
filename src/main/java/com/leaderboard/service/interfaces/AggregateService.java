package com.leaderboard.service.interfaces;

import com.leaderboard.dto.AggregatedResultDTO;

import java.time.LocalDate;
import java.util.List;

public interface AggregateService {
    List<AggregatedResultDTO> getAll();
    List<AggregatedResultDTO> getAllByName(String name);
    List<AggregatedResultDTO> getAllByDate(LocalDate start, LocalDate end);
}
