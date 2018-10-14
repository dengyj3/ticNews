package com.zcgx.ticNews.dao;

import com.zcgx.ticNews.po.UrlConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 *
 */
public interface UrlConfigDao extends JpaRepository<UrlConfig, Long>, JpaSpecificationExecutor<UrlConfig> {
    @Query(value = "select * from jianbao.tbl_url_config where id=?1", nativeQuery = true)
    UrlConfig findById(long id);

}
