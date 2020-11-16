INSERT INTO cargo (nome) VALUES ('Analista');
INSERT INTO cargo (nome) VALUES ('Analista');
INSERT INTO cargo (nome) VALUES ('Gerente');
INSERT INTO cargo (nome) VALUES ('Trainee');

INSERT INTO funcionario(data_aniversario,documento,nome,cargo_id)
VALUES (CURRENT_DATE , '33344455509', 'Joao da Silva', 1);
INSERT INTO funcionario(data_aniversario,documento,nome,cargo_id)
VALUES (CURRENT_DATE , '33344455510', 'José da Silva', 1);
INSERT INTO funcionario(data_aniversario,documento,nome,cargo_id)
VALUES (CURRENT_DATE , '33344455511', 'Maria da Silva', 2);
INSERT INTO funcionario(data_aniversario,documento,nome,cargo_id)
VALUES (CURRENT_DATE , '33344455512', 'Joana da Silva', 2);
INSERT INTO funcionario(data_aniversario,documento,nome,cargo_id)
VALUES (CURRENT_DATE , '33344455513', 'Lurdez da Silva', 3);
INSERT INTO funcionario(data_aniversario,documento,nome,cargo_id)
VALUES (CURRENT_DATE , '33344455514', 'Pedro da Silva', 4);

INSERT INTO departamento(nome) VALUES ('TI');
INSERT INTO departamento(nome) VALUES ('RH');
INSERT INTO departamento(nome) VALUES ('FINANCEIRO');

INSERT INTO funcionario_departamento(departamento_id,funcionario_id,data_inicio,data_fim)
VALUES (1,1, CURRENT_DATE , null);
INSERT INTO funcionario_departamento(departamento_id,funcionario_id,data_inicio,data_fim)
VALUES (1,2, CURRENT_DATE , null);
INSERT INTO funcionario_departamento(departamento_id,funcionario_id,data_inicio,data_fim)
VALUES (2,3, CURRENT_DATE , null);
INSERT INTO funcionario_departamento(departamento_id,funcionario_id,data_inicio,data_fim)
VALUES (2,4, CURRENT_DATE , null);
INSERT INTO funcionario_departamento(departamento_id,funcionario_id,data_inicio,data_fim)
VALUES (3,5, CURRENT_DATE , null);
INSERT INTO funcionario_departamento(departamento_id,funcionario_id,data_inicio,data_fim)
VALUES (3,6, CURRENT_DATE , CURRENT_DATE);
INSERT INTO funcionario_departamento(departamento_id,funcionario_id,data_inicio,data_fim)
VALUES (3,6, CURRENT_DATE , null);
