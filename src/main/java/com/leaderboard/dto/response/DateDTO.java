package com.leaderboard.dto.response;

import java.sql.Timestamp;

public class DateDTO {
    private Timestamp timestamp;

    public DateDTO(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "DateDTO{" +
                "timestamp=" + timestamp +
                '}';
    }
}
