package epolsoft.practice.smart_surveys.dto;

import epolsoft.practice.smart_surveys.entity.enums.AnswerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerOptionWithoutResultDto {
    private Long id;

    private AnswerType answerType;

    private String option;
}
