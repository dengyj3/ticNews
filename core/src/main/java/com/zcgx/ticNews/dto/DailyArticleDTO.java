package com.zcgx.ticNews.dto;

import java.util.List;

public class DailyArticleDTO {
    private String today;
    private String weekOfToday;
    private List<ArticleDTO> articleDTOList;

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getWeekOfToday() {
        return weekOfToday;
    }

    public void setWeekOfToday(String weekOfToday) {
        this.weekOfToday = weekOfToday;
    }

    public List<ArticleDTO> getArticleDTOList() {
        return articleDTOList;
    }

    public void setArticleDTOList(List<ArticleDTO> articleDTOList) {
        this.articleDTOList = articleDTOList;
    }
}
