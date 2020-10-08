CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `name` varchar(10) default NULL COMMENT '姓名',
  `age` int(2) default NULL COMMENT '年龄',
  `pwd` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
