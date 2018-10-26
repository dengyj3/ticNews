package com.zcgx.ticNews.service;

import com.zcgx.ticNews.dto.FTBanDTO;
import com.zcgx.ticNews.po.FTBanList;
import com.zcgx.ticNews.util.PageList;
import com.zcgx.ticNews.util.Response;

public interface FTService {
    String findFTBanList();

    String findPFList(String processid);

    Response<PageList<FTBanList>> queryFTBanList(int pageNo, int pageSize) throws Exception;

    Response<FTBanDTO> queryFTBanDetail(long id) throws Exception;
}
