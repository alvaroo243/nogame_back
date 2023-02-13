package grupo2.nogame_rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.dto.List.Resource_storageList;
import grupo2.nogame_rest.service.Resource_storageService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class Resource_storageRestController {
    
    private Resource_storageService resource_storageService;

    public Resource_storageRestController(Resource_storageService resource_storageService) {
        this.resource_storageService = resource_storageService;
    }

    @GetMapping("/resource_storage")
    public List<Resource_storageList> getResource_storagesList() {
        return resource_storageService.findAllResource_storageList();
    }
}