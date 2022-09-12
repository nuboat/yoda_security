DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS accesses;

CREATE TABLE clients (
  id BIGINT NOT NULL,
  client_name VARCHAR(128),
  vat_id VARCHAR(64),
  is_active BOOLEAN,
  is_public_enrollment BOOLEAN,
  is_single_access BOOLEAN,
  meta_json TEXT,
  creator_id BIGINT NOT NULL,
  created TIMESTAMP WITH TIME ZONE NOT NULL,
  CONSTRAINT clients_pk PRIMARY KEY (id)
);

CREATE TABLE accounts (
  id BIGINT NOT NULL,
  client_id BIGINT,
  is_active BOOLEAN,
  is_verify BOOLEAN,
  is_changepass BOOLEAN,
  account_type INTEGER NOT NULL,
  account_role INTEGER NOT NULL,
  username VARCHAR(64) NOT NULL,
  password_hash VARCHAR(64),
  email VARCHAR(256) NOT NULL,
  firstname VARCHAR(256),
  lastname VARCHAR(256),
  mobile_no VARCHAR(64),
  meta_json TEXT,
  avatar_url VARCHAR(256),
  staff_code VARCHAR(256),
  position VARCHAR(256),
  creator_id BIGINT,
  created TIMESTAMP WITH TIME ZONE,
  CONSTRAINT accounts_pk PRIMARY KEY (id)
);

CREATE TABLE accesses (
  token VARCHAR(256) NOT NULL,
  client_id BIGINT,
  account_id BIGINT NOT NULL,
  access_role INT NOT NULL,
  access_name VARCHAR(64),
  expire TIMESTAMP WITH TIME ZONE NOT NULL,
  meta_json TEXT,
  creator_id BIGINT,
  created TIMESTAMP WITH TIME ZONE NOT NULL,
  CONSTRAINT accesses_pk PRIMARY KEY (token)
);

CREATE TABLE roles (
  id BIGINT,
  role_name VARCHAR(256) NOT NULL,
  creator_id BIGINT,
  created TIMESTAMP WITH TIME ZONE NOT NULL,
  CONSTRAINT roles_pk PRIMARY KEY (id)
);

CREATE TABLE permissions (
  id BIGINT,
  method_id INT,
  endpoint VARCHAR(256) NOT NULL,
  creator_id BIGINT,
  created TIMESTAMP WITH TIME ZONE NOT NULL,
  CONSTRAINT permissions_pk PRIMARY KEY (id)
);

CREATE TABLE role_account (
  role_id BIGINT,
  account_id BIGINT,
  creator_id BIGINT,
  created TIMESTAMP WITH TIME ZONE NOT NULL,
  CONSTRAINT role_account_pk PRIMARY KEY (role_id, account_id)
);

CREATE TABLE role_permission (
  role_id BIGINT,
  permission_id BIGINT,
  creator_id BIGINT,
  created TIMESTAMP WITH TIME ZONE NOT NULL,
  CONSTRAINT role_permission_pk PRIMARY KEY (role_id, permission_id)
);
