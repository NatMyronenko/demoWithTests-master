create table public.users (
                              id integer primary key not null default nextval(),
                              country character varying(255),
                              email character varying(255),
                              name character varying(255),
                              phone character varying,
                              address character varying,
                              salary integer,
                              workdays integer,
                              employee_id integer,
                              tax_id bigint,
                              gender character varying(255),
                              foreign key (tax_id) references public.taxcode (id)
                                  match simple on update no action on delete no action,
                              foreign key (employee_id) references public.cards (id)
                                  match simple on update no action on delete no action
);

create table public.flyway_schema_history (
                                              installed_rank integer primary key not null,
                                              version character varying(50),
                                              description character varying(200) not null,
                                              type character varying(20) not null,
                                              script character varying(1000) not null,
                                              checksum integer,
                                              installed_by character varying(100) not null,
                                              installed_on timestamp without time zone not null default now(),
                                              execution_time integer not null,
                                              success boolean not null
);







