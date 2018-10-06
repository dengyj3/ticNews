use tic;

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
	`open_id` int(11) COMMENT 'open ID',
	`union_id` int(11) COMMENT 'union ID',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

drop table `tbl_web_log`;
CREATE TABLE `tbl_web_log`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `client_ip` varchar(100) NOT NULL COMMENT '客户端IP',
    `url` varchar(255) NOT NULL COMMENT '请求URL',
    `type` varchar(10) NOT NULL COMMENT '请求方式',
    `user_name` varchar(128) NOT NULL COMMENT '用户名',
    `class_method` varchar(255) NOT NULL COMMENT '请求的方法名称'，
    `args` varchar(255) NOT NULL COMMENT '请求参数',
    `request_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '请求时间',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='web日志表';