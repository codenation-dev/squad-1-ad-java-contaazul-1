CREATE TABLE "usuario" (
  "id" SERIAL PRIMARY KEY,
  "nome" varchar(100),
  "cpf" varchar(11),
  "email" varchar(100),
  "senha" varchar(255),
  "token" varchar(255),
  "created_at" timestamp,
  "updated_at" timestamp
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
