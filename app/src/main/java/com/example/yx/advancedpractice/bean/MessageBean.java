package com.example.yx.advancedpractice.bean;

import java.io.Serializable;

/**
 * @author yangxia
 * @since 2/1/19 下午4:55
 */
public class MessageBean implements Serializable {
    /**
     * 消息的来源
     */
    private String  fromName;
    /**
     * 消息的发送时间
     */
    private String  sendTime;
    /**
     * 消息的内容
     */
    private String content;
    private MessageEnum messageEnum;

    public MessageEnum getMessageEnum() {
        return messageEnum;
    }

    public void setMessageEnum(MessageEnum messageEnum) {
        this.messageEnum = messageEnum;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
