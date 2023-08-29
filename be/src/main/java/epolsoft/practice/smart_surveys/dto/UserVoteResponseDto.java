package epolsoft.practice.smart_surveys.dto;

import epolsoft.practice.smart_surveys.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVoteResponseDto {
    @NotNull
    private Long answerOptionId;

    @NotNull
    private Long userId;

    @NotNull
    private String text;
}
