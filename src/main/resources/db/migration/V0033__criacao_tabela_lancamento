CREATE TABLE lancamento (
  id bigint(20) NOT NULL auto_increment,
  data datetime NOT NULL,
  data_atualizacao datetime NOT NULL,
  data_criacao datetime NOT NULL,
  descricao varchar(255) DEFAULT NULL,
  localizacao varchar(255) DEFAULT NULL,
  tipo varchar(255) NOT NULL,
  funcionario_id bigint(20) DEFAULT NULL,
  
  primary key(id)
);

ALTER TABLE `lancamento`
  ADD KEY `FK46i4k5vl8wah7feutye9kbpi4` (`funcionario_id`);

ALTER TABLE `lancamento`
  ADD CONSTRAINT `FK46i4k5vl8wah7feutye9kbpi4` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`);