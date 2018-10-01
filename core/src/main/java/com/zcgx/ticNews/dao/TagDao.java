package com.zcgx.ticNews.dao;

import com.zcgx.ticNews.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 */
public interface TagDao extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

}
