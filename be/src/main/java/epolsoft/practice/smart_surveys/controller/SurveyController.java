package epolsoft.practice.smart_surveys.controller;

import com.itextpdf.text.*;
import epolsoft.practice.smart_surveys.dto.*;
import epolsoft.practice.smart_surveys.dto.AccessSurveyRequestDto;
import epolsoft.practice.smart_surveys.dto.AccessSurveyResponseDto;
import epolsoft.practice.smart_surveys.dto.SurveyAnswerResponseDto;
import epolsoft.practice.smart_surveys.dto.SurveyRequestDto;
import epolsoft.practice.smart_surveys.dto.SurveyResponseDto;
import epolsoft.practice.smart_surveys.dto.SurveyWithPollsDto;
import epolsoft.practice.smart_surveys.dto.UserVoteRequestDto;
import epolsoft.practice.smart_surveys.dto.UserVoteResponseDto;
import epolsoft.practice.smart_surveys.entity.AccessSurvey;
import epolsoft.practice.smart_surveys.entity.Survey;
import epolsoft.practice.smart_surveys.mapper.*;
import epolsoft.practice.smart_surveys.services.AccessSurveyService;
import epolsoft.practice.smart_surveys.services.FileDownloadService;
import epolsoft.practice.smart_surveys.services.SurveyService;
import epolsoft.practice.smart_surveys.services.UserVoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Опросник", description = "Все методы для работы с опросником")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @Autowired
    private AccessSurveyService accessSurveyService;

    @Autowired
    private UserVoteService userVoteService;

    @Autowired
    private AccessSurveyMapper accessSurveyMapper;

    @Autowired
    private SurveyMapper surveyMapper;

    @Autowired
    private UserVoteMapper userVoteMapper;

    @Autowired
    private FileDownloadService fileDownloadService;

    @Autowired
    private SurveyAnswerOptionMapper surveyAnswerOptionMapper;

    @Operation(summary = "Создать новый опрос")
    @PreAuthorize("hasAuthority('MODER')")
    @PostMapping()
    public SurveyResponseDto createSurvey(@Valid @RequestBody SurveyRequestDto surveyDto) {
        Survey survey = surveyService.createSurvey(surveyDto);
        return surveyMapper.toResponseDto(survey);
    }

    @Operation(summary = "Создать новый доступ к опросу")
    @PreAuthorize("hasAuthority('MODER')")
    @PostMapping("/access")
    public AccessSurveyResponseDto createAccessSurvey(@Valid @RequestBody AccessSurveyRequestDto accessSurveyDto) {
        AccessSurvey accessSurvey = accessSurveyService.createAccessSurvey(accessSurveyDto);
        return accessSurveyMapper.toResponseDto(accessSurvey);
    }

    @Operation(summary = "Получить опрос по id")
    @PreAuthorize("hasAnyAuthority('USER', 'MODER', 'ADMIN')")
    @GetMapping("/{id}")
    public SurveyWithPollsDto getById(@PathVariable Long id) {
        Survey survey = surveyService.getSurveyById(id);
        return surveyMapper.toSurveyWithPollsDto(survey);
    }

    @Operation(summary = "Получить список опросов за авторством текущего пользователя")
    @PreAuthorize("hasAuthority('MODER')")
    @GetMapping("/author")
    public Page<SurveyResponseDto> getSurveys(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Survey> surveys = surveyService.getAllSurveysByUser(pageable);

        return surveys.map(surveyMapper::toResponseDto);
    }

    @Operation(summary = "Получить список доступных текущему пользователю опросов")
    @PreAuthorize("hasAnyAuthority('USER', 'MODER', 'ADMIN')")
    @GetMapping("/available")
    public Page<AccessSurveyResponseDto> getAccessSurveys(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.ASC) Pageable pageable) {
        Page<AccessSurvey> accessSurveys = accessSurveyService.getAccessSurveysByUser(pageable);
        return accessSurveys.map(accessSurveyMapper::toResponseDto);

    }
    @Operation(summary = "Получить данные по id опроса: количество голосов за каждый вариант ответа и процентное соотношение ответов. ")
    @PreAuthorize("hasAnyAuthority('USER', 'MODER', 'ADMIN')")
    @GetMapping("/{id}/answers")
    public SurveyAnswerResponseDto getAnswersOption(@PathVariable Long id) {
        Survey survey = surveyService.getAllAnswersOptionById(id);
        return surveyAnswerOptionMapper.toResponseDto(survey);
    }

    @Operation(summary = "Записать результаты опроса в бд")
    @PreAuthorize("hasAnyAuthority('USER', 'MODER', 'ADMIN')")
    @PostMapping("/submit")
    public List<UserVoteResponseDto> setUserVote(
            @RequestBody List<UserVoteRequestDto> userVoteDtos) {
        List<UserVoteResponseDto> userVoteResponseDto = userVoteMapper.toResponseDtos(userVoteDtos);
        return userVoteService.createUserVotes(userVoteResponseDto);
    }

    @Operation(summary = "Получить отчет ")
    @GetMapping("/report/{id}")
    public ModelAndView getReportAnswersOption(@PathVariable Long id) throws DocumentException, IOException {
        fileDownloadService.getReport(id);
        return new ModelAndView("redirect:/downloadFile/surveyReport_" + id + ".pdf");
    }
}
