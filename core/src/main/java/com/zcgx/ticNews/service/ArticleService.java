package com.zcgx.ticNews.service;

import com.zcgx.ticNews.dto.ArticleDTO;
import com.zcgx.ticNews.util.PageList;
import com.zcgx.ticNews.util.Response;

import java.util.List;

public interface ArticleService {
    Response<PageList<ArticleDTO>> queryArticleList(int pageNo, int pageSize);

    Response<ArticleDTO> queryArticleDetail(long id);

    Response<String> operation(long id, String vote);
}
