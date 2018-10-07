package com.zcgx.ticNews.dao;

import com.zcgx.ticNews.po.User;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户表
 */
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query(value = "select * from jianbao.tbl_user where unionid=?1", nativeQuery = true)
    User findByUnionId(String unionid);

    @Query(value = "select * from jianbao.tbl_user where subscribe_newspaper=1", nativeQuery = true)
    List<User> findBySubscribeNewspaper();
}
