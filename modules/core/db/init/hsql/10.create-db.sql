-- begin DDCHC_HEALTH_CHECK_REPORT
create table DDCHC_HEALTH_CHECK_REPORT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    EXECUTED_AT timestamp not null,
    RESULT_ varchar(50) not null,
    SUMMARY varchar(4000),
    --
    primary key (ID)
)^
-- end DDCHC_HEALTH_CHECK_REPORT
-- begin DDCHC_HEALTH_CHECK_REPORT_DETAIL
create table DDCHC_HEALTH_CHECK_REPORT_DETAIL (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    RESULT_ varchar(50) not null,
    CATEGORY varchar(255),
    MESSAGE varchar(255),
    DETAILED_MESSAGE longvarchar,
    HEALTH_CHECK_RUN_ID varchar(36) not null,
    CONFIGURATION_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end DDCHC_HEALTH_CHECK_REPORT_DETAIL
-- begin DDCHC_HEALTH_CHECK_CONFIGURATION
create table DDCHC_HEALTH_CHECK_CONFIGURATION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ACTIVE boolean,
    HEALTH_CHECK_CLASS varchar(4000) not null,
    DESCRIPTION varchar(4000),
    SOLUTION_INFORMATION varchar(4000),
    --
    primary key (ID)
)^
-- end DDCHC_HEALTH_CHECK_CONFIGURATION
