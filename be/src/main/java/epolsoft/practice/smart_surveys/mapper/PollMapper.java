package epolsoft.practice.smart_surveys.mapper;

import epolsoft.practice.smart_surveys.dto.PollRequestDto;
import epolsoft.practice.smart_surveys.dto.PollResponseDto;
import epolsoft.practice.smart_surveys.entity.Poll;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PollMapper extends GeneralMapper<PollResponseDto, Poll, PollRequestDto> {

}
