create table user
(
    user_id    bigint   NOT NULL AUTO_INCREMENT COMMENT 'PK',
    service_id bigint   NOT NULL COMMENT 'FK',
    name       varchar(20) COMMENT '유저 이름',
    deleted    bit      NOT NULL COMMENT '삭제 여부',
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_user (user_id)
);

create table auth_social
(
    social_type varchar(10) NOT NULL COMMENT 'PK',
    social_id   varchar(50) NOT NULL COMMENT 'PK, 소셜에서 제공해주는 유저 아이디',
    user_id     bigint      NOT NULL COMMENT 'FK',
    created_at  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_auth_social (social_id, social_type)
);


CREATE TABLE auth_refresh_token
(
    refresh_token_id bigint   NOT NULL AUTO_INCREMENT,
    refresh_token    binary(45) NOT NULL,
    user_id          bigint   NOT NULL,
    expired_at       DATETIME NOT NULL,
    created_at       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_authRefreshToken (refresh_token_id),
    UNIQUE KEY idx_refreshToken (refresh_token)
);
