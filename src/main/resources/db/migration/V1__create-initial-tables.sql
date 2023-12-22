CREATE TYPE ROLE as ENUM ('ADMIN', 'USER');

CREATE TABLE IF NOT EXISTS public._user (
    id_user uuid NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    dt_registration TIMESTAMP NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    total_installments INTEGER NOT NULL,
    total_installments_paid INTEGER NOT NULL,
    base64QRCode bytea NULL,
    role ROLE DEFAULT 'USER' NOT NULL,

    CONSTRAINT pk_user PRIMARY KEY (id_user)
);

CREATE TABLE IF NOT EXISTS DEPARTMENT (
    idDepartment INTEGER NOT NULL,
    id_user uuid NOT NULL,
    department VARCHAR(100) NOT NULL,

    CONSTRAINT PK_DEPARTAMENT PRIMARY KEY (idDepartment),
    CONSTRAINT FK_DEPARTAMENT_USER FOREIGN KEY (id_user) REFERENCES _user (id_user)
);