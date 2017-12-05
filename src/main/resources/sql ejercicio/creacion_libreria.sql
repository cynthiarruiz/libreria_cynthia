CREATE SEQUENCE public.libros_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.libros_id_seq
    OWNER TO postgres;
-- Table: public.libros

-- DROP TABLE public.libros;

CREATE TABLE public.libros
(
    id integer NOT NULL DEFAULT nextval('libros_id_seq'::regclass),
    titulo text COLLATE pg_catalog."default" NOT NULL,
    categoria text COLLATE pg_catalog."default" NOT NULL,
    autor text COLLATE pg_catalog."default" NOT NULL,
    url text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT libros_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.libros
    OWNER to postgres;