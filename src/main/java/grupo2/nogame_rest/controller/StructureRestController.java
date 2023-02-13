package grupo2.nogame_rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.dto.List.StructureList;
import grupo2.nogame_rest.service.StructureService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class StructureRestController {
    
    private StructureService structureService;

    public StructureRestController(StructureService structureService) {
        this.structureService = structureService;
    }

    @GetMapping("/structure")
    public List<StructureList> getStructuresList() {
        return structureService.findAllStructureList();
    }
}