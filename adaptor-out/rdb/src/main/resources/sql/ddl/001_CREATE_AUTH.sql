create table auth_social
(
    social_type varchar(10) NOT NULL,
    social_id   varchar(50) NOT NULL,
    users_id     bigint      NOT NULL,
    created_at  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY auth_social (social_id, social_type)
);

create table users
(
    users_id   bigint   NOT NULL AUTO_INCREMENT,
    name       varchar(20),
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted    bit      NOT NULL,
    PRIMARY KEY PK_users (users_id)
);
