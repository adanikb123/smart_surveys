package epolsoft.practice.smart_surveys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import epolsoft.practice.smart_surveys.entity.enums.AnswerType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "answer_option")
@Data
@NoArgsConstructor
public class AnswerOption
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "poll_id", referencedColumnName = "id")
    private Poll poll;

    @Column(name = "option_text", nullable = false, columnDefinition = "text")
    private String option;

    @Column(name = "option_image", columnDefinition = "bytea")
    private byte[] optionImage;

    @Column(name = "voted_count", nullable = false, columnDefinition = "integer default 0")
    private int votedCount;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AnswerType answerType;
}
