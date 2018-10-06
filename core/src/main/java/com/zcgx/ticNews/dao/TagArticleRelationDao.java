package com.zcgx.ticNews.dao;

import com.zcgx.ticNews.po.TagArticleRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
public interface TagArticleRelationDao extends JpaRepository<TagArticleRelation, Long>, JpaSpecificationExecutor<TagArticleRelation> {
    @Query(value = "select * from tic.tbl_article_tag where article_id=?1", nativeQuery = true)
    List<TagArticleRelation> findByArticleId(long articleId);

    @Transactional
    @Modifying
    @Query(value = "delete from tic.tbl_article_tag where article_id=?1", nativeQuery = true)
    List<TagArticleRelation> deleteByArticleId(long articleId);

    @Transactional
    @Modifying
    @Query(value = "delete from tic.tbl_article_tag where tag_id=?1", nativeQuery = true)
    List<TagArticleRelation> deleteByTagId(long tagId);
}
