package com.leaderbord.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GroupsResponseDTO {
    int groupId;
    String name;
    String[] gameTypes; // --> to ENUM
    List<SetsDTO> sets;  //
}
