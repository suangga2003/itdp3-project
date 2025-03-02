--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4
-- Dumped by pg_dump version 16.4

-- Started on 2025-03-02 19:57:29

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 229 (class 1255 OID 25347)
-- Name: update_timestamp(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.update_timestamp() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  NEW.updated_at = CURRENT_TIMESTAMP;
  RETURN NEW;
END;
$$;


ALTER FUNCTION public.update_timestamp() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 25220)
-- Name: m_currency; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.m_currency (
    currency_id integer NOT NULL,
    currency_name character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.m_currency OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 25219)
-- Name: m_currency_currency_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.m_currency_currency_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.m_currency_currency_id_seq OWNER TO postgres;

--
-- TOC entry 4934 (class 0 OID 0)
-- Dependencies: 215
-- Name: m_currency_currency_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.m_currency_currency_id_seq OWNED BY public.m_currency.currency_id;


--
-- TOC entry 220 (class 1259 OID 25242)
-- Name: m_status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.m_status (
    status_id integer NOT NULL,
    status_name character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.m_status OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 25241)
-- Name: m_status_status_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.m_status_status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.m_status_status_id_seq OWNER TO postgres;

--
-- TOC entry 4935 (class 0 OID 0)
-- Dependencies: 219
-- Name: m_status_status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.m_status_status_id_seq OWNED BY public.m_status.status_id;


--
-- TOC entry 218 (class 1259 OID 25231)
-- Name: m_transaction_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.m_transaction_type (
    type_id integer NOT NULL,
    type_name character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.m_transaction_type OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 25230)
-- Name: m_transaction_type_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.m_transaction_type_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.m_transaction_type_type_id_seq OWNER TO postgres;

--
-- TOC entry 4936 (class 0 OID 0)
-- Dependencies: 217
-- Name: m_transaction_type_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.m_transaction_type_type_id_seq OWNED BY public.m_transaction_type.type_id;


--
-- TOC entry 222 (class 1259 OID 25253)
-- Name: s_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.s_user (
    user_id integer NOT NULL,
    username character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.s_user OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 25252)
-- Name: s_user_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.s_user_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.s_user_user_id_seq OWNER TO postgres;

--
-- TOC entry 4937 (class 0 OID 0)
-- Dependencies: 221
-- Name: s_user_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.s_user_user_id_seq OWNED BY public.s_user.user_id;


--
-- TOC entry 224 (class 1259 OID 25264)
-- Name: t_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_account (
    account_id integer NOT NULL,
    user_id integer NOT NULL,
    currency_id integer NOT NULL,
    balance numeric(20,10) DEFAULT 0,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.t_account OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 25263)
-- Name: t_account_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_account_account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.t_account_account_id_seq OWNER TO postgres;

--
-- TOC entry 4938 (class 0 OID 0)
-- Dependencies: 223
-- Name: t_account_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_account_account_id_seq OWNED BY public.t_account.account_id;


--
-- TOC entry 226 (class 1259 OID 25286)
-- Name: t_exchange_rate; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_exchange_rate (
    rate_id integer NOT NULL,
    currency_from integer NOT NULL,
    currency_to integer NOT NULL,
    rate numeric(20,10) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.t_exchange_rate OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 25285)
-- Name: t_exchange_rate_rate_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_exchange_rate_rate_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.t_exchange_rate_rate_id_seq OWNER TO postgres;

--
-- TOC entry 4939 (class 0 OID 0)
-- Dependencies: 225
-- Name: t_exchange_rate_rate_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_exchange_rate_rate_id_seq OWNED BY public.t_exchange_rate.rate_id;


--
-- TOC entry 228 (class 1259 OID 25307)
-- Name: t_transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_transaction (
    transaction_id integer NOT NULL,
    account_from integer NOT NULL,
    account_to integer NOT NULL,
    currency_id integer NOT NULL,
    amount numeric(20,10) NOT NULL,
    rate_id integer,
    type_id integer,
    status_id integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    currency integer
);


ALTER TABLE public.t_transaction OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 25306)
-- Name: t_transaction_transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_transaction_transaction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.t_transaction_transaction_id_seq OWNER TO postgres;

--
-- TOC entry 4940 (class 0 OID 0)
-- Dependencies: 227
-- Name: t_transaction_transaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_transaction_transaction_id_seq OWNED BY public.t_transaction.transaction_id;


--
-- TOC entry 4719 (class 2604 OID 25223)
-- Name: m_currency currency_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_currency ALTER COLUMN currency_id SET DEFAULT nextval('public.m_currency_currency_id_seq'::regclass);


