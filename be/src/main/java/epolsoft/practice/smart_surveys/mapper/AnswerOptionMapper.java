package epolsoft.practice.smart_surveys.mapper;

import epolsoft.practice.smart_surveys.dto.AnswerOptionRequestDto;
import epolsoft.practice.smart_surveys.dto.AnswerOptionResponseDto;
import epolsoft.practice.smart_surveys.entity.AnswerOption;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AnswerOptionMapper extends GeneralMapper<AnswerOptionResponseDto, AnswerOption, AnswerOptionRequestDto> {
    default List<AnswerOptionResponseDto> toResponseDtos(List<AnswerOption> source) {
        int totalVotes = source
                .stream()
                .mapToInt(AnswerOption::getVotedCount)
                .sum();
        if (totalVotes == 0) {
            return source
                    .stream()
                    .map(this::toResponseDto)
                    .collect(Collectors.toList());
        } else {
            return source
                    .stream()
                    .map(answerOption -> toResponseDto(answerOption, totalVotes))
                    .collect(Collectors.toList());
        }
    }

    private AnswerOptionResponseDto toResponseDto(AnswerOption answerOption, int totalVotes) {
        AnswerOptionResponseDto responseDto = toResponseDto(answerOption);
        responseDto.setVotedInPercent(((double) answerOption.getVotedCount() / totalVotes) * 100);
        return responseDto;
    }
}
