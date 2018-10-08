use jianbao;
drop table `tbl_article_list`;
create table `tbl_article_list`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`title` varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
	`summary` varchar(500) NOT NULL DEFAULT '' COMMENT '摘要',
	`content` varchar(2000) NOT NULL DEFAULT '' COMMENT '内容',
	`source` varchar(100) NOT NULL DEFAULT '' COMMENT '来源',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`url` varchar(255) NOT NULL DEFAULT '' COMMENT '页面url',
	`vote_positive_name` varchar(20) NOT NULL DEFAULT '' COMMENT '正方名称',
	`vote_positive_count` int(11) NOT NULL DEFAULT 0 COMMENT '正方数量',
	`vote_negtive_name` varchar(20) NOT NULL DEFAULT '' COMMENT '反方名称',
	`vote_negtive_count` int(11) NOT NULL DEFAULT 0 COMMENT '反方数量',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章列表';

drop table `tbl_tag`;
create table `tbl_tag`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`tag_name` varchar(20) NOT NULL DEFAULT '' COMMENT '标签名称',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';

drop table `tbl_article_tag`;
create table `tbl_article_tag`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`tag_id` int(11) COMMENT '标签ID',
	`article_id` int(11) COMMENT '文章ID',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章标签关联表';

drop table `tbl_user`;
create table `tbl_user`(
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `subscribe` int(1) COMMENT '是否订阅公众号, 1:订阅, 2:未订阅',
 `openid` varchar(28) COMMENT '用户公众号的openid(公众号和网页是一样的)',
 `unionid` varchar(28) COMMENT '统一的用户unionid',
 `subscribe_newspaper` int(1) COMMENT '是否订阅早报, 1:订阅, 2:未订阅',
 `user_source` int(1) COMMENT '用户来源, 1: 公众号, 2:小程序, 3: 其它',
 `mp_openid` varchar(28) COMMENT '小程序的openid',
 `remark` varchar(255) COMMENT '备注',
 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

drop table `tbl_access_token`;
create table `tbl_access_token`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`access_token` varchar(255) COMMENT 'Token',
	`expiresIn` varchar(255) COMMENT '有效期',
	`create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='access_token表';
