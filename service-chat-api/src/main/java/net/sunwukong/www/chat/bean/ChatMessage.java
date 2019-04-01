package net.sunwukong.www.chat.bean;

import java.util.Date;

public class ChatMessage {
    private String id;

    private String userNo;

    private Date sendDate;

    private String messageType;

    private String messageMode;

    private String groupNo;

    private String receiveUserNo;

    private String messageText;

    private String messagePhotoSmall;

    private String messagePhotoLarge;

    private String readState;

    private Date readDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    public String getMessageMode() {
        return messageMode;
    }

    public void setMessageMode(String messageMode) {
        this.messageMode = messageMode == null ? null : messageMode.trim();
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo == null ? null : groupNo.trim();
    }

    public String getReceiveUserNo() {
        return receiveUserNo;
    }

    public void setReceiveUserNo(String receiveUserNo) {
        this.receiveUserNo = receiveUserNo == null ? null : receiveUserNo.trim();
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText == null ? null : messageText.trim();
    }

    public String getMessagePhotoSmall() {
        return messagePhotoSmall;
    }

    public void setMessagePhotoSmall(String messagePhotoSmall) {
        this.messagePhotoSmall = messagePhotoSmall == null ? null : messagePhotoSmall.trim();
    }

    public String getMessagePhotoLarge() {
        return messagePhotoLarge;
    }

    public void setMessagePhotoLarge(String messagePhotoLarge) {
        this.messagePhotoLarge = messagePhotoLarge == null ? null : messagePhotoLarge.trim();
    }

    public String getReadState() {
        return readState;
    }

    public void setReadState(String readState) {
        this.readState = readState == null ? null : readState.trim();
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }
}