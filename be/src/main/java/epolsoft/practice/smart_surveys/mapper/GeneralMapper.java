package epolsoft.practice.smart_surveys.mapper;

import java.util.List;

public interface GeneralMapper<D,E,R>{
    E toEntity(R dto);
    List<E> toEntities(List<R> dtos);
    D toResponseDto(E entity);
    List<D> toResponseDtos(List<E> entities);
}
