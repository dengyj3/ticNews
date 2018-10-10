package com.zcgx.ticNews.po;

import javax.persistence.*;

@Entity
@Table(name = "tbl_vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String unionid;
    private long articleId;
    private String vote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", unionid='" + unionid + '\'' +
                ", articleId=" + articleId +
                ", vote='" + vote + '\'' +
                '}';
    }
}
