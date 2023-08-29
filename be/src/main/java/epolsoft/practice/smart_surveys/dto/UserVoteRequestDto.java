package epolsoft.practice.smart_surveys.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVoteRequestDto {
    @NotNull(message = "{answerOptionId.notNull}")
    private Long answerOptionId;

    @NotNull(message = "{userVoteText.notNull}")
    private String text;
}
