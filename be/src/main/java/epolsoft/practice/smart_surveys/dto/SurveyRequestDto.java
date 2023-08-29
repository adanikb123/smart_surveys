package epolsoft.practice.smart_surveys.dto;

import epolsoft.practice.smart_surveys.entity.enums.TimeType;
import epolsoft.practice.smart_surveys.validators.EnumNamePattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyRequestDto {
    @NotBlank(message = "{surveyTitle.notBlank}")
    @Size(min = 1, max = 50, message = "{surveyTitle.wrongSize}")
    private String surveyTitle;

    @NotBlank(message = "{surveyDescriptionImage.notBlank}")
    @Size(min = 1, max = 200, message = "{surveyDescriptionImage.wrongSize}")
    private String surveyDescriptionImage;

    @NotBlank(message = "{surveyDescription.notBlank}")
    @Size(min = 1, max = 200, message = "{surveyDescription.wrongSize}")
    private String surveyDescription;

    @NotNull(message = "{anonymity.notNull}")
    private Boolean anonymity;

    @NotNull(message = "{timeAmount.notNull}")
    @PositiveOrZero(message = "{timeAmount.notNegative}")
    private Integer timeAmount;

    @EnumNamePattern(regexp = "SECOND|MINUTE|HOUR|DAY|WEEK|MONTH|YEAR")
    private TimeType timeType;

    @NotNull(message = "{openSurveyDate.notNull}")
    private LocalDateTime openSurveyDate;

    @NotNull(message = "{closeSurveyDate.notNull}")
    private LocalDateTime closeSurveyDate;

    @NotNull(message = "{closeSurveyIterableDate.notNull}")
    private LocalDateTime closeSurveyIterableDate;

    @NotNull(message = "{authorId.notNull}")
    private Long authorId;

    @NotNull(message = "{listPoll.notNull}")
    private List<PollRequestDto> polls;
}
