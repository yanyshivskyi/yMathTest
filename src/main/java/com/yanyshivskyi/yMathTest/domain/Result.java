package com.yanyshivskyi.yMathTest.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_test")
    private Test test;
    private Float point;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date myDate;

    @Column(name="number_try")
    private Integer numberTry;

}