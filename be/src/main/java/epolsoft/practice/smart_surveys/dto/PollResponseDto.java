package epolsoft.practice.smart_surveys.dto;

import epolsoft.practice.smart_surveys.entity.enums.PollType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollResponseDto {

    private Long id;

    private String question;

    private PollType pollType;

    private List<AnswerOptionResponseDto> answers;

}
