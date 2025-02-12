create table sys_file_info
(
    file_id      int auto_increment comment '文件id'
        primary key,
    file_name    varchar(50)  default '' null comment '文件名称',
    file_path    varchar(255) default '' null comment '文件路径',
    size         bigint       default 0  null comment '文件大小',
    fileMimeType varchar(255)            null comment '文件的MIME类型',
    parent_id    int                     null comment '父文件夹ID，根目录为NULL',
    is_folder    tinyint(1)   default 0  null comment '是否是文件夹，0-文件，1-文件夹'
)
    comment '文件信息表' charset = utf8mb3;


