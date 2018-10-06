package com.zcgx.ticNews.controller;

import com.alibaba.fastjson.JSONObject;
import com.zcgx.ticNews.dto.ArticleDTO;
import com.zcgx.ticNews.po.Article;
import com.zcgx.ticNews.service.ArticleService;
import com.zcgx.ticNews.util.PageList;
import com.zcgx.ticNews.util.Response;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    ArticleService articleService;

    @ApiOperation(value = "获取文章列表", notes = "根据请求获取文章列表")
    @RequestMapping("/queryArticle")
    public Response<PageList<ArticleDTO>> queryArticleList(@RequestParam(required = true) int pageNo, @RequestParam(required = true) int pageSize){
        try {
            if (pageNo < 1){
                pageNo = 0 * pageSize;
            }else {
                pageNo = (pageNo-1) * pageSize;
            }
            return articleService.queryArticleList(pageNo, pageSize);
        }catch (Exception e){
            logger.error("获取文章列表失败! " + e);
            return Response.error("获取文章列表失败! " + e);
        }
    }
    @RequestMapping("/queryArticleDetail")
    public Response<ArticleDTO> queryArticleDetail(@RequestParam(required = true) long id){
        try {
            return articleService.queryArticleDetail(id);
        }catch (Exception e){
            logger.error("获取文章详情失败! " + e);
            return Response.error("获取文章详情失败! " + e);
        }
    }

    @RequestMapping(value = "/operation", method = RequestMethod.GET)
    public Response<String> operation(long id, String vote){
        try {
            return articleService.operation(id, vote);
        }catch (Exception e){
            logger.error("用户操作失败! " + e);
            return Response.error("用户失败! " + e);
        }
    }


}
