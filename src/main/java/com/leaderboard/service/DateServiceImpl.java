package com.leaderboard.service;

import com.leaderboard.entity.DateLB;
import com.leaderboard.repository.DateRepository;
import com.leaderboard.service.interfaces.DateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DateServiceImpl implements DateService {
    private final DateRepository dateRepository;

    public DateServiceImpl(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    @Override
    public DateLB createIfNotExist(DateLB dateLB) {
        Optional<DateLB> date = getByDate(dateLB.getDate());
        return date.isEmpty()
                ? save(dateLB)
                : date.get();
    }

    @Override
    public Optional<DateLB> getByDate(LocalDate date) {
        return dateRepository.getByDate(date);

    }

    @Override
    public DateLB save(DateLB dateLB) {
        return dateRepository.save(dateLB);
    }
}
