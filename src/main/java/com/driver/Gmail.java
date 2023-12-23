package com.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Gmail extends Email {
    public class Mail{
        Date da;
        String send;

        public Date getDa() {
            return da;
        }

        public void setDa(Date da) {
            this.da = da;
        }

        public String getSend() {
            return send;
        }

        public void setSend(String send) {
            this.send = send;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        String msg;

        public Mail(Date da, String send, String msg) {
            this.da = da;
            this.send = send;
            this.msg = msg;
        }
    }
    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    private ArrayList<Mail> inbox;
    private ArrayList<Mail> trash;

    public void setInboxCapacity(int inboxCapacity) {
        this.inboxCapacity = inboxCapacity;
    }

    public ArrayList<Mail> getInbox() {
        return inbox;
    }

    public void setInbox(ArrayList<Mail> inbox) {
        this.inbox = inbox;
    }

    public ArrayList<Mail> getTrash() {
        return trash;
    }

    public void setTrash(ArrayList<Mail> trash) {
        this.trash = trash;
    }

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
        this.inbox = new ArrayList<>();
        this.trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        Mail newMail = new Mail(date,sender,message);
        inbox.add(0,newMail);
        if(inbox.size()>inboxCapacity){
            Mail mailDeleted = inbox.remove(inbox.size()-1);
            trash.add(mailDeleted);
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        Mail mailToBeDeleted = null;
        for(Mail mail:inbox){
            if(mail.msg.equals(message)){
                mailToBeDeleted=mail;
                inbox.remove(mailToBeDeleted);
                trash.add(mailToBeDeleted);
                break;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.size()==0) return  null;
        return inbox.get(0).msg;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.size()==0) return  null;
        return inbox.get(inbox.size()-1).msg;

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count=0;
        for(Mail mail:inbox){
            if(mail.da.toInstant().isBefore(start.toInstant())==false && mail.da.toInstant().isAfter(end.toInstant())==false)
                count++;
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
