package com.ElmaC.springmvc.Pojo;

public class UserPojo {
    private String username;
    private String password;
    private Integer sex;
    private String[] interest;
    private String intro;

    public UserPojo() {
    }

    public UserPojo(String username, String password, Integer sex, String[] interest, String intro) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.interest = interest;
        this.intro = intro;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置
     * @param sex
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取
     * @return interest
     */
    public String[] getInterest() {
        return interest;
    }

    /**
     * 设置
     * @param interest
     */
    public void setInterest(String[] interest) {
        this.interest = interest;
    }

    /**
     * 获取
     * @return intro
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 设置
     * @param intro
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String toString() {
        return "UserPojo{username = " + username + ", password = " + password + ", sex = " + (sex == 1?"男":"女") + ", interest = " + interest + ", intro = " + intro + "}";
    }
}
