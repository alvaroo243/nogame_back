package grupo2.nogame_rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.dto.List.ResourceList;
import grupo2.nogame_rest.service.ResourceService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class ResourceRestController {
    
    private ResourceService resourceService;

    public ResourceRestController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/resource")
    public List<ResourceList> getResourcesList() {
        return resourceService.findAllResourceList();
    }
}