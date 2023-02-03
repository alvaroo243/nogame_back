package grupo2.nogame_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo2.nogame_rest.model.dto.TypeList;
import grupo2.nogame_rest.service.TypeService;

@RestController
@RequestMapping("/api/v1/")
public class TypeRestController {
    
    private TypeService typeService;

    @Autowired
    public TypeRestController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/type")
    public List<TypeList> getTypesList() {
        return typeService.findAllTypeList();
    }
}