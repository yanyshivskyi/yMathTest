package com.yanyshivskyi.yMathTest.domain;

        import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Question {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_test")
    private Test idTest;

    @Column(name = "question_text")
    private String questionText;
    private String filename1;
    private String filename2;

    @Column(name = "count_point")
    private Float countPoint;

    private String type;

    public Question() {

    }
    public Question(Test idTest, String questionText,
                    String filename1, String filename2, Float count_point, String type) {
        this.idTest = idTest;
        this.questionText = questionText;
        this.filename1 = filename1;
        this.filename2 = filename2;
        this.countPoint = count_point;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Test getIdTest() {
        return idTest;
    }

    public void setIdTest(Test idTest) {
        this.idTest = idTest;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
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

    public Float getCountPoint() {
        return countPoint;
    }

    public void setCountPoint(Float countPoint) {
        this.countPoint = countPoint;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}