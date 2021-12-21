
package model;

import java.util.Date;

/**
 * Message class is used to store and allow access to the
 * message for each user.
 * Has constructor methods as well as
 * all required getters and setters.
 */
public class Message {

    private int messageID;
    private int userID;
    private String message;
    private Date sentDate;
    private String subjectLine;
    private Boolean readStatus;

    /**
     * Constructor initializes a Message object
     * @param userID
     * @param message
     * @param subjectLine
     * @param sentDate
     */
    public Message(int userID, String message, String subjectLine, Date sentDate)
    {
        setUserID(userID);
        setMessage(message);
        setSubjectLine(subjectLine);
        setSentDate(sentDate);
        setReadStatus(false);
    }

    public Message()
    {}

    // GETTERS AND SETTERS AND TO STRING METHODS

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getSubjectLine() {
        return subjectLine;
    }

    public void setSubjectLine(String subjectLine) {
        this.subjectLine = subjectLine;
    }

    public Boolean getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Boolean readStatus) {
        this.readStatus = readStatus;
    }

    @Override
    public String toString() {
        String read;
        if (readStatus == true)
        {
            read = "Read";
        }
        else
            read = "Unread";

        return sentDate + "      " +subjectLine + "      " + read;
    }
}

