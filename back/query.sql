CREATE TABLE "users" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar(100),
  "email" varchar(100),
  "password" varchar(255), 
  "created_at" timestamp,
  "updated_at" timestamp,
   FOREIGN KEY(role_id) REFERENCES roles(id)
);

CREATE TABLE "erro" (
  "id" SERIAL PRIMARY KEY,
  "id_usuario" int,
  "titulo" varchar(100),
  "detalhes" varchar(200),
  "origem" varchar(50),
  "nivel" varchar(50),
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "roles" (
  "id" SERIAL PRIMARY KEY,
  "role_name" varchar(60)

);
