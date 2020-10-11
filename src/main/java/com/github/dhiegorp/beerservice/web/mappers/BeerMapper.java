package com.github.dhiegorp.beerservice.web.mappers;

import com.github.dhiegorp.beerservice.domain.Beer;
import com.github.dhiegorp.beerservice.web.model.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    @Mapping(target = "beerName", source = "name")
    @Mapping(target = "beerStyle", source = "style")
    BeerDto beerDTOtoBeer(Beer beer);

    @Mapping(target = "name", source = "beerName")
    @Mapping(target = "style", source = "beerStyle")
    Beer beertoBeerDTO(BeerDto beer);
}
