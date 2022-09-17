package com.leaderboard.controllers;

import com.leaderboard.dto.api.response.ProviderDataResponse;
import com.leaderboard.dto.api.response.ProviderResponse;
import com.leaderboard.service.interfaces.ProviderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/provider")
@Tag(name = "ProviderController")
@CrossOrigin()
public class ProviderController implements BaseController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    public List<ProviderResponse> getProviders() {
        return providerService.getProviders();
    }

    @GetMapping("{provider}/data")
    public ProviderDataResponse getProviderDataByProvider(@PathVariable String provider) {
        return providerService.getProviderData(provider);
    }

}
