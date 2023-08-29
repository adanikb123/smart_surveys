package epolsoft.practice.smart_surveys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyWithPollsDto {
    private Long id;

    private String surveyTitle;

    private String surveyDescriptionImage;

    private String surveyDescription;

    private Boolean anonymity;

    private LocalDateTime openSurveyDate;

    private LocalDateTime closeSurveyDate;

    private UserResponseDto author;

    private List<PollDto> polls;
}
