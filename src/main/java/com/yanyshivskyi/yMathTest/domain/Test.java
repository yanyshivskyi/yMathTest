package com.yanyshivskyi.yMathTest.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
// This tells Hibernate to make a table out of this class
public class Test {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public Test() {

    }

    @NotBlank(message="Назва теста не може бути пустою!")
    private String name;
    private String description;
    private String time;

    @Min(value = 0, message = "Мінімальне значення - 0")
    @Column (name="count_try")
    private Integer countTry;

    public Test(String name, String description, String time, Integer count_try) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.countTry = count_try;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCountTry() {
        return countTry;
    }

    public void setCountTry(Integer countTry) {
        this.countTry = countTry;
    }
}