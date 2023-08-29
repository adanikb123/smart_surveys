package epolsoft.practice.smart_surveys.dto;

import epolsoft.practice.smart_surveys.entity.enums.AnswerType;
import epolsoft.practice.smart_surveys.validators.EnumNamePattern;
import jakarta.annotation.Nullable;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerOptionRequestDto {
    @NotBlank(message = "{option.notBlank}")
    @Size(min = 1, message = "{option.wrongSize}")
    private String option;

    @Lob
    @Nullable
    private byte[] optionImage;

    @EnumNamePattern(regexp = "OPEN|CLOSED")
    private AnswerType answerType;
}
