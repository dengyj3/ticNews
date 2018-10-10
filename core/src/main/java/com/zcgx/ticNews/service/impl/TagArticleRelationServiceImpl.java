package com.zcgx.ticNews.service.impl;

import com.zcgx.ticNews.dao.TagArticleRelationDao;
import com.zcgx.ticNews.po.TagArticleRelation;
import com.zcgx.ticNews.service.TagArticleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tagArticleRelationService")
public class TagArticleRelationServiceImpl implements TagArticleRelationService {
    @Autowired
    TagArticleRelationDao tagArticleRelationDao;
    @Override
    public TagArticleRelation addTagArticleRelation(TagArticleRelation tagArticleRelation) {
        return tagArticleRelationDao.saveAndFlush(tagArticleRelation);
    }

    @Override
    public int deleteTagArticleRelation(long id) {
        tagArticleRelationDao.deleteById(id);
        return 0;
    }

    @Override
    public TagArticleRelation modifyTagArticleRelation(TagArticleRelation tagArticleRelation) {
        tagArticleRelation = tagArticleRelationDao.saveAndFlush(tagArticleRelation);
        return tagArticleRelation;
    }

    @Override
    public List<Long> queryTagArticleRelation(List<Long> tagId) {
        return tagArticleRelationDao.findByTagId(tagId);
    }

    @Override
    public List<TagArticleRelation> deleteTagArticleRelationByArticleId(long articleId) {
        return tagArticleRelationDao.deleteByArticleId(articleId);
    }

    @Override
    public List<TagArticleRelation> deleteTagArticleRelationByTagId(long tagId) {
        return tagArticleRelationDao.deleteByTagId(tagId);
    }
}
