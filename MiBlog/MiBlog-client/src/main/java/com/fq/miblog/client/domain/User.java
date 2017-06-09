package com.fq.miblog.client.domain;

/**
 * @author jifang
 * @since 16/7/7 下午3:19.
 */
public class User {

    private Long id;

    private String email;

    private String nickname;

    private String password;

    private Long time;

    public User() {
    }

    public User(Long id, String email, String nickname, String password, Long time) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long         getTime() {
        return time;
    }

    public void qsetTime(Long time) {
        this.time = time;
    }
}