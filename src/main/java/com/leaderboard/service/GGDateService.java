package com.leaderboard.service;

import com.leaderboard.entity.DateLB;
import com.leaderboard.repository.DateRepository;
import com.leaderboard.service.interfaces.DateService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class GGDateService implements DateService {
    private final DateRepository dateRepository;

    public GGDateService(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    @Override
    public DateLB createIfNotExist(DateLB dateLB) {
        Optional<DateLB> date = getByDate(dateLB.getTimestamp());
        return date.isEmpty()
                ? save(dateLB)
                : date.get();
    }

    @Override
    public Optional<DateLB> getByDate(Timestamp date) {
        return dateRepository.getByTimestamp(date);

    }

    @Override
    public DateLB save(DateLB dateLB) {
        return dateRepository.save(dateLB);
    }
}
