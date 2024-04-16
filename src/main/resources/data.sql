CREATE TABLE cliente (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    telefone VARCHAR(12),
    correntista BOOLEAN DEFAULT FALSE,
    saldo_cc NUMERIC(12, 2)
);