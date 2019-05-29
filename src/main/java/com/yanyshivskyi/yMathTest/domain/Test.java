package com.yanyshivskyi.yMathTest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;

@Entity // This tells Hibernate to make a table out of this class
public class Test {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public Test() {

    }

    private String name;
    private String description;
    private Time time;
    private Integer count_try;

    public Test(String name, String description, Time time, Integer count_try) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.count_try = count_try;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getCount_try() {
        return count_try;
    }

    public void setCount_try(Integer count_try) {
        this.count_try = count_try;
    }
}