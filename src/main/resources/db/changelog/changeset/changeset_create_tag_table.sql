--liquibase formatted sql

--changeset nickolay:create_tag_table

CREATE TABLE IF NOT EXISTS TAG
(
    id       bigserial    not null constraint tag_pk primary key,
    tag_name varchar(255) not null constraint tag_uk unique
);

ALTER TABLE task_tag ADD CONSTRAINT UK_TAG_ID UNIQUE (task_id, tag);
ALTER TABLE task_tag ADD CONSTRAINT task_tag_tag_name_fk FOREIGN KEY (tag) REFERENCES tag (tag_name);