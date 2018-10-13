package com.zcgx.ticNews.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zcgx.ticNews.dao.ArticleDao;
import com.zcgx.ticNews.dao.TagArticleRelationDao;
import com.zcgx.ticNews.dao.TagDao;
import com.zcgx.ticNews.dao.VoteDao;
import com.zcgx.ticNews.dto.ArticleDTO;
import com.zcgx.ticNews.dto.EventTrackVo;
import com.zcgx.ticNews.po.Article;
import com.zcgx.ticNews.po.TagArticleRelation;
import com.zcgx.ticNews.po.Vote;
import com.zcgx.ticNews.service.ArticleService;
import com.zcgx.ticNews.service.TagArticleRelationService;
import com.zcgx.ticNews.util.DateUtils;
import com.zcgx.ticNews.util.PageList;
import com.zcgx.ticNews.util.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    private final static Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Autowired
    ArticleDao articleDao;
    @Autowired
    TagArticleRelationDao tagArticleRelationDao;
    @Autowired
    TagArticleRelationService tagArticleRelationService;
    @Autowired
    VoteDao voteDao;

    @Override
    public Response<PageList<ArticleDTO>> queryArticleList(int pageNo, int pageSize)  throws Exception{
        long count = articleDao.count();
        List<Article> articleList = articleDao.findAll(pageNo, pageSize);
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        articleList.stream().forEach(article -> {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(article.getId());
            articleDTO.setCreateTime(DateUtils.getDateStr(article.getCreateTime()));
            articleDTO.setSummary(article.getSummary());
            articleDTO.setTitle(article.getTitle());
            articleDTO.setVotePositiveName(article.getVotePositiveName());
            articleDTO.setVotePositiveCount(article.getVotePositiveCount());
            articleDTO.setVoteNegtiveName(article.getVoteNegtiveName());
            articleDTO.setVoteNegtiveCount(article.getVoteNegtiveCount());
            articleDTO.setUrl(article.getUrl());
            articleDTO.setSource(article.getSource());
            articleDTOList.add(articleDTO);
        });
        PageList<ArticleDTO> pageList = new PageList<ArticleDTO>(pageNo, pageSize, (int)count, articleDTOList);
        return Response.ok(pageList);
    }

    @Override
    public Response<ArticleDTO> queryArticleDetail(long id, String unionid) throws Exception {
        Article article = articleDao.findById(id);
        List<Long> tagIds = tagArticleRelationDao.findByArticleId(id);
        List<Long> articleIds = tagArticleRelationService.queryTagArticleRelation(tagIds);
        articleIds.remove(id);
        List<Article> articleEvent = articleDao.findEventTrackByArticleId(articleIds);
        ArticleDTO articleDTO = new ArticleDTO();
        if (article != null){
            articleDTO.setId(article.getId());
            articleDTO.setTitle(article.getTitle());
            articleDTO.setSummary(article.getSummary());
            articleDTO.setVotePositiveName(article.getVotePositiveName());
            articleDTO.setVotePositiveCount(article.getVotePositiveCount());
            articleDTO.setVoteNegtiveName(article.getVoteNegtiveName());
            articleDTO.setVoteNegtiveCount(article.getVoteNegtiveCount());
            articleDTO.setUrl(article.getUrl());
            articleDTO.setSource(article.getSource());
            if (StringUtils.isNotBlank(unionid)){
                Vote vote = voteDao.findByArticleIdAndUnionid(id, unionid);
                articleDTO.setVote(vote.getVote());
                articleDTO.setUniond(vote.getUnionid());
            }
            List<EventTrackVo> eventTracking = new ArrayList<>();
            articleEvent.stream().forEach(article1 -> {
                EventTrackVo eventTrackVo = new EventTrackVo();
                eventTrackVo.setId(article1.getId());
                eventTrackVo.setTitle(article1.getTitle());
                eventTrackVo.setUrl(article1.getUrl());
                eventTrackVo.setCreateTime(DateUtils.getDateStr(article1.getCreateTime()));
                eventTracking.add(eventTrackVo);
            });
            articleDTO.setEventTracking(eventTracking);
        }
        return Response.ok(articleDTO);
    }

    @Override
    public Response<String> operation(long id, String vote, String unionid)  throws Exception{
        Vote voteEntity = voteDao.findByArticleIdAndUnionid(id, unionid);
        if (voteEntity == null) {
            if ("Y".equals(vote)) {
                articleDao.updateVotePositiveCountById(id);
            } else {
                articleDao.updateVoteNegtiveCountById(id);
            }
            voteEntity = new Vote();
            voteEntity.setArticleId(id);
            voteEntity.setVote(vote);
            voteEntity.setUnionid(unionid);
            voteDao.saveAndFlush(voteEntity);
            return Response.ok("SUCCESS");
        }else {
            return Response.ok("该用户已经投过");
        }
    }

    @Override
    public int addArticle(Article article)  throws Exception{
        articleDao.saveAndFlush(article);
        return 0;
    }

    @Override
    public int updateArticle(Article article)  throws Exception{
        articleDao.saveAndFlush(article);
        return 0;
    }

    @Override
    public Article queryArticle(long id) throws Exception {
        return articleDao.findById(id);
    }

    @Override
    public int deleteArticle(long id)  throws Exception{
        articleDao.deleteById(id);
        return 0;
    }

    @Override
    public Response<List<ArticleDTO>> queryDailyArticle(String date) throws Exception {
        List<Article> articleList = articleDao.findArticleByCreateTime(date);
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        articleList.stream().forEach(article -> {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(article.getId());
            articleDTO.setCreateTime(DateUtils.getDateStr(article.getCreateTime()));
            articleDTO.setSummary(article.getSummary());
            articleDTO.setTitle(article.getTitle());
            articleDTO.setVotePositiveName(article.getVotePositiveName());
            articleDTO.setVotePositiveCount(article.getVotePositiveCount());
            articleDTO.setVoteNegtiveName(article.getVoteNegtiveName());
            articleDTO.setVoteNegtiveCount(article.getVoteNegtiveCount());
            articleDTO.setUrl(article.getUrl());
            articleDTO.setSource(article.getSource());
            articleDTOList.add(articleDTO);
        });
        return Response.ok(articleDTOList);
    }
}
