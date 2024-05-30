
CREATE TABLE adrese
(
id VARCHAR2(20),
tara VARCHAR2(100),
judet_sau_regiune VARCHAR(100),
oras VARCHAR(50),
sector NUMBER(2),
strada VARCHAR(100),
nr_cladire NUMBER(4),
bl VARCHAR2(10),
sc VARCHAR2(10),
etaj NUMBER(3),
nr_ap NUMBER(4),
CONSTRAINT adrese_pk PRIMARY KEY(id),
CONSTRAINT adresa_valida_check CHECK(tara IS NOT NULL AND oras IS NOT NULL AND strada IS NOT NULL AND (nr_cladire IS NOT NULL OR
(bl IS NOT NULL AND sc IS NOT NULL AND nr_ap IS NOT NULL)))
);

create table categorie(
id VARCHAR2(20),
denumire varchar2(50) not null,
constraint categorie_pk primary key(id)
);


create table tip_produse(
id VARCHAR2(20),
id_categorie VARCHAR2(20),
denumire varchar2(50) not null,
specificatii varchar2(250),
constraint tip_produse_pk primary key(id),
constraint tip_produse_fk foreign key(id_categorie) references categorie(id) on delete set null
);


create table magazine
(
    id VARCHAR2(20) constraint magazin_pk primary key,
    id_adresa VARCHAR2(20) constraint magazine_adresa_nenula not null,
    constraint magazine_fk foreign key(id_adresa) references adrese(id) on delete set null
);

create table depozite
(
    id VARCHAR2(20) constraint depozit_pk primary key,
    id_adresa VARCHAR2(20) constraint depozite_adresa_nenula not null,
    capacitate number(6),
    constraint depozite_fk foreign key(id_adresa) references adrese(id) on delete set null
);

create table produse_disponibile(
    id VARCHAR2(20) constraint produse_disponibile_pk primary key,
    id_tip_produs VARCHAR2(20),
    pret number(8,2) not null,
    loc_depozitare VARCHAR2(20) not null,
    constraint produse_disponibile_fk_tip_produs foreign key(id_tip_produs) references tip_produse(id) on delete set null
);

create table vanzari
(
    id VARCHAR2(20) constraint vanzari_pk primary key,
    data date not null,
    suma number(8,2) not null,
    nr_card varchar2(16)
);

create table clienti
(
    id VARCHAR2(20) constraint clienti_pk primary key,
    cnp varchar2(13) not null,
    nume varchar2(30) not null,
    prenume varchar2(30),
    nr_tel varchar2(10),
    mail varchar2(50),
    constraint mail_sau_nr_tel check(nr_tel is not null or mail is not null)
);

create table comenzi
(
    id VARCHAR2(20) constraint comenzi_pk primary key,
    data date not null,
    id_client VARCHAR2(20) not null,
    id_adresa VARCHAR2(20) constraint adresa_nenula not null,
    suma number(8,2),
    nr_card varchar2(16),
    constraint comenzi_clienti_fk foreign key(id_client) references clienti(id) on delete set null,
    constraint comenzi_adrese_fk foreign key(id_adresa) references adrese(id) on delete set null
);

create table produse_vandute(
    id VARCHAR2(20) constraint produse_vandute_pk primary key,
    id_tip_produs VARCHAR2(20),
    pret number(8,2) not null,
    id_tranzactie VARCHAR2(20) not null,
    id_livrare VARCHAR2(20),
    constraint produse_vandute_fk_tip_produs foreign key(id_tip_produs) references tip_produse(id) on delete set null
);

create table livrari
(
    id VARCHAR2(20) constraint livrari_pk primary key,
    data date not null,
    distanta_parcursa_km number(7,2)
);

create table istoric_livrari
(
    id VARCHAR2(20) constraint istoric_livrari_pk primary key,
    data date not null,
    distanta_parcursa_km number(7,2)
);

create table produse_vandute_istoric_livrari
(
    id_produs VARCHAR2(20) constraint produse_vandute_istoric_livrari_pk primary key,
    id_livrare VARCHAR2(20),
    constraint produse_vandute_istoric_livrari_produse_fk foreign key(id_produs) references produse_vandute(id) on delete cascade,
    constraint produse_vandute_istoric_livrari_livrari_fk foreign key(id_livrare) references istoric_livrari(id) on delete cascade
);

create table produse_disponibile_livrari
(
    id_produs VARCHAR2(20) constraint produse_disponibile_livrari_pk primary key,
    id_livrare VARCHAR2(20) constraint produse_disponibile_livrari_livrari_nenul not null,
    constraint produse_disponibile_livrari_produse_fk foreign key(id_produs) references produse_disponibile(id) on delete set null,
    constraint produse_disponibile_livrari_livrari_fk foreign key(id_livrare) references livrari(id) on delete set null
);

create table produse_disponibile_comenzi
(
    id_produs VARCHAR2(20) constraint produse_disponibile_comenzi_pk primary key,
    id_comanda VARCHAR2(20) constraint produse_disponibile_comenzi_nenul not null,
    constraint produse_disponibile_comenzi_produse_fk foreign key(id_produs) references produse_disponibile(id) on delete set null,
    constraint produse_disponibile_comenzi_comenzi_fk foreign key(id_comanda) references comenzi(id) on delete set null
);

create table istoric_comenzi
(
    id VARCHAR2(20) constraint istoric_comenzi_pk primary key,
    data date not null,
    id_client VARCHAR2(20),
    id_adresa VARCHAR2(20),
    suma number(8,2),
    nr_card varchar2(16),
    constraint istoric_comenzi_clienti_fk foreign key(id_client) references clienti(id) on delete set null,
    constraint istoric_comenzi_adrese_fk foreign key(id_adresa) references adrese(id) on delete set null
);