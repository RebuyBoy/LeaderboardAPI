package com.leaderboard.controllers;

import com.leaderboard.dto.AggregatedResultDTO;
import com.leaderboard.entity.Country;
import com.leaderboard.service.interfaces.AggregateService;
import com.leaderboard.service.interfaces.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("lb")
@Tag(name = "LeaderboardController")
public class LeaderboardController {
    private final CountryService countryService;
    private final AggregateService aggregateService;

    public LeaderboardController(CountryService countryService,
                                 AggregateService aggregateService) {
        this.countryService = countryService;
        this.aggregateService = aggregateService;
    }

    @GetMapping("/results")
    @Operation(summary = "get all results")
    public List<AggregatedResultDTO> getAll() {
        return aggregateService.getAll();
    }

    @GetMapping("/results/dates")
    @Operation(summary = "get results starting from date and ending with date if passed")
    @Parameter(example = "yyyy-MM-dd -> 2022-09-05")
    public List<AggregatedResultDTO> getAllByDate(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        //TODO  APi format
        return aggregateService.getAllByDate(start, end);
    }

    @GetMapping("/country/{id}")
    @Operation(summary = "get country by its code")
    public String getCode(@PathVariable("id") String id) {
        Country byCode = countryService.getByCode(id);
        if (byCode == null) {
            return "wrong code";
        }
        return byCode.getName();
    }

}
