CREATE TABLE IF NOT EXISTS public._department (
    id_department BIGSERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL,
    supervisor VARCHAR(50) NOT NULL,
    dt_registration TIMESTAMP NOT NULL,

    CONSTRAINT PK_department PRIMARY KEY (id_department)
);