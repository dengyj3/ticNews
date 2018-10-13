package com.zcgx.ticNews.controller;

import com.alibaba.fastjson.JSONObject;
import com.zcgx.ticNews.dto.ArticleDTO;
import com.zcgx.ticNews.service.ArticleService;
import com.zcgx.ticNews.util.DateUtils;
import com.zcgx.ticNews.util.PageList;
import com.zcgx.ticNews.util.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    private final static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    ArticleService articleService;

    @ApiOperation(value = "获取文章列表", notes = "根据请求获取文章列表")
    @RequestMapping(value = "/queryArticle",method = RequestMethod.GET)
    public Response<PageList<ArticleDTO>> queryArticleList(@RequestParam(required = true) int pageNo, @RequestParam(required = true) int pageSize){
        try {
            if (pageNo < 1){
                pageNo = 0 * pageSize;
            }else {
                pageNo = (pageNo-1) * pageSize;
            }
            return articleService.queryArticleList(pageNo, pageSize);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取文章列表失败! " + e);
            return Response.error("获取文章列表失败! " + e);
        }
    }
    @ApiOperation(value = "获取文章明细", notes = "根据文章ID获取文章详情")
    @RequestMapping(value = "/queryArticleDetail", method = RequestMethod.GET)
    public Response<ArticleDTO> queryArticleDetail(@RequestParam(required = true) long id, @RequestParam(required = false) String unionid){
        try {
            return articleService.queryArticleDetail(id, unionid);
        }catch (Exception e){
            logger.error("获取文章详情失败! " + e);
            return Response.error("获取文章详情失败! " + e);
        }
    }
    @ApiOperation(value = "用户表态", notes = "用户表态")
    @ApiImplicitParam(name = "jsonObject", value = "用户表态", required = true, dataType = "Article")
    @RequestMapping(value = "/operation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response<String> operation(@RequestBody JSONObject jsonObject){
        try {
            long id = jsonObject.getInteger("id");
            String vote = jsonObject.getString("vote");
            String unionid = jsonObject.getString("unionid");
            return articleService.operation(id, vote, unionid);
        }catch (Exception e){
            logger.error("用户表态失败! " + e);
            return Response.error("用户表态失败! " + e);
        }
    }


    @ApiOperation(value = "获取早报文章列表", notes = "根据请求日期文章列表")
    @RequestMapping(value = "/daily/{date}",method = RequestMethod.GET)
    public Response<List<ArticleDTO>> dailyArticle(@PathVariable(required = true) String date){
        try {
            logger.info("receive date is : " + date);
            if (StringUtils.isNotBlank(date)){
                date = DateUtils.getYesterDay(date);
            }
            return articleService.queryDailyArticle(date);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取文章列表失败! " + e);
            return Response.error("获取文章列表失败! " + e);
        }
    }
}
