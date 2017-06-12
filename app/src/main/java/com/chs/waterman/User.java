package com.chs.waterman;

import com.orm.SugarRecord;

/**
 * Created by SESA312452 on 5/26/2017.
 */

public class User extends SugarRecord {



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    String userName;
    String userWeight;
    String userGender;

    public User(){

    }
    public User(String userName, String userWeight, String userGender){
        this.userName = userName;
        this.userWeight = userWeight;
        this.userGender = userGender;
    }
}

