package com.zcgx.ticNews.dao;

import com.zcgx.ticNews.po.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 *
 */
public interface VoteDao extends JpaRepository<Vote, Long>, JpaSpecificationExecutor<Vote> {
    @Query(value = "select * from jianbao.tbl_tag where id=?1", nativeQuery = true)
    Vote findById(long id);
    @Query(value = "select * from jianbao.tbl_tag where tag_name=?1", nativeQuery = true)
    Vote findByTagName(String tagName);
}
