package com.zcgx.ticNews.dao;

import com.zcgx.ticNews.po.WebLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WebLogDao extends JpaRepository<WebLog, Long>, JpaSpecificationExecutor<WebLog> {
}
