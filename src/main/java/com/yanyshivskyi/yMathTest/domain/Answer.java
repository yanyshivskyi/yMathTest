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

    @Column(name="answer_text")
    private String answerText;
    private Boolean isCorrect;
    private String conformity;

    public Answer(Question question, String answerText, Boolean isCorrect, String conformity) {
        this.question = question;
        this.answerText = answerText;
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

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getCorrect() {
        if(isCorrect) return "1";
        else return "0";
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
