PGDMP     2                    {         	   ecommerce     15.3 (Ubuntu 15.3-1.pgdg20.04+1)     15.3 (Ubuntu 15.3-1.pgdg20.04+1) "    )           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            *           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            +           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ,           1262    16774 	   ecommerce    DATABASE     u   CREATE DATABASE ecommerce WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';
    DROP DATABASE ecommerce;
                postgres    false            �            1259    16810 	   cart_item    TABLE     w   CREATE TABLE public.cart_item (
    cart_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer
);
    DROP TABLE public.cart_item;
       public         heap    postgres    false            �            1259    16787    client    TABLE     �   CREATE TABLE public.client (
    client_id integer NOT NULL,
    first_name character varying(60) NOT NULL,
    email character varying(100) NOT NULL
);
    DROP TABLE public.client;
       public         heap    postgres    false            �            1259    16786    client_client_id_seq    SEQUENCE     �   CREATE SEQUENCE public.client_client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.client_client_id_seq;
       public          postgres    false    217            -           0    0    client_client_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.client_client_id_seq OWNED BY public.client.client_id;
          public          postgres    false    216            �            1259    16776    product    TABLE     .  CREATE TABLE public.product (
    product_id integer NOT NULL,
    name character varying(60) NOT NULL,
    price double precision NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT positive_price CHECK ((price > (0)::double precision)),
    CONSTRAINT positive_quanity CHECK ((quantity >= 0))
);
    DROP TABLE public.product;
       public         heap    postgres    false            �            1259    16775    product_product_id_seq    SEQUENCE     �   CREATE SEQUENCE public.product_product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.product_product_id_seq;
       public          postgres    false    215            .           0    0    product_product_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.product_product_id_seq OWNED BY public.product.product_id;
          public          postgres    false    214            �            1259    16796    shopping_cart    TABLE     �   CREATE TABLE public.shopping_cart (
    cart_id integer NOT NULL,
    client_id integer,
    totalprice double precision DEFAULT 0.00
);
 !   DROP TABLE public.shopping_cart;
       public         heap    postgres    false            �            1259    16795    shopping_cart_cart_id_seq    SEQUENCE     �   CREATE SEQUENCE public.shopping_cart_cart_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.shopping_cart_cart_id_seq;
       public          postgres    false    219            /           0    0    shopping_cart_cart_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.shopping_cart_cart_id_seq OWNED BY public.shopping_cart.cart_id;
          public          postgres    false    218            |           2604    16790    client client_id    DEFAULT     t   ALTER TABLE ONLY public.client ALTER COLUMN client_id SET DEFAULT nextval('public.client_client_id_seq'::regclass);
 ?   ALTER TABLE public.client ALTER COLUMN client_id DROP DEFAULT;
       public          postgres    false    217    216    217            {           2604    16779    product product_id    DEFAULT     x   ALTER TABLE ONLY public.product ALTER COLUMN product_id SET DEFAULT nextval('public.product_product_id_seq'::regclass);
 A   ALTER TABLE public.product ALTER COLUMN product_id DROP DEFAULT;
       public          postgres    false    214    215    215            }           2604    16799    shopping_cart cart_id    DEFAULT     ~   ALTER TABLE ONLY public.shopping_cart ALTER COLUMN cart_id SET DEFAULT nextval('public.shopping_cart_cart_id_seq'::regclass);
 D   ALTER TABLE public.shopping_cart ALTER COLUMN cart_id DROP DEFAULT;
       public          postgres    false    218    219    219            &          0    16810 	   cart_item 
   TABLE DATA           B   COPY public.cart_item (cart_id, product_id, quantity) FROM stdin;
    public          postgres    false    220   u'       #          0    16787    client 
   TABLE DATA           >   COPY public.client (client_id, first_name, email) FROM stdin;
    public          postgres    false    217   �'       !          0    16776    product 
   TABLE DATA           D   COPY public.product (product_id, name, price, quantity) FROM stdin;
    public          postgres    false    215   �'       %          0    16796    shopping_cart 
   TABLE DATA           G   COPY public.shopping_cart (cart_id, client_id, totalprice) FROM stdin;
    public          postgres    false    219   �(       0           0    0    client_client_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.client_client_id_seq', 15, true);
          public          postgres    false    216            1           0    0    product_product_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.product_product_id_seq', 25, true);
          public          postgres    false    214            2           0    0    shopping_cart_cart_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.shopping_cart_cart_id_seq', 15, true);
          public          postgres    false    218            �           2606    16814    cart_item cart_item_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY public.cart_item
    ADD CONSTRAINT cart_item_pkey PRIMARY KEY (cart_id, product_id);
 B   ALTER TABLE ONLY public.cart_item DROP CONSTRAINT cart_item_pkey;
       public            postgres    false    220    220            �           2606    16794    client client_email_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_email_key UNIQUE (email);
 A   ALTER TABLE ONLY public.client DROP CONSTRAINT client_email_key;
       public            postgres    false    217            �           2606    16792    client client_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (client_id);
 <   ALTER TABLE ONLY public.client DROP CONSTRAINT client_pkey;
       public            postgres    false    217            �           2606    16785    product product_name_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_name_key UNIQUE (name);
 B   ALTER TABLE ONLY public.product DROP CONSTRAINT product_name_key;
       public            postgres    false    215            �           2606    16783    product product_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public            postgres    false    215            �           2606    16804 )   shopping_cart shopping_cart_client_id_key 
   CONSTRAINT     i   ALTER TABLE ONLY public.shopping_cart
    ADD CONSTRAINT shopping_cart_client_id_key UNIQUE (client_id);
 S   ALTER TABLE ONLY public.shopping_cart DROP CONSTRAINT shopping_cart_client_id_key;
       public            postgres    false    219            �           2606    16802     shopping_cart shopping_cart_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.shopping_cart
    ADD CONSTRAINT shopping_cart_pkey PRIMARY KEY (cart_id);
 J   ALTER TABLE ONLY public.shopping_cart DROP CONSTRAINT shopping_cart_pkey;
       public            postgres    false    219            �           2606    16830     cart_item cart_item_cart_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cart_item
    ADD CONSTRAINT cart_item_cart_id_fkey FOREIGN KEY (cart_id) REFERENCES public.shopping_cart(cart_id) ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.cart_item DROP CONSTRAINT cart_item_cart_id_fkey;
       public          postgres    false    219    3212    220            �           2606    16835 #   cart_item cart_item_product_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cart_item
    ADD CONSTRAINT cart_item_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(product_id) ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.cart_item DROP CONSTRAINT cart_item_product_id_fkey;
       public          postgres    false    215    220    3204            �           2606    16825 *   shopping_cart shopping_cart_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.shopping_cart
    ADD CONSTRAINT shopping_cart_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.client(client_id) ON DELETE CASCADE;
 T   ALTER TABLE ONLY public.shopping_cart DROP CONSTRAINT shopping_cart_client_id_fkey;
       public          postgres    false    219    217    3208            &      x������ � �      #      x������ � �      !   #  x�E�AN�0E�ߧ��bǎ�e����B�
VlLj�Fi\9)��Xp vls1&A���y�����=?>��&hQ;��`��#�`����
�TX���u�}���T�,6!����ch��`�vp�a�`���l��VX)	W��癅?O_��ߟ�\��%���K��<����	hP7�9�/R�RCjǔ�6v��C>��5�![������CZT֠R�Kl<�F�BP���wah��r��6��j����'�J��*�KJ��ojG6��y�t��.˔�.��ҏ	Tbؓ`�� ��i      %      x������ � �     