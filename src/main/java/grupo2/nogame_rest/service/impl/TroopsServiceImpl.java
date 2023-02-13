package grupo2.nogame_rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import grupo2.nogame_rest.model.dto.List.TroopsList;
import grupo2.nogame_rest.repository.TroopsRepository;
import grupo2.nogame_rest.service.TroopsService;
import grupo2.nogame_rest.service.mapper.TroopsMapper;

@Service
public class TroopsServiceImpl implements TroopsService{
    
    private final TroopsRepository troopsRepository;

    public TroopsServiceImpl(TroopsRepository troopsRepository) {
        this.troopsRepository = troopsRepository;
    }

    public List<TroopsList> findAllTroopsList() {
        return TroopsMapper.INSTANCE.troopsToTroopsList(troopsRepository.findAll());
    }
}
