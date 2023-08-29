package epolsoft.practice.smart_surveys.entity;

import epolsoft.practice.smart_surveys.entity.enums.TimeType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "survey")
@Data
@NoArgsConstructor
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "survey_title", nullable = false, length = 50)
    private String surveyTitle;

    @Column(name = "survey_description_image", length = 200)
    private String surveyDescriptionImage;

    @Column(name = "survey_description", length = 200)
    private String surveyDescription;

    @Column(name = "anonymity")
    private Boolean anonymity;

    @Column(name = "time_amount", nullable = false)
    private Integer timeAmount;

    @Column(name = "time_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TimeType timeType;

    @Column(name = "open_survey_date", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime openSurveyDate;

    @Column(name = "close_survey_date", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime closeSurveyDate;

    @Column(name = "close_survey_iterable_date", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime closeSurveyIterableDate;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.REMOVE)
    private List<Poll> polls = new ArrayList<>();
}
