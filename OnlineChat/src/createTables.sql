CREATE TABLE message
(
  id serial NOT NULL,
  message character varying,
  user_id integer,
  date character varying(50),
  CONSTRAINT pk_message PRIMARY KEY (id),
  CONSTRAINT message_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);
CREATE TABLE users
(
  id integer NOT NULL,
  name character varying,
  surname character varying,
  photo character varying,
  email character varying,
  login character varying,
  passwordhash character varying(100),
  phone character varying,
  token character varying(100),
  CONSTRAINT pk_user PRIMARY KEY (id)
);