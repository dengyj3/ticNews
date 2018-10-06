package com.zcgx.ticNews.service.impl;

import com.zcgx.ticNews.dao.TagDao;
import com.zcgx.ticNews.po.Tag;
import com.zcgx.ticNews.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tagService")
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;

    @Override
    public Tag addTag(Tag tag) {
        tag = tagDao.saveAndFlush(tag);
        return tag;
    }

    @Override
    public int deleteTag(long id) {
        tagDao.deleteById(id);
        return 0;
    }

    @Override
    public int modifyTag(Tag tag) {
        tagDao.saveAndFlush(tag);
        return 0;
    }

    @Override
    public Tag queryTag(long id) {
        Tag tag = tagDao.findById(id);
        return tag;
    }
}
