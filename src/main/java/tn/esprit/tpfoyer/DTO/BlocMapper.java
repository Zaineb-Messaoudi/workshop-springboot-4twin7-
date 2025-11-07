package tn.esprit.tpfoyer.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpfoyer.entities.Bloc;

@Mapper(componentModel = "spring")
public interface BlocMapper {
    @Mapping(target = "libelleBloc", source = "nomBloc")
    BlocDTO toDTO(Bloc bloc);

}
