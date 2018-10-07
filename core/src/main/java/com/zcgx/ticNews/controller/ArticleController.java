package com.zcgx.ticNews.controller;

import com.zcgx.ticNews.dto.ArticleDTO;
import com.zcgx.ticNews.service.ArticleService;
import com.zcgx.ticNews.util.PageList;
import com.zcgx.ticNews.util.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    public Response<ArticleDTO> queryArticleDetail(@RequestParam(required = true) long id){
        try {
            return articleService.queryArticleDetail(id);
        }catch (Exception e){
            logger.error("获取文章详情失败! " + e);
            return Response.error("获取文章详情失败! " + e);
        }
    }
    @ApiOperation(value = "用户表态", notes = "用户表态")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "文章id",required = true,dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "vote", value = "用户表态, Y or N", required = true, dataType = "String")})
    @RequestMapping(value = "/operation", method = RequestMethod.GET)
    public Response<String> operation(@RequestParam(required = true) long id, @RequestParam(required = true) String vote){
        try {
            return articleService.operation(id, vote);
        }catch (Exception e){
            logger.error("用户操作失败! " + e);
            return Response.error("用户失败! " + e);
        }
    }


}
