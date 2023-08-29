package epolsoft.practice.smart_surveys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import epolsoft.practice.smart_surveys.entity.enums.PollType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poll")
@Data
@NoArgsConstructor
public class Poll
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    private Survey survey;

    @Column(name = "poll_image", columnDefinition = "bytea")
    private byte[] pollDescriptionImage;

    @Column(name = "question_text", nullable = false, columnDefinition = "text")
    private String question;

    @Column(name = "poll_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PollType pollType;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.REMOVE)
    private List<AnswerOption> answers = new ArrayList<>();
}