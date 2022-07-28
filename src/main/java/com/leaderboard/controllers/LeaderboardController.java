package com.leaderboard.controllers;

import com.leaderboard.entity.Country;
import com.leaderboard.entity.Result;
import com.leaderboard.service.interfaces.CountryService;
import com.leaderboard.service.interfaces.ResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("lb")
@Api
public class LeaderboardController {
    private final CountryService countryService;
    private final ResultService resultService;

    public LeaderboardController(CountryService countryService
            , ResultService resultService) {
        this.countryService = countryService;
        this.resultService = resultService;
    }

    @GetMapping("/{code}")
    @ResponseBody
    @ApiOperation("get country name by its code")
    public Country get(@PathVariable String code) {
        return countryService.getByCode(code);
    }

    @GetMapping("/results")
    @ResponseBody
    @ApiOperation("get all results")
    public List<Result> getAll() {
        return resultService.getAll();
    }
}
