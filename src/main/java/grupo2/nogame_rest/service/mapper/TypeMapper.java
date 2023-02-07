package grupo2.nogame_rest.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import grupo2.nogame_rest.model.db.TypeDb;
import grupo2.nogame_rest.model.dto.List.TypeList;

@Mapper
public interface TypeMapper {
    TypeMapper INSTANCE = Mappers.getMapper(TypeMapper.class);

    TypeList TypeDbToTypeList(TypeDb typeDb);
    List<TypeList> typesToTypeList(List<TypeDb> typeDb);
}
