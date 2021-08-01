package com.example.ex4.repo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.DatabaseMetaData;
import java.util.Date;

/**
 * Hold the user structure
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@NotEmpty(message = "Name is mandatory")
    public String userName;

    public Date date = new Date();

    /**
     * getDate
     * @return date
     */
    public Date getDate() { return date; }

    /**
     * setDate
     * @param date date
     */
    public void setDate(Date date) { this.date = date; }

    /**
     * setId
     * @param id id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * getId
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * setUserName
     * @param userName userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * getUserName
     * @return userName
     */
    public String getUserName() {
        return userName;
    }
}
