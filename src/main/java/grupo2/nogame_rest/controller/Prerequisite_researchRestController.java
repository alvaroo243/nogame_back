package grupo2.nogame_rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.dto.List.Prerequisite_researchList;
import grupo2.nogame_rest.service.Prerequisite_researchService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class Prerequisite_researchRestController {
    
    private Prerequisite_researchService prerequisite_researchService;

    public Prerequisite_researchRestController(Prerequisite_researchService prerequisite_researchService) {
        this.prerequisite_researchService = prerequisite_researchService;
    }

    @GetMapping("/prerequisite_research")
    public List<Prerequisite_researchList> getPrerequisite_researchsList() {
        return prerequisite_researchService.findAllPrerequisite_researchList();
    }
}