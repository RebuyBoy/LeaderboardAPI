package com.leaderboard.controllers;

import com.leaderboard.dto.AggregatedResultDTO;
import com.leaderboard.service.interfaces.AggregateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("results")
@Tag(name = "LeaderboardController")
public class LeaderboardController extends BaseController {

    private final AggregateService aggregateService;

    public LeaderboardController(AggregateService aggregateService) {
        this.aggregateService = aggregateService;
    }

    @GetMapping()
    @Operation(summary = "get all results")
    public List<AggregatedResultDTO> getAll() {
        return aggregateService.getAll();
    }

    @GetMapping("/date")
    @Operation(summary = "get results starting from date and ending with date if passed")
    @Parameter(example = "yyyy-MM-dd -> 2022-09-05")
    public List<AggregatedResultDTO> getAllByDate(@NotNull @RequestParam(value = "start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                  @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        return aggregateService.getAllByDate(start, end);
    }

}
