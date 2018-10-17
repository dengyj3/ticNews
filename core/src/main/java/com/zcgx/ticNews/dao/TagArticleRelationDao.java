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
    @Query(value = "select distinct article_id from jianbao.tbl_article_tag where tag_id in (?1)", nativeQuery = true)
    List<Integer> findByTagId(List<Long> tagId);

    @Transactional
    @Modifying
    @Query(value = "delete from jianbao.tbl_article_tag where article_id=?1", nativeQuery = true)
    int deleteByArticleId(long articleId);

    @Transactional
    @Modifying
    @Query(value = "delete from jianbao.tbl_article_tag where tag_id=?1", nativeQuery = true)
    int deleteByTagId(long tagId);

    @Query(value = "select tag_id from jianbao.tbl_article_tag where article_id=?1", nativeQuery = true)
    List<Long> findByArticleId(long articleId);
}
