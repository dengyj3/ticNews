package com.zcgx.ticNews.po;

import javax.persistence.*;

@Entity
@Table(name = "tbl_article_tag")
public class TagArticleRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long tagId;
    private long articleId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "TagArticleRelation{" +
                "id=" + id +
                ", tagId=" + tagId +
                ", articleId=" + articleId +
                '}';
    }
}
