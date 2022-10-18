create table public.addresses (
                                  id bigint primary key not null default nextval('addresses_id_seq'::regclass),
                                  addresses_active boolean,
                                  city character varying(255),
                                  country character varying(255),
                                  street character varying(255),
                                  id_employee integer,
                                  foreign key (id_employee) references public.users (id)
                                      match simple on update no action on delete no action
);

create table public.cards (
                              id integer primary key not null default nextval('salarycards_id_seq'::regclass),
                              number character varying(255)
);

