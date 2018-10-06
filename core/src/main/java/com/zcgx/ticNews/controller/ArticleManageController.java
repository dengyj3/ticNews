package com.zcgx.ticNews.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zcgx.ticNews.dto.ArticleDTO;
import com.zcgx.ticNews.po.Article;
import com.zcgx.ticNews.po.Tag;
import com.zcgx.ticNews.po.TagArticleRelation;
import com.zcgx.ticNews.service.ArticleService;
import com.zcgx.ticNews.service.TagArticleRelationService;
import com.zcgx.ticNews.service.TagService;
import com.zcgx.ticNews.util.PageList;
import com.zcgx.ticNews.util.Response;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/manage")
public class ArticleManageController {
    private final static Logger logger = LoggerFactory.getLogger(ArticleManageController.class);
    @Autowired
    ArticleService articleService;
    @Autowired
    TagService tagService;
    @Autowired
    TagArticleRelationService tagArticleRelationService;

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response<String> addArticle(@RequestBody JSONObject jsonObject){
        try {
            Article article = new Article();
            article.setTitle(jsonObject.getString("title"));
            article.setSummary(jsonObject.getString("summary"));
            article.setContent(jsonObject.getString("content"));
            article.setUrl(jsonObject.getString("url"));
            article.setSource(jsonObject.getString("source"));
            article.setVotePositiveName(jsonObject.getString("votePositiveName"));
            article.setVotePositiveCount(jsonObject.getInteger("votePositiveCount"));
            article.setVoteNegtiveName(jsonObject.getString("voteNegtiveName"));
            article.setVoteNegtiveCount(jsonObject.getInteger("voteNegtiveCount"));
            articleService.addArticle(article);
            System.out.println(article.getId());
            List<String> tagList = (List<String>) jsonObject.get("tags");
            if (tagList.size()>0) {
                tagList.stream().forEach(t -> {
                    Tag tag = new Tag();
                    tag.setTagName(t);
                    tagService.addTag(tag);
                    TagArticleRelation tagArticleRelation = new TagArticleRelation();
                    tagArticleRelation.setArticleId(article.getId());
                    tagArticleRelation.setTagId(tag.getId());
                    tagArticleRelationService.addTagArticleRelation(tagArticleRelation);
                });
            }

            return Response.ok("SUCCESS");
        }catch (Exception e){
            logger.error("新增文章失败! " + e);
            return Response.error("新增文章失败! " + e);
        }
    }

    @RequestMapping(value = "/getArticle")
    public Response<Article> getArticle(@RequestParam(required = true) long id){
        try {
            Article article = articleService.queryArticle(id);
            return Response.ok(article);
        }catch (Exception e){
            logger.error("获取文章失败! " + e);
            return Response.error("获取文章失败! " + e);
        }
    }

    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response<String> updateArticle(@RequestBody JSONObject jsonObject){
        try {
            long id = jsonObject.getLong("id");
            Article article = articleService.queryArticle(id);
            article.setTitle(jsonObject.getString("title"));
            article.setSummary(jsonObject.getString("summary"));
            article.setContent(jsonObject.getString("content"));
            article.setUrl(jsonObject.getString("url"));
            article.setVotePositiveName(jsonObject.getString("votePositiveName"));
            article.setVotePositiveCount(jsonObject.getLong("votePositiveCount"));
            article.setVoteNegtiveName(jsonObject.getString("voteNegtiveName"));
            article.setVoteNegtiveCount(jsonObject.getLong("voteNegtiveCount"));
            article.setSource(jsonObject.getString("source"));
            article.setUpdateTime(new Date());
            article.setId(id);
            articleService.updateArticle(article);
            return Response.ok("SUCCESS");
        }catch (Exception e){
            logger.error("更新文章失败! " + e);
            return Response.error("更新文章失败! " + e);
        }
    }

    public Response<String> deleteArticle(@RequestParam(required = true) long id){
        try{
            articleService.deleteArticle(id);
            return Response.ok("SUCCESS");
        }catch (Exception e){
            logger.error("删除文章失败! " + e);
            return Response.error("删除文章失败! " + e);
        }
    }
}
