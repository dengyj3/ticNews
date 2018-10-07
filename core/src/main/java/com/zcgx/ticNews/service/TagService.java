package com.zcgx.ticNews.service;

import com.zcgx.ticNews.po.Tag;


public interface TagService {
    Tag addTag(Tag tag);
    int deleteTag(long id);
    int modifyTag(Tag tag);
    Tag queryTag(long id);
}
