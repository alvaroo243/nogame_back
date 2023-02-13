package grupo2.nogame_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.ResourceDb;
import grupo2.nogame_rest.model.dto.List.ResourceList;

@Mapper
public interface ResourceMapper {
    ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);

    ResourceList ResourceDbToResourceList(ResourceDb resourceDb);
    
    List<ResourceList> resourcesToResourceList(List<ResourceDb> resourcesDb);
}
