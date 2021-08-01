package com.example.ex4.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

/**
 * This Bean class hold the user name for user session
 */
@Component
public class UserSession implements Serializable {
    /**
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the user name
     * @param userName user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * The user name
     */
    private String userName;

    /**
     * The user session bean
     * @return UserSession
     */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserSession userSessionBean() {
        return new UserSession();
    }
}

