package com.leaderboard.controllers;

import com.leaderboard.dto.api.response.ResultResponse;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Stake;
import com.leaderboard.service.interfaces.AggregateService;
import com.leaderboard.service.interfaces.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("v1/results")
@Tag(name = "AggregateResultController")
@CrossOrigin()
public class AggregatedResultController implements BaseController {

    private final AggregateService aggregateService;
    private final ClientService clientService;

    public AggregatedResultController(AggregateService aggregateService,
                                      ClientService clientService) {
        this.aggregateService = aggregateService;
        this.clientService = clientService;
    }

    @GetMapping("/stake")
    @Operation(summary = "get last year results by provider, gameType and stake if passed")
    public ResultResponse getAllByStake(@RequestParam Provider provider,
                                        @RequestParam GameType gameType,
                                        @RequestParam(required = false) Stake stake) {
        return aggregateService.getAllByStake(provider, gameType, stake);
    }

    @GetMapping("/date")
    @Operation(summary = "get results by date from start to end if passed or current date if not, by stake or last year if stake not passed")
    @Parameter(example = "start(yyyy-MM-dd): 2022-09-05, end : 2022-09-05")
    public ResultResponse getAllByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                       @RequestParam Provider provider,
                                       @RequestParam GameType gameType,
                                       @RequestParam(required = false) Stake stake) {

        return aggregateService.getAllByDate(start, end, provider, gameType, stake);
    }

    @GetMapping("/parse")
    @Operation(summary = "parse results by date from start to end if passed or current date if not")
    @Parameter(example = "start(yyyy-MM-dd): 2022-09-05, end : 2022-09-05")
    public void parseData(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                          @RequestParam Provider provider,
                          @RequestParam GameType gameType) {
        if (Objects.isNull(end)) {
            end = LocalDate.now();
        }
        clientService.runDailyDataFlow(start.datesUntil(end).toList(),gameType);
    }

}
