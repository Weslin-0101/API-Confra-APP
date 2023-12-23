CREATE DATABASE appconfradb;

CREATE TYPE ROLE as ENUM ('ADMIN', 'USER');

CREATE TABLE IF NOT EXISTS public._department (
    id_department BIGSERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL,
    supervisor VARCHAR(50) NOT NULL,
    dt_registration TIMESTAMP NOT NULL,

    CONSTRAINT PK_department PRIMARY KEY (id_department)
);

CREATE TABLE IF NOT EXISTS public._user (
    id_user uuid NOT NULL,
    id_Department BIGINT NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    dt_registration TIMESTAMP NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    total_installments INTEGER NOT NULL,
    total_installments_paid INTEGER NOT NULL,
    base64QRCode bytea NULL,
    role ROLE DEFAULT 'USER' NOT NULL,

    CONSTRAINT PK_user PRIMARY KEY (id_user),
    CONSTRAINT FK_user_department FOREIGN KEY (id_Department) REFERENCES public._department (id_department)
);