package com.zcgx.ticNews.po;

import javax.persistence.*;

@Entity
@Table(name = "tbl_article_list")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String summary;
    private String content;
    private String createTime;
    private String updateTime;
    private String url;
    private String votePositiveName;
    private long votePositiveCount;
    private String voteNegtiveName;
    private long voteNegtiveCount;

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


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", url='" + url + '\'' +
                ", votePositiveName='" + votePositiveName + '\'' +
                ", votePositiveCount=" + votePositiveCount +
                ", voteNegtiveName='" + voteNegtiveName + '\'' +
                ", voteNegtiveCount=" + voteNegtiveCount +
                '}';
    }
}
