package com.example.webdev.controller;

import com.example.webdev.core.screening.*;
import com.example.webdev.core.screening.model.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ScreeningController {
    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @ModelAttribute("screenings")
    public List<ScreeningDto> screenings() {
        return screeningService.listScreenings();
    }

    @GetMapping("/screenings")
    public String getScreeningsPage() {
        return "screenings";
    }
}
