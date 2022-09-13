package com.leaderboard.controllers;

import com.leaderboard.dto.AggregatedResultDTO;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Provider;
import com.leaderboard.service.interfaces.AggregateService;
import com.leaderboard.service.interfaces.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("results")
@Tag(name = "LeaderboardController")
public class LeaderboardController extends BaseController {

    private final AggregateService aggregateService;
    private final ClientService clientService;

    public LeaderboardController(AggregateService aggregateService,
                                 ClientService clientService) {
        this.aggregateService = aggregateService;
        this.clientService = clientService;
    }

    @GetMapping()
    @Operation(summary = "get last year results by gameType and stake if passed")
    public List<AggregatedResultDTO> getAll(@RequestParam(value = "provider") Provider provider,
                                            @RequestParam(value = "gameType") GameType gameType) {
        return aggregateService.getAll(provider, gameType);
    }

    @GetMapping("/stake")
    @Operation(summary = "get last year results by gameType and stake if passed")
    @Parameter(example = "GGNETWORK,SHORT_DECK,stakeEquivalent: 10/5/2/1/0.5/0.25/0.1")
    public List<AggregatedResultDTO> getAllByStake(@RequestParam(value = "provider") Provider provider,
                                                   @RequestParam(value = "gameType") GameType gameType,
                                                   @RequestParam(value = "stakeEquivalent") String stakeEquivalent) {

        return aggregateService.getAllByStake(provider, gameType, stakeEquivalent);
    }

    @GetMapping("/date")
    @Operation(summary = "get results by game type,stake and starting from date and ending with date if passed or current date")
    @Parameter(example = "start(yyyy-MM-dd): 2022-09-05,GGNETWORK, SHORT_DECK, stakeEquivalent: 10/5/2/1/0.5/0.25/0.1")
    public List<AggregatedResultDTO> getAllByDate(@RequestParam(value = "start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                  @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                                  @RequestParam(value = "gameType") GameType gameType,
                                                  @RequestParam(value = "stakeEquivalent") String stakeEquivalent,
                                                  @RequestParam(value = "provider") Provider provider) {

        return aggregateService.getAllByDate(start, end, provider, gameType, stakeEquivalent);
    }

    @GetMapping("/parse")
    public void parseData(@RequestParam(value = "start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                          @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                          @RequestParam(value = "provider") Provider provider) {
        if (Objects.isNull(end)) {
            end = LocalDate.now();
        }
       //TODO provider
        clientService.runDailyDataFlow(start.datesUntil(end).collect(Collectors.toList()));
    }

}
