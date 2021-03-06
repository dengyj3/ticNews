package com.zcgx.ticNews.service;

import com.zcgx.ticNews.dto.ArticleDTO;
import com.zcgx.ticNews.dto.DailyArticleDTO;
import com.zcgx.ticNews.po.Article;
import com.zcgx.ticNews.util.PageList;
import com.zcgx.ticNews.util.Response;


public interface ArticleService {
    Response<PageList<ArticleDTO>> queryArticleList(int pageNo, int pageSize) throws Exception;

    Response<ArticleDTO> queryArticleDetail(long id, String unionid) throws Exception;

    Response<String> operation(long id, String vote, String unionid) throws Exception;

    int addArticle(Article article) throws Exception;

    int updateArticle(Article article) throws Exception;

    Article queryArticle(long id) throws Exception;

    int deleteArticle(long id) throws Exception;

    Response<DailyArticleDTO> queryDailyArticle(String date) throws Exception;
}
