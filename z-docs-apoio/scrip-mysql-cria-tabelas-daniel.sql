--/////////////////////////////////////////////////////////////////////////////////////
--/////////////////////////////////////////////////////////////////////////////////////
-- criação da tabela CONTATO

DROP TABLE IF EXISTS adota_pet.contato;
CREATE TABLE IF NOT EXISTS adota_pet.contato(
  contato_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(200) NOT NULL,
  email VARCHAR(45) NOT NULL,
  mensagem TINYTEXT NOT NULL,
  data_lancamento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  lido TINYINT(1) NOT NULL,
PRIMARY KEY (`contato_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

describe contato;
select * from contato;

--/////////////////////////////////////////////////////////////////////////////////////
--/////////////////////////////////////////////////////////////////////////////////////
-- criação da tabela HASHTAG_ANIMAL

DROP TABLE IF EXISTS adota_pet.hashtag_animal;

CREATE TABLE IF NOT EXISTS adota_pet.hashtag_animal(
   hashtag_animal_id BIGINT(20) NOT NULL AUTO_INCREMENT
  ,hashtag VARCHAR(800) NOT NULL
  ,animal_id BIGINT(20) NOT NULL
  ,data_lancamento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  ,notificado TINYINT(1) NOT NULL,
  PRIMARY KEY (`hashtag_animal_id`, `animal_id`),
  INDEX fk_hashtag_animal_idx (`animal_id` ASC),
  CONSTRAINT fk_hashtag_animal_idx FOREIGN KEY (`animal_id`) REFERENCES adota_pet.animal (`animal_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

describe hashtag_animal;
select * from hashtag_animal;

--/////////////////////////////////////////////////////////////////////////////////////
--/////////////////////////////////////////////////////////////////////////////////////
-- criação da tabela HASHTAG_CLIENTE

DROP TABLE IF EXISTS adota_pet.hashtag_cliente;

CREATE TABLE IF NOT EXISTS adota_pet.hashtag_cliente(
   hashtag_cliente_id BIGINT(20) NOT NULL AUTO_INCREMENT
  ,hashtag VARCHAR(800) NOT NULL
  ,cliente_id BIGINT(20) NOT NULL
  ,data_lancamento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  ,notificado TINYINT(1) NOT NULL,
  PRIMARY KEY (`hashtag_cliente_id`, `cliente_id`),
  INDEX fk_hashtag_cliente_idx (`cliente_id` ASC),
  CONSTRAINT fk_hashtag_cliente_idx FOREIGN KEY (`cliente_id`) REFERENCES adota_pet.cliente (`cliente_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

describe hashtag_cliente;
select * from hashtag_cliente;

--/////////////////////////////////////////////////////////////////////////////////////
--/////////////////////////////////////////////////////////////////////////////////////
-- criação da tabela NOTIFICACAO

DROP TABLE IF EXISTS adota_pet.notificacao;

CREATE TABLE IF NOT EXISTS adota_pet.notificacao(
   notificao_id BIGINT(20) NOT NULL AUTO_INCREMENT
  ,cliente_id BIGINT(20) NOT NULL
  ,animal_id BIGINT(20) NOT NULL
  ,data_lancamento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  ,notifica TINYINT(1) NOT NULL,
  PRIMARY KEY (`notificao_id`),
  INDEX fk_notificacao_cliente_idx (`cliente_id` ASC),
  CONSTRAINT fk_notificacao_cliente_idx FOREIGN KEY (`cliente_id`) REFERENCES adota_pet.cliente (`cliente_id`),
  INDEX fk_notificacao_animal_idx (`animal_id` ASC),
  CONSTRAINT fk_notificacao_animal_idx FOREIGN KEY (`animal_id`) REFERENCES adota_pet.animal (`animal_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

describe notificacao;
select * from notificacao;