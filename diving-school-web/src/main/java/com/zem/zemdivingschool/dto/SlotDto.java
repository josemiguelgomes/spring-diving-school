package com.zem.zemdivingschool.dto;

import com.zem.zemdivingschool.persistence.model.*;

import java.util.Date;

public class SlotDto {
    private String title;
    private Date startingDate;
    private Date endingDate;
    private SlotStatus status;

    public SlotDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }
}