--
-- TOC entry 4725 (class 2604 OID 25245)
-- Name: m_status status_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_status ALTER COLUMN status_id SET DEFAULT nextval('public.m_status_status_id_seq'::regclass);


--
-- TOC entry 4722 (class 2604 OID 25234)
-- Name: m_transaction_type type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_transaction_type ALTER COLUMN type_id SET DEFAULT nextval('public.m_transaction_type_type_id_seq'::regclass);


--
-- TOC entry 4728 (class 2604 OID 25256)
-- Name: s_user user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.s_user ALTER COLUMN user_id SET DEFAULT nextval('public.s_user_user_id_seq'::regclass);


--
-- TOC entry 4731 (class 2604 OID 25267)
-- Name: t_account account_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_account ALTER COLUMN account_id SET DEFAULT nextval('public.t_account_account_id_seq'::regclass);


--
-- TOC entry 4735 (class 2604 OID 25289)
-- Name: t_exchange_rate rate_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_exchange_rate ALTER COLUMN rate_id SET DEFAULT nextval('public.t_exchange_rate_rate_id_seq'::regclass);


--
-- TOC entry 4738 (class 2604 OID 25310)
-- Name: t_transaction transaction_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_transaction ALTER COLUMN transaction_id SET DEFAULT nextval('public.t_transaction_transaction_id_seq'::regclass);


--
-- TOC entry 4916 (class 0 OID 25220)
-- Dependencies: 216
-- Data for Name: m_currency; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.m_currency (currency_id, currency_name, created_at, updated_at) FROM stdin;
1	IDR	2025-02-28 13:41:09.699943	2025-02-28 13:41:09.699943
2	USD	2025-02-28 13:41:20.320757	2025-02-28 13:41:20.320757
3	YEN	2025-02-28 13:41:20.32374	2025-02-28 13:41:20.32374
\.


--
-- TOC entry 4920 (class 0 OID 25242)
-- Dependencies: 220
-- Data for Name: m_status; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.m_status (status_id, status_name, created_at, updated_at) FROM stdin;
1	SUCCESS	2025-02-28 13:42:35.785315	2025-02-28 13:42:35.785315
2	PENDING	2025-02-28 13:42:35.78894	2025-02-28 13:42:35.78894
3	FAILED	2025-02-28 13:42:35.790291	2025-02-28 13:42:35.790291
\.


--
-- TOC entry 4918 (class 0 OID 25231)
-- Dependencies: 218
-- Data for Name: m_transaction_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.m_transaction_type (type_id, type_name, created_at, updated_at) FROM stdin;
1	EXCHANGE	2025-02-28 13:45:22.963171	2025-02-28 13:45:22.963171
2	DEPOSIT	2025-02-28 13:45:22.966969	2025-02-28 13:45:22.966969
3	WITHDRAW	2025-02-28 13:45:22.968373	2025-02-28 13:45:22.968373
\.


--
-- TOC entry 4922 (class 0 OID 25253)
-- Dependencies: 222
-- Data for Name: s_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.s_user (user_id, username, email, password, created_at, updated_at) FROM stdin;
1	prdvfrzzbbr	humbalang22@gmail.com	12345678	2025-03-01 08:34:49.343	2025-03-01 08:34:49.343
2	cinnamonpass	123@yahoo.co.id	1029384857	2025-03-01 11:10:08.81	2025-03-01 11:10:08.81
\.


--
-- TOC entry 4924 (class 0 OID 25264)
-- Dependencies: 224
-- Data for Name: t_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.t_account (account_id, user_id, currency_id, balance, created_at, updated_at) FROM stdin;
3	1	2	12343465.1100000000	2025-03-01 09:55:52.479	2025-03-01 09:55:52.479
2	1	1	12343335.1100000000	2025-03-01 09:50:25.281	2025-03-02 15:57:49.830964
11	2	2	0.0015455760	2025-03-01 14:40:21.183	2025-03-02 15:57:49.830964
4	1	3	12343445.1100000000	2025-03-01 10:24:37.898	2025-03-02 16:14:11.347845
10	2	1	9.0000000000	2025-03-01 14:40:00.825	2025-03-02 16:21:06.785613
12	2	3	20.0138769182	2025-03-01 14:40:27.242	2025-03-02 16:21:06.785613
\.


