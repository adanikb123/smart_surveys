package epolsoft.practice.smart_surveys.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessSurveyRequestDto {
    @NotNull(message = "{showResult.notNull}")
    private Boolean showResult;

    @NotNull(message = "{surveyId.notNull}")
    private Long surveyId;

    @NotNull(message = "{userId.notNull}")
    private Long userId;
}
