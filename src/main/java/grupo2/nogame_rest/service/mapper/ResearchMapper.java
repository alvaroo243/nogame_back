package grupo2.nogame_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.ResearchDb;
import grupo2.nogame_rest.model.dto.List.ResearchList;

@Mapper
public interface ResearchMapper {
    ResearchMapper INSTANCE = Mappers.getMapper(ResearchMapper.class);

    ResearchList ResearchDbToResearchList(ResearchDb researchDb);
    
    List<ResearchList> researchToResearchList(List<ResearchDb> researchsDb);
}
