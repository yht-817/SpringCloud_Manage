package net.sunwukong.www.marketing.bean;

import java.util.Date;

public class ChatServiceMessage {
    private String id;

    private String userNo;

    private String sendName;

    private String noticeType;

    private String sendTitle;

    private Date sendDate;

    private String sendText;

    private String sendProject1;

    private String sendProject2;

    private String sendUrl;

    private String serviceNo;

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

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName == null ? null : sendName.trim();
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType == null ? null : noticeType.trim();
    }

    public String getSendTitle() {
        return sendTitle;
    }

    public void setSendTitle(String sendTitle) {
        this.sendTitle = sendTitle == null ? null : sendTitle.trim();
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendText() {
        return sendText;
    }

    public void setSendText(String sendText) {
        this.sendText = sendText == null ? null : sendText.trim();
    }

    public String getSendProject1() {
        return sendProject1;
    }

    public void setSendProject1(String sendProject1) {
        this.sendProject1 = sendProject1 == null ? null : sendProject1.trim();
    }

    public String getSendProject2() {
        return sendProject2;
    }

    public void setSendProject2(String sendProject2) {
        this.sendProject2 = sendProject2 == null ? null : sendProject2.trim();
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl == null ? null : sendUrl.trim();
    }

    public String getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo == null ? null : serviceNo.trim();
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