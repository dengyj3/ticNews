package com.zcgx.ticNews.dao;

import com.zcgx.ticNews.po.FTBanList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 */
public interface FTBanListDao extends JpaRepository<FTBanList, Long>, JpaSpecificationExecutor<FTBanList> {
    @Query(value = "select * from jianbao.tbl_ftban order by id desc limit ?1,?2", nativeQuery = true)
    List<FTBanList> findAll(int pageNo, int pageSize);
    @Query(value = "select * from jianbao.tbl_ftban where id=?1", nativeQuery = true)
    FTBanList findById(long id);

    FTBanList findByApplySn(String applySn);
}
