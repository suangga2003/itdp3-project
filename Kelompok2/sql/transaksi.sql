--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2025-03-02 23:04:10

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
-- TOC entry 222 (class 1259 OID 24594)
-- Name: transaksi; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaksi (
    transaksi_id integer NOT NULL,
    akun_id integer,
    jumlah_in double precision,
    jumlah_out double precision,
    rate_id integer,
    user_id integer,
    waktu_transaksi timestamp(6) without time zone
);


ALTER TABLE public.transaksi OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24593)
-- Name: transaksi_transaksi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.transaksi ALTER COLUMN transaksi_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.transaksi_transaksi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 4857 (class 0 OID 24594)
-- Dependencies: 222
-- Data for Name: transaksi; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaksi (transaksi_id, akun_id, jumlah_in, jumlah_out, rate_id, user_id, waktu_transaksi) FROM stdin;
1	1	1506	10	2	1	2025-03-02 15:25:24.32951
2	1	15060	100	2	1	2025-03-02 21:39:21.54297
\.


--
-- TOC entry 4863 (class 0 OID 0)
-- Dependencies: 221
-- Name: transaksi_transaksi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaksi_transaksi_id_seq', 2, true);


--
-- TOC entry 4707 (class 2606 OID 24598)
-- Name: transaksi transaksi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaksi
    ADD CONSTRAINT transaksi_pkey PRIMARY KEY (transaksi_id);


--
-- TOC entry 4708 (class 2606 OID 24617)
-- Name: transaksi fk_account; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaksi
    ADD CONSTRAINT fk_account FOREIGN KEY (akun_id) REFERENCES public.account(id);


--
-- TOC entry 4709 (class 2606 OID 24622)
-- Name: transaksi fk_rate; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaksi
    ADD CONSTRAINT fk_rate FOREIGN KEY (rate_id) REFERENCES public.rate(id);


--
-- TOC entry 4710 (class 2606 OID 24612)
-- Name: transaksi fk_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaksi
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2025-03-02 23:04:11

--
-- PostgreSQL database dump complete
--

