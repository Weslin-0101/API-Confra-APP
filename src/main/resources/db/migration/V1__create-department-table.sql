CREATE TABLE IF NOT EXISTS public._department (
    id_department SERIAL NOT NULL,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(100) NOT NULL,
    supervisor VARCHAR(50) NOT NULL,
    dt_registration TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dt_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT PK_department PRIMARY KEY (id_department)
);