package com.zcgx.ticNews.service;

import com.zcgx.ticNews.po.TagArticleRelation;

import java.util.List;

public interface TagArticleRelationService {
    TagArticleRelation addTagArticleRelation(TagArticleRelation tagArticleRelation);
    int deleteTagArticleRelation(long id);
    TagArticleRelation modifyTagArticleRelation(TagArticleRelation tagArticleRelation);
    List<Long> queryTagArticleRelation(List<Long> tagId);

    int deleteTagArticleRelationByArticleId(long articleId);
    int deleteTagArticleRelationByTagId(long tagId);
}
