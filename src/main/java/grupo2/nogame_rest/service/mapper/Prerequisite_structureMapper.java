package grupo2.nogame_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.Prerequisite_structureDb;
import grupo2.nogame_rest.model.dto.List.Prerequisite_structureList;

@Mapper
public interface Prerequisite_structureMapper {
    Prerequisite_structureMapper INSTANCE = Mappers.getMapper(Prerequisite_structureMapper.class);

    @Mapping(target = "structure_name", source = "structure.name")
    Prerequisite_structureList Prerequisite_structureDbToPrerequisite_structureList(Prerequisite_structureDb prerequisite_structureDb);
    
    List<Prerequisite_structureList> prerequisite_structuresToPrerequisite_structureList(List<Prerequisite_structureDb> prerequisite_structuresDb);
}
