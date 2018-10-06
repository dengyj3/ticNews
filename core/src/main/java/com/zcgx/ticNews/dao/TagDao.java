package com.zcgx.ticNews.dao;

import com.zcgx.ticNews.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 *
 */
public interface TagDao extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {
    @Query(value = "select * from tic.tbl_tag where id=?1", nativeQuery = true)
    Tag findById(long id);
}
