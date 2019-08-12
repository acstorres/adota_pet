-- MySQL Script generated by MySQL Workbench
-- Sun Aug 11 11:14:20 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema adota_pet
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS adota_pet ;

-- -----------------------------------------------------
-- Schema adota_pet
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS adota_pet DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE adota_pet ;

-- -----------------------------------------------------
-- Table adota_pet.endereco
-- -----------------------------------------------------
DROP TABLE IF EXISTS adota_pet.endereco ;

CREATE TABLE IF NOT EXISTS adota_pet.endereco (
  endereco_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(200) NULL DEFAULT NULL,
  bairro VARCHAR(200) NULL DEFAULT NULL,
  cidade VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`endereco_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table adota_pet.tipo_pessoa_cliente
-- -----------------------------------------------------
DROP TABLE IF EXISTS adota_pet.tipo_pessoa_cliente ;

CREATE TABLE IF NOT EXISTS adota_pet.tipo_pessoa_cliente (
  tipo_pessoa_cliente_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  tipo CHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`tipo_pessoa_cliente_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table adota_pet.usuario
-- -----------------------------------------------------
DROP TABLE IF EXISTS adota_pet.usuario ;

CREATE TABLE IF NOT EXISTS adota_pet.usuario (
  usuario_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  login VARCHAR(200) NOT NULL,
  senha VARCHAR(15) NOT NULL,
  data_lancamento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  ativo TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`usuario_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table adota_pet.cliente
-- -----------------------------------------------------
DROP TABLE IF EXISTS adota_pet.cliente ;

CREATE TABLE IF NOT EXISTS adota_pet.cliente (
  cliente_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(200) NOT NULL,
  cpf VARCHAR(20) NULL DEFAULT NULL,
  cnpj VARCHAR(20) NULL DEFAULT NULL,
  razao_social VARCHAR(200) NULL DEFAULT NULL,
  nome_fantasia VARCHAR(200) NULL DEFAULT NULL,
  data_nascimento DATETIME NULL DEFAULT NULL,
  data_lancamento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  ativo TINYINT(1) NOT NULL,
  usuario_id BIGINT(20) NOT NULL,
  endereco_id BIGINT(20) NOT NULL,
  pessoa_cliente_id BIGINT(20) NOT NULL,
  doador TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`cliente_id`),
  INDEX fk_cliente_usuario_idx (`usuario_id` ASC) VISIBLE,
  INDEX fk_cliente_endereco1_idx (`endereco_id` ASC) VISIBLE,
  INDEX fk_cliente_tipo_pessoa_cliente1_idx (`pessoa_cliente_id` ASC) VISIBLE,
  CONSTRAINT fk_cliente_endereco1
    FOREIGN KEY (`endereco_id`)
    REFERENCES adota_pet.endereco (`endereco_id`),
  CONSTRAINT fk_cliente_tipo_pessoa_cliente1
    FOREIGN KEY (`pessoa_cliente_id`)
    REFERENCES adota_pet.tipo_pessoa_cliente (`tipo_pessoa_cliente_id`),
  CONSTRAINT fk_cliente_usuario
    FOREIGN KEY (`usuario_id`)
    REFERENCES adota_pet.usuario (`usuario_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `adota_pet`.`especie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS adota_pet.especie ;

CREATE TABLE IF NOT EXISTS adota_pet.especie (
  especie_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`especie_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table adota_pet.raca
-- -----------------------------------------------------
DROP TABLE IF EXISTS adota_pet.raca ;

CREATE TABLE IF NOT EXISTS adota_pet.raca (
  raca_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(200) NULL DEFAULT NULL,
  especie_id BIGINT(20) NOT NULL,
  PRIMARY KEY (`raca_id`, `especie_id`),
  INDEX fk_raca_especie1_idx (`especie_id` ASC) VISIBLE,
  CONSTRAINT fk_raca_especie1
    FOREIGN KEY (`especie_id`)
    REFERENCES adota_pet.especie (`especie_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table adota_pet.animal
-- -----------------------------------------------------
DROP TABLE IF EXISTS adota_pet.animal ;

CREATE TABLE IF NOT EXISTS adota_pet.animal (
  animal_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(200) NOT NULL,
  porte VARCHAR(20) NULL DEFAULT NULL,
  idade_estimada INT(11) NULL DEFAULT NULL,
  cor_pelagem VARCHAR(200) NULL DEFAULT NULL,
  cadastrado TINYINT(1) NOT NULL,
  sinais_marcas VARCHAR(500) NULL DEFAULT NULL,
  descricao VARCHAR(500) NULL DEFAULT NULL,
  data_cadastro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  adotado TINYINT(1) NOT NULL,
  raca_id BIGINT(20) NOT NULL,
  doador_cliente_id BIGINT(20) NOT NULL,
  PRIMARY KEY (`animal_id`, `doador_cliente_id`),
  INDEX fk_animal_raca1_idx (`raca_id` ASC) VISIBLE,
  INDEX fk_animal_cliente1_idx (`doador_cliente_id` ASC) VISIBLE,
  CONSTRAINT fk_animal_cliente1
    FOREIGN KEY (`doador_cliente_id`)
    REFERENCES adota_pet.cliente (`cliente_id`),
  CONSTRAINT fk_animal_raca1
    FOREIGN KEY (`raca_id`)
    REFERENCES adota_pet.raca (`raca_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table adota_pet.lancamento_acao
-- -----------------------------------------------------
DROP TABLE IF EXISTS adota_pet.lancamento_acao ;

CREATE TABLE IF NOT EXISTS adota_pet.lancamento_acao (
  lancamento_acao_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  data_lancamento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  cliente_id BIGINT(20) NOT NULL,
  PRIMARY KEY (`lancamento_acao_id`),
  INDEX fk_lancamento_acao_cliente1_idx (`cliente_id` ASC) VISIBLE,
  CONSTRAINT fk_lancamento_acao_cliente1
    FOREIGN KEY (`cliente_id`)
    REFERENCES adota_pet.cliente (`cliente_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table adota_pet.movimento_adocao
-- -----------------------------------------------------
DROP TABLE IF EXISTS adota_pet.movimento_adocao ;

CREATE TABLE IF NOT EXISTS adota_pet.movimento_adocao (
  movimento_adocao_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  animal_id BIGINT(20) NOT NULL,
  lancamento_acao_id BIGINT(20) NOT NULL,
  PRIMARY KEY (`movimento_adocao_id`),
  INDEX fk_movimento_adocao_animal1_idx (`animal_id` ASC) VISIBLE,
  INDEX fk_movimento_adocao_lancamento_acao1_idx (`lancamento_acao_id` ASC) VISIBLE,
  CONSTRAINT fk_movimento_adocao_animal1
    FOREIGN KEY (`animal_id`)
    REFERENCES adota_pet.animal (`animal_id`),
  CONSTRAINT fk_movimento_adocao_lancamento_acao1
    FOREIGN KEY (`lancamento_acao_id`)
    REFERENCES adota_pet.lancamento_acao (`lancamento_acao_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;