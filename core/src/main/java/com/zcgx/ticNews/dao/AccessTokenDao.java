package com.zcgx.ticNews.dao;

import com.zcgx.ticNews.po.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 *
 */
public interface AccessTokenDao extends JpaRepository<AccessToken, Long>, JpaSpecificationExecutor<AccessToken> {
    @Query(value = "select * from jianbao.tbl_access_token where id=?1", nativeQuery = true)
    AccessToken findById(long id);

}
