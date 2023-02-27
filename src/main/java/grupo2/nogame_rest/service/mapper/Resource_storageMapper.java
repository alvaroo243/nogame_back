package grupo2.nogame_rest.service.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.Resource_StorageEditDb;
import grupo2.nogame_rest.model.db.Resource_StorageNewDb;
import grupo2.nogame_rest.model.db.Resource_storageDb;
import grupo2.nogame_rest.model.dto.Edit.Resource_StorageEdit;
import grupo2.nogame_rest.model.dto.Info.Resource_StorageInfo;
import grupo2.nogame_rest.model.dto.List.Resource_storageList;
import grupo2.nogame_rest.model.dto.New.Resource_StorageNew;

@Mapper
public interface Resource_storageMapper {
    Resource_storageMapper INSTANCE = Mappers.getMapper(Resource_storageMapper.class);

    @Mapping(target = "resource_name", source = "resourceDb.name")
    @Mapping(target = "planet_name", source = "planetDb.name")
    Resource_storageList Resource_storageDbToResource_storageList(Resource_storageDb resource_storageDb);

    Resource_StorageEdit resource_storageDbToResource_storageEdit(Resource_storageDb resource_storageDb);

    Resource_StorageEditDb resourceStorageDbToResourceStorageEditDb(Resource_storageDb resource_storageDb);

    @Mapping(target = "resource_id", source = "resourceDb.id")
    Resource_StorageInfo ResourceStorageDbToResourceStorageInfo(Resource_storageDb resource_storageDb);

    Set<Resource_StorageInfo> ResourcesStorageDbDbToResourcesStorageInfo(Set<Resource_storageDb> resources_storageDb);

    Resource_StorageNewDb Resource_StorageNewToNewDb(Resource_StorageNew resource_StorageNew);

    Resource_StorageNew Resource_StorageNewDbToNew(Resource_StorageNewDb resource_StorageNewDb);
    
    List<Resource_storageList> resources_storageToResource_storageList(List<Resource_storageDb> resource_storagesDb);
    List<Resource_StorageEdit> resources_storageToResource_storageEdit(List<Resource_storageDb> resource_storageDb);

    void updateStorageEditDbFromStorageEdit(Resource_StorageEdit resource_StorageEdit,@MappingTarget Resource_StorageEditDb resource_StorageEditDb);
}
