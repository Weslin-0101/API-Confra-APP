CREATE TABLE IF NOT EXISTS public._user (
    id uuid NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    dt_registration TIMESTAMP NOT NULL,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    total_installments INTEGER NOT NULL,
    total_installments_paid INTEGER NOT NULL,
    verification_code VARCHAR(64) NOT NULL,
    verified boolean DEFAULT false,
    base64QRCode bytea NULL DEFAULT NULL,
    check_in boolean DEFAULT false,

    CONSTRAINT PK_user PRIMARY KEY (id)
);