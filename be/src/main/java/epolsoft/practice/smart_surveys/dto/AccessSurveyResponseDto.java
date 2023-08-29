package epolsoft.practice.smart_surveys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessSurveyResponseDto {
    private Long id;

    private Boolean showResult;

    private SurveyResponseDto survey;
}
