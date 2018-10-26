package com.zcgx.ticNews.dao;

import com.zcgx.ticNews.po.PFList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 *
 */
public interface PFListDao extends JpaRepository<PFList, Long>, JpaSpecificationExecutor<PFList> {
    List<PFList> findByProcessid(String processid);
}
