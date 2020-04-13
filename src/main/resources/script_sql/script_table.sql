    DROP SEQUENCE IF EXISTS public.topo_id_seq CASCADE;
    CREATE SEQUENCE public.topo_id_seq;

    DROP TABLE IF EXISTS public.topo CASCADE;
    CREATE TABLE public.topo (
                    id INTEGER NOT NULL DEFAULT nextval('public.topo_id_seq'),
                    nom VARCHAR(70) NOT NULL,
                    description VARCHAR(1000) NOT NULL,
                    lieu VARCHAR(100) NOT NULL,
                    date_parution DATE NOT NULL,
                    disponibilite VARCHAR NOT NULL,
                    emprunteur INTEGER,
                    demandeur INTEGER,
                    site_id INTEGER NOT NULL,
                    utilisateur_createur INTEGER NOT NULL,
                    date_creation TIMESTAMP NOT NULL,
                    utilisateur_modif INTEGER,
                    date_modif TIMESTAMP,
                    CONSTRAINT topo_pk PRIMARY KEY (id)
    );


    ALTER SEQUENCE public.topo_id_seq OWNED BY public.topo.id;

    DROP SEQUENCE IF EXISTS public.utilisateur_id_seq CASCADE;
    CREATE SEQUENCE public.utilisateur_id_seq;

    DROP TABLE IF EXISTS public.utilisateur CASCADE;
    CREATE TABLE public.utilisateur (
                    id INTEGER NOT NULL DEFAULT nextval('public.utilisateur_id_seq'),
                    pseudo VARCHAR(15) NOT NULL,
                    nom VARCHAR(30) NOT NULL,
                    prenom VARCHAR(30) NOT NULL,
                    email VARCHAR(50) NOT NULL,
                    password VARCHAR(255) NOT NULL,
                    statut VARCHAR(15) NOT NULL,
                    actif boolean NOT NULL,
                    telephone VARCHAR(15),
                    utilisateur_createur INTEGER NOT NULL,
                    date_creation TIMESTAMP NOT NULL,
                    utilisateur_modif INTEGER,
                    date_modif TIMESTAMP,
                    CONSTRAINT utilisateur_pk PRIMARY KEY (id)
    );

    ALTER SEQUENCE public.utilisateur_id_seq OWNED BY public.utilisateur.id;

    DROP SEQUENCE IF EXISTS public.site_id_seq CASCADE;
    CREATE SEQUENCE public.site_id_seq;

    DROP TABLE IF EXISTS public.site CASCADE;
    CREATE TABLE public.site (
                    id INTEGER NOT NULL DEFAULT nextval('public.site_id_seq'),
                    nom VARCHAR(30) NOT NULL,
                    pays VARCHAR(30) NOT NULL,
                    region VARCHAR(30) NOT NULL,
                    ville VARCHAR(30) NOT NULL,
                    nombre_secteur INTEGER NOT NULL,
                    tag boolean NOT NULL,
                    utilisateur_createur INTEGER NOT NULL,
                    date_creation TIMESTAMP NOT NULL,
                    utilisateur_modif INTEGER,
                    date_modif TIMESTAMP,
                    CONSTRAINT site_pk PRIMARY KEY (id)
    );

    DROP SEQUENCE IF EXISTS public.secteur_id_seq CASCADE;
    CREATE SEQUENCE public.secteur_id_seq;

    DROP TABLE IF EXISTS public.secteur CASCADE;
    CREATE TABLE public.secteur (
                    id INTEGER NOT NULL DEFAULT nextval('public.secteur_id_seq'),
                    nom VARCHAR NOT NULL,
                    site_id INTEGER NOT NULL,
                    nombre_voie INTEGER NOT NULL,
                    utilisateur_createur INTEGER NOT NULL,
                    date_creation TIMESTAMP NOT NULL,
                    utilisateur_modif INTEGER,
                    date_modif TIMESTAMP,
                    CONSTRAINT secteur_pk PRIMARY KEY (id)
    );

    ALTER SEQUENCE public.secteur_id_seq OWNED BY public.secteur.id;

    DROP SEQUENCE IF EXISTS public.voie_id_seq CASCADE;
    CREATE SEQUENCE public.voie_id_seq;

    DROP TABLE IF EXISTS public.voie CASCADE;
    CREATE TABLE public.voie (
                    id INTEGER NOT NULL DEFAULT nextval('public.voie_id_seq'),
                    numero_voie INTEGER NOT NULL,
                    secteur_id INTEGER NOT NULL,
                    nombre_longueur INTEGER NOT NULL,
                    cotation VARCHAR(2) NOT NULL,
                    utilisateur_createur INTEGER NOT NULL,
                    date_creation Timestamp NOT NULL,
                    utilisateur_modif INTEGER,
                    date_modif Timestamp,
                    CONSTRAINT voie_pk PRIMARY KEY (id)
    );


    ALTER SEQUENCE public.voie_id_seq OWNED BY public.voie.id;

    DROP SEQUENCE IF EXISTS public.reservation_topo_id_seq CASCADE;
    CREATE SEQUENCE public.reservation_topo_id_seq;

    DROP TABLE IF EXISTS public.reservation_topo CASCADE;
    CREATE TABLE public.reservation_topo (
                    id INTEGER NOT NULL DEFAULT nextval('public.reservation_topo_id_seq'),
                    topo_id INTEGER NOT NULL,
                    utilisateur_createur INTEGER NOT NULL,
                    reponse_demande VARCHAR(10) NOT NULL,
                    date_creation TIMESTAMP,
                    utilisateur_modif INTEGER,
                    date_modif TIMESTAMP,
                    CONSTRAINT reservation_topo_pk PRIMARY KEY (id)
    );

    DROP SEQUENCE IF EXISTS public.commentaire_id_seq CASCADE;
    CREATE SEQUENCE public.commentaire_id_seq;

    DROP TABLE IF EXISTS public.commentaire CASCADE;
    CREATE TABLE public.commentaire (
                    id INTEGER NOT NULL DEFAULT nextval('public.commentaire_id_seq'),
                    site_id INTEGER NOT NULL,
                    utilisateur_createur INTEGER NOT NULL,
                    commentaire VARCHAR(500) NOT NULL,
                    date_creation TIMESTAMP,
                    utilisateur_modif INTEGER,
                    date_modif TIMESTAMP,
                    CONSTRAINT commentaire_pk PRIMARY KEY (id)
    );

    ALTER TABLE public.commentaire ADD CONSTRAINT site_commentaire_fk
    FOREIGN KEY (site_id)
    REFERENCES public.site (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    NOT DEFERRABLE;

    ALTER TABLE public.commentaire ADD CONSTRAINT utilisateur_commentaire_fk
    FOREIGN KEY (utilisateur_createur)
    REFERENCES public.utilisateur (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    NOT DEFERRABLE;

    ALTER TABLE public.secteur ADD CONSTRAINT site_secteur_fk
    FOREIGN KEY (site_id)
    REFERENCES public.site (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    NOT DEFERRABLE;

    ALTER TABLE public.voie ADD CONSTRAINT secteur_voie_fk
    FOREIGN KEY (secteur_id)
    REFERENCES public.secteur (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    NOT DEFERRABLE;

    ALTER TABLE public.topo ADD CONSTRAINT utilisateur_topo_fk
    FOREIGN KEY (utilisateur_createur)
    REFERENCES public.utilisateur (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    NOT DEFERRABLE;

    ALTER TABLE public.reservation_topo ADD CONSTRAINT topo_reservation_topo_fk
    FOREIGN KEY (topo_id)
    REFERENCES public.topo (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    NOT DEFERRABLE;

    ALTER TABLE public.reservation_topo ADD CONSTRAINT utilisateur_reservation_topo_fk
    FOREIGN KEY (utilisateur_createur)
    REFERENCES public.utilisateur (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    NOT DEFERRABLE;