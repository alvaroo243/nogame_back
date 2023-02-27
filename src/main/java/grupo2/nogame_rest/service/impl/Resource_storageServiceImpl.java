package grupo2.nogame_rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import grupo2.nogame_rest.model.db.Resource_StorageEditDb;
import grupo2.nogame_rest.model.db.Resource_StorageNewDb;
import grupo2.nogame_rest.model.db.Resource_storageDb;
import grupo2.nogame_rest.model.dto.Edit.Resource_StorageEdit;
import grupo2.nogame_rest.model.dto.List.Resource_storageList;
import grupo2.nogame_rest.model.dto.New.Resource_StorageNew;
import grupo2.nogame_rest.repository.Resource_storageRepository;
import grupo2.nogame_rest.service.Resource_storageService;
import grupo2.nogame_rest.service.mapper.Resource_storageMapper;

@Service
public class Resource_storageServiceImpl implements Resource_storageService{
    
    private final Resource_storageRepository resource_storageRepository;

    public Resource_storageServiceImpl(Resource_storageRepository resource_storageRepository) {
        this.resource_storageRepository = resource_storageRepository;
    }

	private static final Logger log = LoggerFactory.getLogger(Resource_storageServiceImpl.class);

    @Override
    public List<Resource_storageList> findAllResource_storageList() {
        return Resource_storageMapper.INSTANCE.resources_storageToResource_storageList(resource_storageRepository.findAll());
    }

    @Override
    public List<Resource_StorageEdit> findAllResource_storageEdit() {
        return Resource_storageMapper.INSTANCE.resources_storageToResource_storageEdit(resource_storageRepository.findAll());
    }

    @Override
    public Optional<Resource_StorageEdit> getResource_StorageEditById(Long id) {
        Optional<Resource_storageDb> storageDb =resource_storageRepository.findById(id);
        if (storageDb.isPresent())
            return Optional.of(Resource_storageMapper.INSTANCE.resource_storageDbToResource_storageEdit(storageDb.get()));
        else 
            return Optional.empty();
    }

    @Override
    public Optional <Resource_storageDb> getStorageDbByID(Long id) {
        Optional<Resource_storageDb> storageDb = resource_storageRepository.findById(id);
        if(storageDb.isPresent())
            return storageDb;
        else
        return Optional.empty();
    }

    @Override
    public Optional<Resource_StorageEditDb> getStorageEditDbById(Long id) {
        Optional<Resource_StorageEditDb> storageEditDb = Optional.of(Resource_storageMapper.INSTANCE.resourceStorageDbToResourceStorageEditDb(resource_storageRepository.findById(id).get()));
        if (storageEditDb.isPresent())
            return storageEditDb;
        else
            return Optional.empty();
    }
    
    @Override
    public Resource_storageDb getStorageByPlanetId(Long planetId) {
        List<Resource_storageDb> storages = resource_storageRepository.findAll();
        for (Resource_storageDb storage : storages) {
            if (storage.getPlanetDb() != null && storage.getPlanetDb().getId() == planetId) {
                return storage;
            }
        }
        return null;
    }

	@Scheduled(fixedRate = 60000)
    public void resource_generator() {
        int quantityToAdd = 20;
        //List<Resource_StorageEdit> lista_almacenes = findAllResource_storageEdit();
        List<Resource_storageDb> lista = resource_storageRepository.findAll();
        List<Resource_StorageEdit> lista_almacenes= Resource_storageMapper.INSTANCE.resources_storageToResource_storageEdit(lista);
        for (Resource_StorageEdit almacen : lista_almacenes) {
            int currentQuantity = almacen.getQuantity();
            int newQuantity = currentQuantity + quantityToAdd;
            almacen.setQuantity(newQuantity);
            update(almacen);
            log.info("Se agregaron " + quantityToAdd + " a la cantidad de recursos en la tabla Resource_storage.");
        }
    
        log.info("Se agregaron " + quantityToAdd + " a la cantidad de recursos en la tabla Resource_storage.");
    }

    @Override
    public Resource_StorageNew save(Resource_StorageNew resource_StorageNew) {
        Resource_StorageNewDb resource_StorageNewDb = Resource_storageMapper.INSTANCE.Resource_StorageNewToNewDb(resource_StorageNew);
        return Resource_storageMapper.INSTANCE.Resource_StorageNewDbToNew(resource_storageRepository.save(resource_StorageNewDb));
    }

    @Override
    public Optional<Resource_StorageEdit> update(Resource_StorageEdit resourceStorageEdit) {
        Optional<Resource_StorageEditDb> storageEditDb = getStorageEditDbById(resourceStorageEdit.getId());
        if (storageEditDb.isPresent()) {
            Resource_storageMapper.INSTANCE.updateStorageEditDbFromStorageEdit(resourceStorageEdit, storageEditDb.get());
            resource_storageRepository.save(storageEditDb.get());
            return getResource_StorageEditById(resourceStorageEdit.getId());
        } else {
            return Optional.empty();
        }
    }
    
}
