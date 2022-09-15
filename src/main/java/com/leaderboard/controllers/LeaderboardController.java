package com.leaderboard.controllers;

import com.leaderboard.dto.AggregatedResult;
import com.leaderboard.dto.response.ResultResponse;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Stake;
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
public class LeaderboardController implements BaseController {

    private final AggregateService aggregateService;
    private final ClientService clientService;

    public LeaderboardController(AggregateService aggregateService,
                                 ClientService clientService) {
        this.aggregateService = aggregateService;
        this.clientService = clientService;
    }

    @GetMapping("/stake")
    @Operation(summary = "get last year results by provider, gameType and stake if passed")
    public ResultResponse getAllByStake(@RequestParam(value = "provider") Provider provider,
                                                   @RequestParam(value = "gameType") GameType gameType,
                                                   @RequestParam(value = "stake", required = false) Stake stake) {
        List<AggregatedResult> aggregatedResults = aggregateService.getAllByStake(provider, gameType, stake);
        return new ResultResponse(provider.name(), stake.getCurrency(), aggregatedResults);
    }

    @GetMapping("/date")
    @Operation(summary = "get results by date from start to end if passed or current date if not, by stake or last year if stake not passed")
    @Parameter(example = "start(yyyy-MM-dd): 2022-09-05, end : 2022-09-05")
    public ResultResponse getAllByDate(@RequestParam(value = "start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                  @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                                  @RequestParam(value = "provider") Provider provider,
                                                  @RequestParam(value = "gameType") GameType gameType,
                                                  @RequestParam(value = "stake", required = false) Stake stake) {

        List<AggregatedResult> aggregatedResults = aggregateService.getAllByDate(start, end, provider, gameType, stake);
        return new ResultResponse(provider.name(), stake.getCurrency(), aggregatedResults);
    }

    @GetMapping("/parse")
    @Operation(summary = "parse results by date from start to end if passed or current date if not")
    @Parameter(example = "start(yyyy-MM-dd): 2022-09-05, end : 2022-09-05")
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
