create table service
(
    service_id  bigint   NOT NULL AUTO_INCREMENT COMMENT 'PK',
    description varchar(500) COMMENT '서비스 설명',
    deleted     bit      NOT NULL COMMENT '서비스 삭제 여부',
    created_at  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_service (service_id)
);

create table service_key
(
    service_key_id bigint       NOT NULL AUTO_INCREMENT COMMENT 'PK',
    service_id     bigint       NOT NULL COMMENT 'FK',
    key_value      varchar(100) NOT NULL COMMENT '서비스 아이디를 대체할 값',
    created_at     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_serviceKey (service_key_id),
    UNIQUE KEY IDX_keyValue (key_value)
);

create table service_third_party_info
(
    service_third_party_info_id bigint       NOT NULL AUTO_INCREMENT COMMENT 'PK',
    service_id                  bigint       NOT NULL COMMENT 'FK',
    third_party_type            varchar(20)  NOT NULL COMMENT '서드파티 종류',
    third_party_client_id       varchar(500) NOT NULL COMMENT '서드파티 클라이언트 ID',
    created_at                  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at                  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY PK_serviceThirdPartyInfo (service_third_party_info_id),
    INDEX                       IDX_serviceId (service_id)
);
