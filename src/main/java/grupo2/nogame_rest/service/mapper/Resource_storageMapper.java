package grupo2.nogame_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.Resource_StorageEditDb;
import grupo2.nogame_rest.model.db.Resource_storageDb;
import grupo2.nogame_rest.model.dto.Edit.Resource_StorageEdit;
import grupo2.nogame_rest.model.dto.List.Resource_storageList;


@Mapper
public interface Resource_storageMapper {
    Resource_storageMapper INSTANCE = Mappers.getMapper(Resource_storageMapper.class);

    @Mapping(target = "resource_name", source = "resourceDb.name")
    @Mapping(target = "planet_name", source = "planetDb.name")

    Resource_storageList Resource_storageDbToResource_storageList(Resource_storageDb resource_storageDb);
    
    Resource_StorageEdit resource_storageDbToResource_storageEdit(Resource_storageDb resource_storage);
    
    Resource_StorageEditDb resourceStorageDbToResourceStorageEditDb(Resource_storageDb resource_storageDb);
    
    void updateStorageEditDbFromStorageEdit(Resource_StorageEdit storageEdit,@MappingTarget Resource_StorageEditDb storageEditDb);

    
    List<Resource_StorageEdit> resources_storageToResource_storageEdit(List<Resource_storageDb> resource_storageDb);
    List<Resource_storageList> resources_storageToResource_storageList(List<Resource_storageDb> resource_storagesDb);
    
    Object storageEditDbToStorageEdit(Resource_StorageEditDb almacenDb);
    

}
