CREATE TABLE scenarios (
	id SERIAL,
	name varchar(255) NOT NULL,
	method varchar(100) NOT NULL,
    path varchar(1000) NOT NULL,

	CONSTRAINT scenarios_pkey PRIMARY KEY (id)
);