package grupo2.nogame_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.Prerequisite_researchDb;
import grupo2.nogame_rest.model.dto.List.Prerequisite_researchList;

@Mapper
public interface Prerequisite_researchMapper {
    Prerequisite_researchMapper INSTANCE = Mappers.getMapper(Prerequisite_researchMapper.class);

    @Mapping(target = "structure_name", source = "structure.name")
    @Mapping(target = "resource_name", source = "resource.name")
    Prerequisite_researchList Prerequisite_researchDbToPrerequisite_researchList(Prerequisite_researchDb prerequisite_researchDb);
    
    List<Prerequisite_researchList> prerequisite_researchsToPrerequisite_researchList(List<Prerequisite_researchDb> prerequisite_researchsDb);
}
