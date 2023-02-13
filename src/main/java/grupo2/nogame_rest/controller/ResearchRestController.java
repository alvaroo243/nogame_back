package grupo2.nogame_rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.dto.List.ResearchList;
import grupo2.nogame_rest.service.ResearchService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class ResearchRestController {
    
    private ResearchService researchService;

    public ResearchRestController(ResearchService researchService) {
        this.researchService = researchService;
    }

    @GetMapping("/research")
    public List<ResearchList> getResearchsList() {
        return researchService.findAllResearchList();
    }
}