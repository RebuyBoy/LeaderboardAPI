package com.leaderboard.service.interfaces;

import com.leaderboard.entity.DateLB;

import java.sql.Timestamp;
import java.util.Optional;

public interface DateService {

    DateLB createIfNotExist(DateLB date);
    Optional<DateLB> getByDate(Timestamp date);
    DateLB save(DateLB dateLB);
}
