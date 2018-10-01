package com.zcgx.ticNews.dao;

import com.zcgx.ticNews.po.TagArticleRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 */
public interface TagArticleRelationDao extends JpaRepository<TagArticleRelation, Long>, JpaSpecificationExecutor<TagArticleRelation> {

}
