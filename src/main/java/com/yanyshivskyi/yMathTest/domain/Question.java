package com.yanyshivskyi.yMathTest.domain;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Question {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public Question() {

    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_test")
    private Test id_test;

    private String question_text;
    private String filename1;
    private String filename2;
    private Float count_point;

    private String type;

    public Question(Long id, Test id_test, String question_text,
                    String filename1, String filename2, Float count_point,
                    String type) {
        this.id = id;
        this.id_test = id_test;
        this.question_text = question_text;
        this.filename1 = filename1;
        this.filename2 = filename2;
        this.count_point = count_point;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Test getId_test() {
        return id_test;
    }

    public void setId_test(Test id_test) {
        this.id_test = id_test;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getFilename1() {
        return filename1;
    }

    public void setFilename1(String filename1) {
        this.filename1 = filename1;
    }

    public String getFilename2() {
        return filename2;
    }

    public void setFilename2(String filename2) {
        this.filename2 = filename2;
    }

    public Float getCount_point() {
        return count_point;
    }

    public void setCount_point(Float count_point) {
        this.count_point = count_point;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}