package com.zcgx.ticNews.service;

import com.zcgx.ticNews.po.AccessToken;

public interface AccessTokenService {
    int deleteByPrimaryKey(long id);

    int insert(AccessToken record);

    int insertSelective(AccessToken accessToken);

    AccessToken selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(AccessToken accessToken);

    int updateByPrimaryKey(AccessToken accessToken);
}
