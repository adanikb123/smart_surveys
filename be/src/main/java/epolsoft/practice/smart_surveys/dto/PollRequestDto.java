package epolsoft.practice.smart_surveys.dto;

import epolsoft.practice.smart_surveys.entity.enums.PollType;
import epolsoft.practice.smart_surveys.validators.EnumNamePattern;
import jakarta.annotation.Nullable;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollRequestDto {
    @NotBlank(message = "{question.notBlank}")
    @Size(min = 1, max = 200, message = "{question.wrongSize}")
    private String question;

    @Lob
    @Nullable
    private byte[] pollDescriptionImage;

    @EnumNamePattern(regexp = "SINGLE|VARIABLE")
    private PollType pollType;

    @NotNull(message = "{listAnsOpt.notNull}")
    private List<AnswerOptionRequestDto> answers;
}
