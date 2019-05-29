package com.yanyshivskyi.yMathTest.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Answer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public Answer() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_question")
    private Question question;
    private String answer_text;
    private Boolean isCorrect;
    private String conformity;

    public Answer(Long id, Question question, String answer_text, Boolean isCorrect, String conformity) {
        this.id = id;
        this.question = question;
        this.answer_text = answer_text;
        this.isCorrect = isCorrect;
        this.conformity = conformity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getId_question() {
        return question;
    }

    public void setId_question(Question question) {
        this.question = question;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public String getConformity() {
        return conformity;
    }

    public void setConformity(String conformity) {
        this.conformity = conformity;
    }
}
