package com.zcgx.ticNews.dto;

import java.util.Date;
import java.util.List;

public class ArticleDTO {
    private long id;
    private String title;
    private String summary;
    private String votePositiveName;
    private long votePositiveCount;
    private String voteNegtiveName;
    private long voteNegtiveCount;
    private Date createTime;
    private String url;
    private List<EventTrackVo> eventTracking;

    public List<EventTrackVo> getEventTracking() {
        return eventTracking;
    }

    public void setEventTracking(List<EventTrackVo> eventTracking) {
        this.eventTracking = eventTracking;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getVotePositiveName() {
        return votePositiveName;
    }

    public void setVotePositiveName(String votePositiveName) {
        this.votePositiveName = votePositiveName;
    }

    public long getVotePositiveCount() {
        return votePositiveCount;
    }

    public void setVotePositiveCount(long votePositiveCount) {
        this.votePositiveCount = votePositiveCount;
    }

    public String getVoteNegtiveName() {
        return voteNegtiveName;
    }

    public void setVoteNegtiveName(String voteNegtiveName) {
        this.voteNegtiveName = voteNegtiveName;
    }

    public long getVoteNegtiveCount() {
        return voteNegtiveCount;
    }

    public void setVoteNegtiveCount(long voteNegtiveCount) {
        this.voteNegtiveCount = voteNegtiveCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
