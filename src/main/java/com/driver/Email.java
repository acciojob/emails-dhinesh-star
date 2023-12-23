package com.driver;

public class Email {

    private String emailId;
    private String password;

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(password.equals(oldPassword)==true){
            boolean upperCaseCheck=false,lowerCaseCheck=false,digitCheck=false,specialCharCheck=false;
            for(char ch:newPassword.toCharArray()){
                if(ch>=65 && ch<=90) upperCaseCheck=true;
                else if(ch>=97 && ch<=122) lowerCaseCheck=true;
                else if(ch>=48 && ch<=57) digitCheck=true;
                else specialCharCheck=true;
            }
            if(newPassword.length()>=8 && upperCaseCheck && lowerCaseCheck && digitCheck && specialCharCheck){
                this.password=newPassword;
            }
        }
    }
}
