package com.example.ex4.repo;

import javax.persistence.*;

/**
 * Hold the message structure
 */
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userName;

    private String msg;

    /**
     * getId
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * setId
     * @param id id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * getMsg
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * setMsg
     * @param message message
     */
    public void setMsg(String message) {
        this.msg = message;
    }

    /**
     * getUserName
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * setUserName
     * @param userName userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     * constructor
     */
    public Message() {
    }

    /**
     * constructor
     * @param userName userName
     * @param message message
     */
    public Message(String userName, String message) {
        this.userName = userName;
        this.msg = message;
    }
}
