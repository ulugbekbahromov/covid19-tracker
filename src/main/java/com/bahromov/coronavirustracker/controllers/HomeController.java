package com.bahromov.coronavirustracker.controllers;

import com.bahromov.coronavirustracker.models.LocationStats;
import com.bahromov.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    CoronaVirusDataService coronaVirusDataService;

    public HomeController(CoronaVirusDataService coronaVirusDataService) {
        this.coronaVirusDataService = coronaVirusDataService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        int totalNewCases = allStats.stream().mapToInt(LocationStats::getDiffFromPrevDay).sum();

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
