-- CREATE TABLE categoria (
--     categoria_id INTEGER PRIMARY KEY,
--     nome text,
--     descricao text
-- )

-- CREATE TABLE pratos (
--     id INTEGER PRIMARY KEY,
--     nome text NOT NULL, 
--     descricao text, 
--     valor NUMERIC ,
--     categoria_id INTEGER REFERENCES categoria (categoria_id),
--     calorias INTEGER,
--     quantidade NUMERIC,
--     unidadeMedida text
-- )

-- Adicionando dados Ficticios

INSERT INTO pratos (nome, descricao, valor) VALUES ("Macarrão a Bolonhesa", "Otimo macarrão a bolonhesa", 43.50)