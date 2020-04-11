CREATE TABLE "Usuario" (
  "id" SERIAL PRIMARY KEY,
  "nome" varchar(100),
  "email" varchar(100),
  "senha" varchar(255),
  "token" varchar(255),
  "data_criacao" timestamp
);

CREATE TABLE "Erro" (
  "id" SERIAL PRIMARY KEY,
  "id_usuario" int,
  "titulo" varchar(100),
  "detalhes" varchar(200),
  "origem" varchar(50),
  "nivel" varchar(50),
  "data_ocorrencia" timestamp,
  FOREIGN KEY ("id_usuario") REFERENCES "Usuario" ("id")
);

