package com.leaderboard.dto.response;

import java.sql.Timestamp;

public class DateResponse {
    private Timestamp timestamp;

    public DateResponse(Timestamp timestamp) {
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
