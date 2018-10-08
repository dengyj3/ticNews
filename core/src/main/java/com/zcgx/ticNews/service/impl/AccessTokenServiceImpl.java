package com.zcgx.ticNews.service.impl;

import com.zcgx.ticNews.dao.AccessTokenDao;
import com.zcgx.ticNews.po.AccessToken;
import com.zcgx.ticNews.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "TokenCache")
@Service("accessTokenService")
public class AccessTokenServiceImpl implements AccessTokenService {
    @Autowired
    AccessTokenDao accessTokenDao;

    @Override
    @CacheEvict(key = "#p0")
    public int deleteByPrimaryKey(long id) {
        accessTokenDao.deleteById(id);
        return 0;
    }

    @Override
    @CachePut(key = "#p0")
    public int insert(AccessToken accessToken) {
        accessTokenDao.saveAndFlush(accessToken);
        return 0;
    }

    @Override
    @CachePut(key = "#p0")
    public int insertSelective(AccessToken accessToken) {
        accessTokenDao.saveAndFlush(accessToken);
        return 0;
    }

    @Override
    @Cacheable(key = "#p0")
    public AccessToken selectByPrimaryKey(long id) {
        return accessTokenDao.findById(id);
    }

    @Override
    @Cacheable(key = "#p0")
    public int updateByPrimaryKeySelective(AccessToken accessToken) {
        accessTokenDao.saveAndFlush(accessToken);
        return 0;
    }

    @Override
    @Cacheable(key = "#p0")
    public int updateByPrimaryKey(AccessToken accessToken) {
        accessTokenDao.saveAndFlush(accessToken);
        return 0;
    }
}
