CREATE TABLE scenario_steps (
	id SERIAL,
	scenario_id integer NOT NULL,
	request_body varchar(4000) NOT NULL,
	response_body varchar(4000) NOT NULL,
	response_status varchar(255) NOT NULL,
--	next_id integer NOT NULL,
	request_headers varchar(1000) NOT NULL,
	response_headers varchar(1000) NOT NULL,
	sequence integer NOT NULL,
	status varchar(100) NOT NULL,

	CONSTRAINT scenario_steps_pkey PRIMARY KEY (id),
	CONSTRAINT fk_scenario_id FOREIGN KEY (scenario_id) REFERENCES scenarios (id)
--	CONSTRAINT fk_next_id FOREIGN KEY (next_id) REFERENCES scenario_steps (id)
);