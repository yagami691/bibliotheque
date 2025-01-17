--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.1

-- Started on 2025-01-17 19:34:49

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 16434)
-- Name: emprunt; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.emprunt (
    idemprunt integer NOT NULL,
    membre_id integer,
    livre_id integer,
    dateemprunt date NOT NULL,
    dateretourprevue date NOT NULL,
    dateretoureffective date
);


ALTER TABLE public.emprunt OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16454)
-- Name: emprunt_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.emprunt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.emprunt_id_seq OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16433)
-- Name: emprunt_idemprunt_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.emprunt_idemprunt_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.emprunt_idemprunt_seq OWNER TO postgres;

--
-- TOC entry 4824 (class 0 OID 0)
-- Dependencies: 221
-- Name: emprunt_idemprunt_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.emprunt_idemprunt_seq OWNED BY public.emprunt.idemprunt;


--
-- TOC entry 218 (class 1259 OID 16416)
-- Name: livre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livre (
    id integer NOT NULL,
    titre character varying(255) NOT NULL,
    auteur character varying(255) NOT NULL,
    categorie character varying(255),
    nombreexemplaires integer DEFAULT 0,
    CONSTRAINT livre_nombreexemplaires_check CHECK ((nombreexemplaires >= 0))
);


ALTER TABLE public.livre OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16415)
-- Name: livre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.livre_id_seq OWNER TO postgres;

--
-- TOC entry 4827 (class 0 OID 0)
-- Dependencies: 217
-- Name: livre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.livre_id_seq OWNED BY public.livre.id;


--
-- TOC entry 220 (class 1259 OID 16427)
-- Name: membre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.membre (
    id integer NOT NULL,
    nom character varying(100) NOT NULL,
    prenom character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    adhesiondate date
);


ALTER TABLE public.membre OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16426)
-- Name: membre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.membre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.membre_id_seq OWNER TO postgres;

--
-- TOC entry 4830 (class 0 OID 0)
-- Dependencies: 219
-- Name: membre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.membre_id_seq OWNED BY public.membre.id;


--
-- TOC entry 4655 (class 2604 OID 16437)
-- Name: emprunt idemprunt; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt ALTER COLUMN idemprunt SET DEFAULT nextval('public.emprunt_idemprunt_seq'::regclass);


--
-- TOC entry 4652 (class 2604 OID 16419)
-- Name: livre id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre ALTER COLUMN id SET DEFAULT nextval('public.livre_id_seq'::regclass);


--
-- TOC entry 4654 (class 2604 OID 16430)
-- Name: membre id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membre ALTER COLUMN id SET DEFAULT nextval('public.membre_id_seq'::regclass);


--
-- TOC entry 4815 (class 0 OID 16434)
-- Dependencies: 222
-- Data for Name: emprunt; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.emprunt (idemprunt, membre_id, livre_id, dateemprunt, dateretourprevue, dateretoureffective) FROM stdin;
8	4	4	2025-01-17	2025-01-31	\N
\.


--
-- TOC entry 4811 (class 0 OID 16416)
-- Dependencies: 218
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.livre (id, titre, auteur, categorie, nombreexemplaires) FROM stdin;
2	kara	kiri	roman	203
3	la pieuvre	kens	aventure	20
4	Passi	theko	roman	20
\.


--
-- TOC entry 4813 (class 0 OID 16427)
-- Dependencies: 220
-- Data for Name: membre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.membre (id, nom, prenom, email, adhesiondate) FROM stdin;
4	kento	jean	jeanKento@gmail.com	2025-02-01
5	pens	ken	pensKen@gmail.com	2025-01-18
\.


--
-- TOC entry 4832 (class 0 OID 0)
-- Dependencies: 223
-- Name: emprunt_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.emprunt_id_seq', 1, false);


--
-- TOC entry 4833 (class 0 OID 0)
-- Dependencies: 221
-- Name: emprunt_idemprunt_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.emprunt_idemprunt_seq', 8, true);


--
-- TOC entry 4834 (class 0 OID 0)
-- Dependencies: 217
-- Name: livre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livre_id_seq', 4, true);


--
-- TOC entry 4835 (class 0 OID 0)
-- Dependencies: 219
-- Name: membre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.membre_id_seq', 5, true);


--
-- TOC entry 4662 (class 2606 OID 16439)
-- Name: emprunt emprunt_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_pkey PRIMARY KEY (idemprunt);


--
-- TOC entry 4658 (class 2606 OID 16425)
-- Name: livre livre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livre
    ADD CONSTRAINT livre_pkey PRIMARY KEY (id);


--
-- TOC entry 4660 (class 2606 OID 16432)
-- Name: membre membre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.membre
    ADD CONSTRAINT membre_pkey PRIMARY KEY (id);


--
-- TOC entry 4663 (class 2606 OID 16445)
-- Name: emprunt emprunt_livre_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_livre_id_fkey FOREIGN KEY (livre_id) REFERENCES public.livre(id) ON DELETE CASCADE;


--
-- TOC entry 4664 (class 2606 OID 16440)
-- Name: emprunt emprunt_membre_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_membre_id_fkey FOREIGN KEY (membre_id) REFERENCES public.membre(id) ON DELETE CASCADE;


--
-- TOC entry 4822 (class 0 OID 0)
-- Dependencies: 222
-- Name: TABLE emprunt; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.emprunt TO pens;


--
-- TOC entry 4823 (class 0 OID 0)
-- Dependencies: 223
-- Name: SEQUENCE emprunt_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.emprunt_id_seq TO pens;


--
-- TOC entry 4825 (class 0 OID 0)
-- Dependencies: 221
-- Name: SEQUENCE emprunt_idemprunt_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.emprunt_idemprunt_seq TO pens;


--
-- TOC entry 4826 (class 0 OID 0)
-- Dependencies: 218
-- Name: TABLE livre; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.livre TO pens;


--
-- TOC entry 4828 (class 0 OID 0)
-- Dependencies: 217
-- Name: SEQUENCE livre_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE public.livre_id_seq TO pens;


--
-- TOC entry 4829 (class 0 OID 0)
-- Dependencies: 220
-- Name: TABLE membre; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.membre TO pens;


--
-- TOC entry 4831 (class 0 OID 0)
-- Dependencies: 219
-- Name: SEQUENCE membre_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.membre_id_seq TO pens;


-- Completed on 2025-01-17 19:34:52

--
-- PostgreSQL database dump complete
--

