package grupo2.nogame_rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.dto.TypeList;
import grupo2.nogame_rest.repository.TypeRepository;
import grupo2.nogame_rest.service.TypeService;
import grupo2.nogame_rest.service.mapper.TypeMapper;

@Service
public class TypeServiceImpl implements TypeService{

    private final TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<TypeList> findAllTypeList() {
        return TypeMapper.INSTANCE.typesToTypeList(typeRepository.findAll());
    }
}
