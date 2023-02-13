package grupo2.nogame_rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.dto.List.Prerequisite_structureList;
import grupo2.nogame_rest.service.Prerequisite_structureService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class Prerequisite_structureRestController {
    
    private Prerequisite_structureService prerequisite_structureService;

    public Prerequisite_structureRestController(Prerequisite_structureService prerequisite_structureService) {
        this.prerequisite_structureService = prerequisite_structureService;
    }

    @GetMapping("/prerequisite_structure")
    public List<Prerequisite_structureList> getPrerequisite_structuresList() {
        return prerequisite_structureService.findAllPrerequisite_structureList();
    }
}
