package com.zcgx.ticNews.service;

import com.zcgx.ticNews.po.Tag;

import java.util.List;

public interface TagService {
    Tag addTag(Tag tag);
    int deleteTag(long id);
    int modifyTag(Tag tag);
    Tag queryTag(long id);
}
