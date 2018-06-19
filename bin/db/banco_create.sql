CREATE DATABASE banco;
USE banco;

CREATE TABLE Cliente (
	idCliente INT PRIMARY KEY auto_increment,
    nome VARCHAR(35) NOT NULL,
    sobrenome VARCHAR(35) NOT NULL,
    rg VARCHAR(14) UNIQUE NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    sexo CHAR(1) NOT NULL,
    estado CHAR(2) NOT NULL,
    cidade VARCHAR(35) NOT NULL,
    endereco VARCHAR(35) NOT NULL,
    idConta INT,
    FOREIGN KEY (idConta) REFERENCES Conta(idConta)
);

CREATE TABLE TiposConta (
	idTipoConta INT PRIMARY KEY auto_increment,
    nomeConta VARCHAR(35)
);

CREATE TABLE Conta (
	idConta INT PRIMARY KEY auto_increment,
    tipoConta INT NOT NULL,
    cliente INT NOT NULL,
    saldo DECIMAL(11,2) DEFAULT 0.00,
    depositoInicial DECIMAL(11, 2) NOT NULL,
    FOREIGN KEY (cliente) REFERENCES Cliente(idCliente),
    FOREIGN KEY (tipoConta) REFERENCES TiposConta(idTipoConta)
);

CREATE TABLE ContaCorrente (
	idConta INT PRIMARY KEY,
    limite DECIMAL(11,2) NOT NULL,
    FOREIGN KEY (idConta) REFERENCES Conta(idConta)
);

CREATE TABLE ContaInvestimento (
	idConta INT PRIMARY KEY,
    montanteMinimo DECIMAL(11,2) NOT NULL,
    depositoMinimo DECIMAL(11,2) NOT NULL,
    FOREIGN KEY (idConta) REFERENCES Conta(idConta)
);

