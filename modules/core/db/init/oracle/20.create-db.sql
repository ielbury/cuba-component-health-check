-- begin DDCHC_HC_REPORT_DETAIL
alter table DDCHC_HC_REPORT_DETAIL add constraint FK_DDCHC_HCREPDET_HEACHERUN foreign key (HEALTH_CHECK_RUN_ID) references DDCHC_HC_REPORT(ID)^
alter table DDCHC_HC_REPORT_DETAIL add constraint FK_DDCHC_HCREPODETA_CONF foreign key (CONFIGURATION_ID) references DDCHC_HC_CONFIGURATION(ID)^
create index IDX_DDCHC_HCREPDET_HEACHERUN on DDCHC_HC_REPORT_DETAIL (HEALTH_CHECK_RUN_ID)^
create index IDX_DDCHC_HCREPODETA_CONF on DDCHC_HC_REPORT_DETAIL (CONFIGURATION_ID)^
-- end DDCHC_HC_REPORT_DETAIL
-- begin DDCHC_HC_CONFIGURATION
alter table DDCHC_HC_CONFIGURATION add constraint FK_DDCHC_HCCONFIG_CATEGORY foreign key (CATEGORY_ID) references DDCHC_HC_CATEGORY(ID)^
create index IDX_DDCHC_HCCONFIG_CATEGORY on DDCHC_HC_CONFIGURATION (CATEGORY_ID)^
-- end DDCHC_HC_CONFIGURATION