package com.zcgx.ticNews.controller;

import com.zcgx.ticNews.dto.ArticleDTO;
import com.zcgx.ticNews.service.ArticleService;
import com.zcgx.ticNews.util.PageList;
import com.zcgx.ticNews.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    ArticleService articleService;

    @RequestMapping("/queryArticle")
    public Response<PageList<ArticleDTO>> queryArticleList(@RequestParam(required = true) int pageNo, @RequestParam(required = true) int pageSize){
        return articleService.queryArticleList(pageNo, pageSize);
    }
    @RequestMapping("/queryArticleDetail")
    public Response<ArticleDTO> queryArticleDetail(@RequestParam(required = true) long id){
        return articleService.queryArticleDetail(id);
    }

    @RequestMapping(value = "/operation", method = RequestMethod.GET)
    public Response<String> operation(long id, String vote){
        return articleService.operation(id, vote);
    }
}
