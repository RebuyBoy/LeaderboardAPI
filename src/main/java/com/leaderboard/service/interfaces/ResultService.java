package com.leaderboard.service.interfaces;

import com.leaderboard.entity.Result;

import java.time.LocalDate;
import java.util.List;

public interface ResultService {

    List<Result> getByDateFrom(LocalDate from);
    List<Result> getByDateBetween(LocalDate from, LocalDate to);
    List<Result> getAll();

    void save(Result result);

}
