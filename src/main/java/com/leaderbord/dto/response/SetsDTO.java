package com.leaderbord.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SetsDTO {
    @JsonProperty("name")
    private String date;
    private List<SubsetsDTO> subsets;
}
