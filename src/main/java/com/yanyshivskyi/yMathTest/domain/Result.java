package com.yanyshivskyi.yMathTest.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity // This tells Hibernate to make a table out of this class
public class Result {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public Result() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_user")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Float getPoint() {
        return point;
    }

    public void setPoint(Float point) {
        this.point = point;
    }

    public Date getMyDate() {
        return myDate;
    }

    public void setMyDate(Date myDate) {
        this.myDate = myDate;
    }

    public Integer getNumberTry() {
        return numberTry;
    }

    public void setNumberTry(Integer numberTry) {
        this.numberTry = numberTry;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_test")
    private Test test;
    private Float point;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date myDate;

    @Column(name="number_try")
    private Integer numberTry;

    public Result(User user, Test test, Float point, Date myDate, Integer numberTry) {
        this.user = user;
        this.test = test;
        this.point = point;
        this.myDate = myDate;
        this.numberTry = numberTry;
    }

}