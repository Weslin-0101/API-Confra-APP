CREATE TABLE IF NOT EXISTS public._user (
    id uuid NOT NULL,
    cod_document VARCHAR(11) NOT NULL,
    dt_registration TIMESTAMP NOT NULL,
    desc_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    desc_department VARCHAR(100) NOT NULL,
    total_installments INTEGER NOT NULL,
    total_installments_paid INTEGER NOT NULL,
    base64QRCode bytea NULL,
    role VARCHAR(20) NOT NULL,

    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT "_user_role_check" CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[])))
);