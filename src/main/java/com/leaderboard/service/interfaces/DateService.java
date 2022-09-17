package com.leaderboard.service.interfaces;

import com.leaderboard.entity.DateLB;

import java.time.LocalDate;
import java.util.Optional;

public interface DateService {

    DateLB createIfNotExist(DateLB date);

    Optional<DateLB> getByDate(LocalDate date);

    DateLB save(DateLB dateLB);

}
