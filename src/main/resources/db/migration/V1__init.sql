create table public.users (
                              id integer primary key not null default nextval('users_id_seq'::regclass),
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
                              is_deleted boolean,
                              foreign key (tax_id) references public.taxcode (id)
                                  match simple on update no action on delete no action,
                              foreign key (employee_id) references public.cards (id)
                                  match simple on update no action on delete no action
);

