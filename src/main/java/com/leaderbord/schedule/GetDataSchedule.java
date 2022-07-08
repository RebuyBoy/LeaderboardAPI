package com.leaderbord.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leaderbord.entity.DateLB;
import com.leaderbord.entity.PlayerResult;
import com.leaderbord.service.LeaderboardCollectorService;
import com.leaderbord.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetDataSchedule {
    private LeaderboardCollectorService leaderboardCollectorService;
    private LeaderboardService leaderboardService;

    // Start every month parse promotion id // https://pml.good-game-network.com/pm-leaderboard/group?groupId=374
    //new table groupId          id | date_id | game_id
    //new table promotionId  id  groupId_id

    public void process(){
        List<PlayerResult> players = leaderboardCollectorService.collect(new DateLB());
        leaderboardService.addAll(players);
    }

    public static void main(String[] args) throws JsonProcessingException {
        String str="{\"nickname\": \"tzzd2020\" ,\n" +
                "\"rank\": 1,\n" +
                "\"countryId\": \"CN\",\n" +
                "\"point\": \"3083.00\",\n" +
                "\"prize\": {\n" +
                "\"currencyId\": \"GCD\",\n" +
                "\"value\": 55,\n" +
                "\"description\": null,\n" +
                "\"updatedAt\": null,\n" +
                "\"prizeItem\": null\n" +
                "},\n" +
                "\"prizePaid\": {\n" +
                "\"currencyId\": null,\n" +
                "\"value\": 0,\n" +
                "\"description\": null,\n" +
                "\"updatedAt\": null,\n" +
                "\"prizeItem\": null\n" +
                "},\n" +
                "\"isCorrect\": true}";
        ObjectMapper objectMapper=new ObjectMapper();
//        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        try {
            JsonNode jsonNode = objectMapper.readTree(str);
            System.out.println(jsonNode.toPrettyString());
            String point = jsonNode.get("point").textValue();
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator(',');
            symbols.setDecimalSeparator('.');
            String pattern = "#,##0.0#";
            DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
            decimalFormat.setParseBigDecimal(true);

            System.out.println(decimalFormat.parse(point));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

//public class HealthWorkerCustomDeserializer extends StdDeserializer {
//
//    private static final long serialVersionUID = 1L;
//
//    public HealthWorkerCustomDeserializer() {
//        this(null);
//    }
//
//    public HealthWorkerCustomDeserializer(Class clazz) {
//        super(clazz);
//    }

//    @Override
//    public HealthWorker deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
//        HealthWorker healthWorker = new HealthWorker();
//        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
//        JsonNode customNameNode = jsonNode.get("name");
//        JsonNode customQualificationNode = jsonNode.get("qualification");
//        JsonNode customYearsOfExperienceNode = jsonNode.get("yearsOfExperience");
//        JsonNode customIdNode = jsonNode.get("yearsOfExperience");
//        String name = "Dr. " + customNameNode.asText();
//        String qualification = customQualificationNode.asText();
//        Double experience = customYearsOfExperienceNode.asDouble();
//        int id = customIdNode.asInt();
//        healthWorker.setName(name);
//        healthWorker.setQualification(qualification);
//        healthWorker.setYearsOfExperience(experience);
//        healthWorker.setId(id);
//        return healthWorker;
//    }
//}
//    Adding a deserializer is similar to adding a serializer, they are added to modules which then gets registered to the ObjectMapper instance:
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addDeserializer(HealthWorker.class, new HealthWorkerCustomDeserializer());
//        objectMapper.registerModule(simpleModule);
//        String healthWorkerJSON = "{\n\t\"id\": 1,\n\t\"name\": \"Reham Muzzamil\",\n\t\"qualification\": \"MBBS\",\n\t\"yearsOfExperience\": 1.5\n}";
//        HealthWorker healthWorker = objectMapper.readValue(healthWorkerJSON,HealthWorker.class);
//        System.out.println(healthWorker.getName());