--
-- TOC entry 4926 (class 0 OID 25286)
-- Dependencies: 226
-- Data for Name: t_exchange_rate; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.t_exchange_rate (rate_id, currency_from, currency_to, rate, created_at, updated_at) FROM stdin;
3	2	1	16536.5500000000	2025-02-28 14:01:15.987473	2025-03-01 09:29:41.285306
5	3	1	109.7600000000	2025-02-28 14:01:15.99052	2025-03-01 09:32:35.295622
4	2	3	150.6600000000	2025-02-28 14:01:15.989076	2025-03-01 09:33:59.414317
1	1	2	0.0000128798	2025-02-28 13:57:24.326453	2025-03-01 09:43:56.076132
2	1	3	0.0138769182	2025-02-28 14:01:15.983596	2025-03-01 09:43:56.081062
6	3	2	0.0178349899	2025-02-28 14:01:15.991834	2025-03-01 09:43:56.08224
\.


--
-- TOC entry 4928 (class 0 OID 25307)
-- Dependencies: 228
-- Data for Name: t_transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.t_transaction (transaction_id, account_from, account_to, currency_id, amount, rate_id, type_id, status_id, created_at, updated_at, currency) FROM stdin;
3	2	11	1	10.0000000000	\N	\N	1	2025-03-02 15:47:20.577	2025-03-02 15:47:20.577	\N
4	2	11	1	10.0000000000	1	\N	1	2025-03-02 15:54:03.245	2025-03-02 15:54:03.245	\N
5	2	11	1	10.0000000000	1	1	1	2025-03-02 15:57:49.845	2025-03-02 15:57:49.845	\N
6	11	11	2	10.0000000000	\N	\N	3	2025-03-02 16:09:48.358	2025-03-02 16:09:48.358	\N
7	12	11	3	10.0000000000	\N	\N	3	2025-03-02 16:12:01.939	2025-03-02 16:12:01.94	\N
8	4	12	3	10.0000000000	\N	1	1	2025-03-02 16:13:19.322	2025-03-02 16:13:19.322	\N
9	4	12	3	10.0000000000	\N	1	1	2025-03-02 16:14:11.351	2025-03-02 16:14:11.351	\N
10	10	12	1	1.0000000000	2	1	1	2025-03-02 16:21:06.793	2025-03-02 16:21:06.793	\N
\.


--
-- TOC entry 4941 (class 0 OID 0)
-- Dependencies: 215
-- Name: m_currency_currency_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.m_currency_currency_id_seq', 4, true);


--
-- TOC entry 4942 (class 0 OID 0)
-- Dependencies: 219
-- Name: m_status_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.m_status_status_id_seq', 3, true);


--
-- TOC entry 4943 (class 0 OID 0)
-- Dependencies: 217
-- Name: m_transaction_type_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.m_transaction_type_type_id_seq', 3, true);


--
-- TOC entry 4944 (class 0 OID 0)
-- Dependencies: 221
-- Name: s_user_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.s_user_user_id_seq', 2, true);


--
-- TOC entry 4945 (class 0 OID 0)
-- Dependencies: 223
-- Name: t_account_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_account_account_id_seq', 13, true);


--
-- TOC entry 4946 (class 0 OID 0)
-- Dependencies: 225
-- Name: t_exchange_rate_rate_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_exchange_rate_rate_id_seq', 8, true);


--
-- TOC entry 4947 (class 0 OID 0)
-- Dependencies: 227
-- Name: t_transaction_transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_transaction_transaction_id_seq', 10, true);


--
-- TOC entry 4742 (class 2606 OID 25229)
-- Name: m_currency m_currency_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_currency
    ADD CONSTRAINT m_currency_pkey PRIMARY KEY (currency_id);


--
-- TOC entry 4746 (class 2606 OID 25251)
-- Name: m_status m_status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_status
    ADD CONSTRAINT m_status_pkey PRIMARY KEY (status_id);


--
-- TOC entry 4744 (class 2606 OID 25240)
-- Name: m_transaction_type m_transaction_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.m_transaction_type
    ADD CONSTRAINT m_transaction_type_pkey PRIMARY KEY (type_id);


--
-- TOC entry 4748 (class 2606 OID 25262)
-- Name: s_user s_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.s_user
    ADD CONSTRAINT s_user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4750 (class 2606 OID 25274)
-- Name: t_account t_account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_account
    ADD CONSTRAINT t_account_pkey PRIMARY KEY (account_id);


--
-- TOC entry 4752 (class 2606 OID 25295)
-- Name: t_exchange_rate t_exchange_rate_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_exchange_rate
    ADD CONSTRAINT t_exchange_rate_pkey PRIMARY KEY (rate_id);


--
-- TOC entry 4754 (class 2606 OID 25316)
-- Name: t_transaction t_transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_transaction
    ADD CONSTRAINT t_transaction_pkey PRIMARY KEY (transaction_id);


--
-- TOC entry 4769 (class 2620 OID 25354)
-- Name: t_account set_timestamp_before_update_account; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER set_timestamp_before_update_account BEFORE UPDATE ON public.t_account FOR EACH ROW EXECUTE FUNCTION public.update_timestamp();


