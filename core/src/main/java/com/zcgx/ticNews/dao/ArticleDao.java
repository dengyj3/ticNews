package com.zcgx.ticNews.dao;

import com.zcgx.ticNews.dto.EventTrackVo;
import com.zcgx.ticNews.po.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;

import java.util.List;

/**
 *
 */
public interface ArticleDao extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    @Query(value = "select * from jianbao.tbl_article_list order by id desc limit ?1,?2", nativeQuery = true)
    List<Article> findAll(int pageNo, int pageSize);
    @Query(value = "select * from jianbao.tbl_article_list where id=?1", nativeQuery = true)
    Article findById(long id);
    @Query(value = "select t1.* from jianbao.tbl_article_list t1 left join jianbao.tbl_article_tag t2 on t1.id=t2.article_id where t1.id in (?1) order by t1.id desc", nativeQuery = true)
    List<Article> findEventTrackByArticleId(List<Long> articleId);

    @Modifying
    @Transactional
    @Query(value = "update jianbao.tbl_article_list set vote_positive_count=vote_positive_count+1 where id=?1", nativeQuery = true)
    int updateVotePositiveCountById(long id);

    @Modifying
    @Transactional
    @Query(value = "update jianbao.tbl_article_list set vote_negtive_count=vote_negtive_count+1 where id=?1", nativeQuery = true)
    int updateVoteNegtiveCountById(long id);

    @Query(value = "select * from jianbao.tbl_article_list where DATE_FORMAT(create_time,'%Y-%m-%d') = ?1 order by id desc limit 10", nativeQuery = true)
    List<Article> findArticleByCreateTime(String date);
}
