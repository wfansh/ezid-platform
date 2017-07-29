DROP TABLE ezid_manual_task_reject_reason;
CREATE TABLE ezid_manual_task_reject_reason (id int NOT NULL AUTO_INCREMENT, parent_id int NOT NULL, name varchar(100), full_name varchar(255), time_created date, time_updated date, PRIMARY KEY (id)) ENGINE=MyISAM DEFAULT CHARSET=utf8;
DROP TABLE ezid_adapter_invalid_cache;
CREATE TABLE ezid_adapter_invalid_cache (id bigint NOT NULL AUTO_INCREMENT, idcard_num varchar(45) NOT NULL, name varchar(45), error varchar(255), message varchar(255), time_created timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (id), CONSTRAINT idx_joint UNIQUE (idcard_num, name)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE ezid_photo_cache;
CREATE TABLE ezid_photo_cache (id bigint NOT NULL AUTO_INCREMENT, idcard_num varchar(45) NOT NULL, name varchar(45), photo_uri varchar(255), photo_type smallint NOT NULL, time_created timestamp DEFAULT CURRENT_TIMESTAMP NULL, PRIMARY KEY (id), CONSTRAINT idx_joint UNIQUE (idcard_num, name)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE ezid_platform_landscape;
CREATE TABLE ezid_platform_landscape (id bigint NOT NULL AUTO_INCREMENT, resource_type varchar(25) NOT NULL, resource_name varchar(50) NOT NULL, url varchar(500), enabled tinyint(1) NOT NULL, time_updated timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (id), CONSTRAINT unikey_type_name UNIQUE (resource_type, resource_name)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE ezid_platform_topology;
CREATE TABLE ezid_platform_topology (id bigint NOT NULL AUTO_INCREMENT, module_id bigint NOT NULL, engine_id bigint NOT NULL, task_executor_id bigint, username varchar(50), password varchar(50), status varchar(10) DEFAULT 'ON' NOT NULL, description varchar(500), time_executed timestamp NULL, time_updated timestamp DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (id), CONSTRAINT unikey_app_tm UNIQUE (module_id, task_executor_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE ezid_callback_failover_exchange;
CREATE TABLE ezid_callback_failover_exchange (id bigint NOT NULL AUTO_INCREMENT, process_instance_id VARCHAR(255) NOT NULL, callback_url VARCHAR(512), enabled TINYINT(1) NOT NULL, time_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (id)) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO ezid_platform_landscape (id, resource_type, resource_name, url, enabled, time_updated) VALUES (1, 'task_executor', 'preprocess', '', true, '2014-06-25 21:33:25');
INSERT INTO ezid_platform_landscape (id, resource_type, resource_name, url, enabled, time_updated) VALUES (2, 'task_executor', 'adapter_nciic', '', true, '2014-06-25 21:35:02');
INSERT INTO ezid_platform_landscape (id, resource_type, resource_name, url, enabled, time_updated) VALUES (3, 'task_executor', 'adapter_loong', '', true, '2014-06-25 21:35:02');
INSERT INTO ezid_platform_landscape (id, resource_type, resource_name, url, enabled, time_updated) VALUES (4, 'task_executor', 'machine', '', true, '2014-06-05 20:21:43');
INSERT INTO ezid_platform_landscape (id, resource_type, resource_name, url, enabled, time_updated) VALUES (5, 'task_executor', 'manual', '', true, '2014-06-05 20:21:43');
