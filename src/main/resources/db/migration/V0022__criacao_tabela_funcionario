CREATE TABLE funcionario (
  id bigint(20) NOT NULL auto_increment,
  cpf varchar(255) NOT NULL,
  data_atualizacao datetime NOT NULL,
  data_criacao datetime NOT NULL,
  email varchar(255) NOT NULL,
  nome varchar(255) NOT NULL,
  perfil varchar(255) NOT NULL,
  qtd_horas_almoco float DEFAULT NULL,
  qtd_horas_trabalho_dia float DEFAULT NULL,
  senha varchar(255) NOT NULL,
  valor_hora decimal(19,2) DEFAULT NULL,
  empresa_id bigint(20) DEFAULT NULL,

  primary key(id)
); 
ALTER TABLE `funcionario`
  ADD KEY `FK4cm1kg523jlopyexjbmi6y54j` (`empresa_id`);
  
ALTER TABLE `funcionario`
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y54j` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`id`);