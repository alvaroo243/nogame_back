package grupo2.nogame_rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.dto.List.TroopsList;
import grupo2.nogame_rest.service.TroopsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class TroopsRestController {
    
    private TroopsService troopsService;

    public TroopsRestController(TroopsService troopsService) {
        this.troopsService = troopsService;
    }

    @GetMapping("/troops")
    public List<TroopsList> getTroopsList() {
        return troopsService.findAllTroopsList();
    }
}