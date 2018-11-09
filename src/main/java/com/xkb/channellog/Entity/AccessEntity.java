package com.xkb.channellog.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class AccessEntity implements Serializable {
    private String ip;
    private String channel;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy:MM:dd:HH:mm:ss")
    private Date accessDateTime;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getAccessDateTime() {
        return accessDateTime;
    }

    public void setAccessDateTime(Date accessDateTime) {
        this.accessDateTime = accessDateTime;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