--
-- TOC entry 4765 (class 2620 OID 25348)
-- Name: m_currency set_timestamp_before_update_currency; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER set_timestamp_before_update_currency BEFORE UPDATE ON public.m_currency FOR EACH ROW EXECUTE FUNCTION public.update_timestamp();


--
-- TOC entry 4770 (class 2620 OID 25352)
-- Name: t_exchange_rate set_timestamp_before_update_exchange_rate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER set_timestamp_before_update_exchange_rate BEFORE UPDATE ON public.t_exchange_rate FOR EACH ROW EXECUTE FUNCTION public.update_timestamp();


--
-- TOC entry 4767 (class 2620 OID 25350)
-- Name: m_status set_timestamp_before_update_status; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER set_timestamp_before_update_status BEFORE UPDATE ON public.m_status FOR EACH ROW EXECUTE FUNCTION public.update_timestamp();


--
-- TOC entry 4771 (class 2620 OID 25353)
-- Name: t_transaction set_timestamp_before_update_transaction; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER set_timestamp_before_update_transaction BEFORE UPDATE ON public.t_transaction FOR EACH ROW EXECUTE FUNCTION public.update_timestamp();


--
-- TOC entry 4766 (class 2620 OID 25349)
-- Name: m_transaction_type set_timestamp_before_update_transaction_type; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER set_timestamp_before_update_transaction_type BEFORE UPDATE ON public.m_transaction_type FOR EACH ROW EXECUTE FUNCTION public.update_timestamp();


--
-- TOC entry 4768 (class 2620 OID 25351)
-- Name: s_user set_timestamp_before_update_user; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER set_timestamp_before_update_user BEFORE UPDATE ON public.s_user FOR EACH ROW EXECUTE FUNCTION public.update_timestamp();


--
-- TOC entry 4755 (class 2606 OID 25280)
-- Name: t_account t_account_currency_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_account
    ADD CONSTRAINT t_account_currency_id_fkey FOREIGN KEY (currency_id) REFERENCES public.m_currency(currency_id);


--
-- TOC entry 4756 (class 2606 OID 25275)
-- Name: t_account t_account_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_account
    ADD CONSTRAINT t_account_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.s_user(user_id);


--
-- TOC entry 4757 (class 2606 OID 25296)
-- Name: t_exchange_rate t_exchange_rate_currency_from_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_exchange_rate
    ADD CONSTRAINT t_exchange_rate_currency_from_fkey FOREIGN KEY (currency_from) REFERENCES public.m_currency(currency_id);


--
-- TOC entry 4758 (class 2606 OID 25301)
-- Name: t_exchange_rate t_exchange_rate_currency_to_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_exchange_rate
    ADD CONSTRAINT t_exchange_rate_currency_to_fkey FOREIGN KEY (currency_to) REFERENCES public.m_currency(currency_id);


--
-- TOC entry 4759 (class 2606 OID 25317)
-- Name: t_transaction t_transaction_account_from_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_transaction
    ADD CONSTRAINT t_transaction_account_from_fkey FOREIGN KEY (account_from) REFERENCES public.t_account(account_id);


--
-- TOC entry 4760 (class 2606 OID 25322)
-- Name: t_transaction t_transaction_account_to_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_transaction
    ADD CONSTRAINT t_transaction_account_to_fkey FOREIGN KEY (account_to) REFERENCES public.t_account(account_id);


--
-- TOC entry 4761 (class 2606 OID 25327)
-- Name: t_transaction t_transaction_currency_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_transaction
    ADD CONSTRAINT t_transaction_currency_id_fkey FOREIGN KEY (currency_id) REFERENCES public.m_currency(currency_id);


--
-- TOC entry 4762 (class 2606 OID 25332)
-- Name: t_transaction t_transaction_rate_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_transaction
    ADD CONSTRAINT t_transaction_rate_id_fkey FOREIGN KEY (rate_id) REFERENCES public.t_exchange_rate(rate_id);


--
-- TOC entry 4763 (class 2606 OID 25342)
-- Name: t_transaction t_transaction_status_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_transaction
    ADD CONSTRAINT t_transaction_status_id_fkey FOREIGN KEY (status_id) REFERENCES public.m_status(status_id);


--
-- TOC entry 4764 (class 2606 OID 25337)
-- Name: t_transaction t_transaction_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_transaction
    ADD CONSTRAINT t_transaction_type_id_fkey FOREIGN KEY (type_id) REFERENCES public.m_transaction_type(type_id);


-- Completed on 2025-03-02 19:57:30

--
-- PostgreSQL database dump complete
--

