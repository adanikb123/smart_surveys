package epolsoft.practice.smart_surveys.services.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import epolsoft.practice.smart_surveys.dto.AnswerOptionResponseDto;
import epolsoft.practice.smart_surveys.entity.AnswerOption;
import epolsoft.practice.smart_surveys.entity.Poll;
import epolsoft.practice.smart_surveys.entity.Survey;
import epolsoft.practice.smart_surveys.mapper.AnswerOptionMapper;
import epolsoft.practice.smart_surveys.services.FileDownloadService;
import epolsoft.practice.smart_surveys.services.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileDownloadServiceImpl implements FileDownloadService {
    private Path foundFile;

    @Autowired
    private SurveyService surveyService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private AnswerOptionMapper answerOptionMapper;

    @Override
    public Document getReport(Long id) throws IOException, DocumentException {
        Survey survey = surveyService.getAllAnswersOptionById(id);
        String file;

        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);//заголовок опроса
        Font answerFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);//ответ
        Font descriptionFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL);//описание
        Font questionFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);//вопрос

        file = uploadPath + "/surveyReport_" + id + ".pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();

        Paragraph preface = new Paragraph();
        preface.add(new Paragraph(survey.getSurveyTitle(), titleFont));
        preface.add(new Paragraph(" "));
        preface.add(new Paragraph(survey.getSurveyDescription(), descriptionFont));
        preface.add(new Paragraph(" "));

        List<Poll> polls = survey.getPolls();
        for (Poll poll : polls) {
            List<AnswerOption> answers = poll.getAnswers();
            List<AnswerOptionResponseDto> answersDto = answerOptionMapper.toResponseDtos(answers);
            preface.add(new Paragraph(poll.getQuestion(), questionFont));
            for (AnswerOptionResponseDto answer : answersDto) {
                preface.add(new Paragraph(answer.getOption() +
                        " - " + Math.round(answer.getVotedInPercent()) + "% " +
                        "(" + answer.getVotedCount() + ")", answerFont));
            }
            preface.add(new Paragraph(" "));
        }
        document.add(preface);
        document.close();

        return document;
    }

    public Resource getFileAsResource(String fileCode) throws IOException {
        Path dirPath = Paths.get("be/src/main/resources/report");

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
            }
        });

        if (foundFile != null) {return new UrlResource(foundFile.toUri());}
        return null;
    }
}
