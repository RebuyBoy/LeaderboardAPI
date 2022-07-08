package com.leaderbord.service;

import com.leaderbord.entity.DateLB;
import com.leaderbord.entity.PlayerResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GGNCollectorServiceImpl implements LeaderboardCollectorService {
//    private StakeService stakeService;
    private ClientGGNLbService clientService;

    @Override
    public List<PlayerResult> collect(DateLB date) {
        return null;
    }


//    @Override
//    public List<Player> collect(DateLB date) {
//        List<Stake> stakes=stakeService.getAll();
//        String url=constructUrl(date, stakes.get(0));
//        String response= clientService.getData(url);
//        String decodedResponse= decrypt(response);
//
//    }

}
