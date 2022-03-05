CREATE TABLE scenarios (
	id SERIAL,
	name varchar(255) NOT NULL,
	CONSTRAINT scenarios_pkey PRIMARY KEY (id)
);

insert into scenarios(id, name) values (1,  'Scenario 1');