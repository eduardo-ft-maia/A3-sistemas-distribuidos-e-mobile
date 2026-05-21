-- Adicionar todos os Estados existentes
INSERT INTO estado (nome, uf) VALUES
    ('Acre', 'AC'),
    ('Alagoas', 'AL'),
    ('Amapá', 'AP'),
    ('Amazonas', 'AM'),
    ('Bahia', 'BA'),
    ('Ceará', 'CE'),
    ('Distrito Federal', 'DF'),
    ('Espírito Santo', 'ES'),
    ('Goiás', 'GO'),
    ('Maranhão', 'MA'),
    ('Mato Grosso', 'MT'),
    ('Mato Grosso do Sul', 'MS'),
    ('Minas Gerais', 'MG'),
    ('Pará', 'PA'),
    ('Paraíba', 'PB'),
    ('Paraná', 'PR'),
    ('Pernambuco', 'PE'),
    ('Piauí', 'PI'),
    ('Rio de Janeiro', 'RJ'),
    ('Rio Grande do Norte', 'RN'),
    ('Rio Grande do Sul', 'RS'),
    ('Rondônia', 'RO'),
    ('Roraima', 'RR'),
    ('Santa Catarina', 'SC'),
    ('São Paulo', 'SP'),
    ('Sergipe', 'SE'),
    ('Tocantins', 'TO');

-- Adicionar a capital de cada estado nas cidades
INSERT INTO cidade (nome, estado_id) VALUES
    ('Rio Branco', (SELECT id FROM estado WHERE uf = 'AC')),
    ('Maceió', (SELECT id FROM estado WHERE uf = 'AL')),
    ('Macapá', (SELECT id FROM estado WHERE uf = 'AP')),
    ('Manaus', (SELECT id FROM estado WHERE uf = 'AM')),
    ('Salvador', (SELECT id FROM estado WHERE uf = 'BA')),
    ('Fortaleza', (SELECT id FROM estado WHERE uf = 'CE')),
    ('Brasília', (SELECT id FROM estado WHERE uf = 'DF')),
    ('Vitória', (SELECT id FROM estado WHERE uf = 'ES')),
    ('Goiânia', (SELECT id FROM estado WHERE uf = 'GO')),
    ('São Luís', (SELECT id FROM estado WHERE uf = 'MA')),
    ('Cuiabá', (SELECT id FROM estado WHERE uf = 'MT')),
    ('Campo Grande', (SELECT id FROM estado WHERE uf = 'MS')),
    ('Belo Horizonte', (SELECT id FROM estado WHERE uf = 'MG')),
    ('Belém', (SELECT id FROM estado WHERE uf = 'PA')),
    ('João Pessoa', (SELECT id FROM estado WHERE uf = 'PB')),
    ('Curitiba', (SELECT id FROM estado WHERE uf = 'PR')),
    ('Recife', (SELECT id FROM estado WHERE uf = 'PE')),
    ('Teresina', (SELECT id FROM estado WHERE uf = 'PI')),
    ('Rio de Janeiro', (SELECT id FROM estado WHERE uf = 'RJ')),
    ('Natal', (SELECT id FROM estado WHERE uf = 'RN')),
    ('Porto Alegre', (SELECT id FROM estado WHERE uf = 'RS')),
    ('Porto Velho', (SELECT id FROM estado WHERE uf = 'RO')),
    ('Boa Vista', (SELECT id FROM estado WHERE uf = 'RR')),
    ('Florianópolis', (SELECT id FROM estado WHERE uf = 'SC')),
    ('São Paulo', (SELECT id FROM estado WHERE uf = 'SP')),
    ('Aracaju', (SELECT id FROM estado WHERE uf = 'SE')),
    ('Palmas', (SELECT id FROM estado WHERE uf = 'TO'));

-- Adicionar Empresas
INSERT INTO empresa (razao_social, nome_fantasia, cnpj, telefone, email, criado_em) VALUES
    (
        'Expresso Sul Transportes LTDA',
        'Expresso Sul',
        '12.345.678/0001-10',
        '(41) 99999-1111',
        'contato@expressosul.com.br',
        CURRENT_TIMESTAMP
    ),
    (
        'Viação Brasil Central LTDA',
        'Brasil Central',
        '23.456.789/0001-20',
        '(11) 98888-2222',
        'atendimento@brasilcentral.com.br',
        CURRENT_TIMESTAMP
    ),
    (
        'Transportes Nordeste S.A.',
        'Nordeste Bus',
        '34.567.890/0001-30',
        '(81) 97777-3333',
        'suporte@nordestebus.com.br',
        CURRENT_TIMESTAMP
    ),
    (
        'Rota Norte Viagens LTDA',
        'Rota Norte',
        '45.678.901/0001-40',
        '(92) 96666-4444',
        'contato@rotanorte.com.br',
        CURRENT_TIMESTAMP
    ),
    (
        'Viação Serra Verde LTDA',
        'Serra Verde',
        '56.789.012/0001-50',
        '(31) 95555-5555',
        'financeiro@serraverde.com.br',
        CURRENT_TIMESTAMP
    );

-- Adicionar Usuários
    INSERT INTO usuario (username, password, role, empresa_id) VALUES
    (
        'admin',
        '{noop}123',
        'ADMIN',
        null
    ),
    (
        'usuario',
        '{noop}123',
        'USER',
        null
    ),
    (
        'empresa1',
        '{noop}123',
        'EMPRESA',
        (SELECT id FROM empresa WHERE nome_fantasia = 'Expresso Sul')
    ),
    (
        'empresa2',
        '{noop}123',
        'EMPRESA',
        (SELECT id FROM empresa WHERE nome_fantasia = 'Brasil Central')
    );

-- Adicionar viagens
INSERT INTO viagem (empresa_id, cidade_origem_id, cidade_destino_id, horario_saida, horario_chegada, valor_passagem, distancia_km, criado_em) VALUES
    (
      (SELECT id FROM empresa WHERE nome_fantasia = 'Expresso Sul'),
      (SELECT id FROM cidade WHERE nome = 'Curitiba'),
      (SELECT id FROM cidade WHERE nome = 'São Paulo'),
      '2026-05-10 07:00:00',
      '2026-05-10 13:00:00',
      89.90,
      408.0,
      CURRENT_TIMESTAMP
    ),
    (
      (SELECT id FROM empresa WHERE nome_fantasia = 'Expresso Sul'),
      (SELECT id FROM cidade WHERE nome = 'São Paulo'),
      (SELECT id FROM cidade WHERE nome = 'Rio de Janeiro'),
      '2026-05-11 08:00:00',
      '2026-05-11 14:30:00',
      120.00,
      430.0,
      CURRENT_TIMESTAMP
    ),
    (
      (SELECT id FROM empresa WHERE nome_fantasia = 'Brasil Central'),
      (SELECT id FROM cidade WHERE nome = 'Belo Horizonte'),
      (SELECT id FROM cidade WHERE nome = 'São Paulo'),
      '2026-05-12 09:00:00',
      '2026-05-12 16:00:00',
      110.50,
      586.0,
      CURRENT_TIMESTAMP
    ),
    (
      (SELECT id FROM empresa WHERE nome_fantasia = 'Brasil Central'),
      (SELECT id FROM cidade WHERE nome = 'Porto Alegre'),
      (SELECT id FROM cidade WHERE nome = 'Curitiba'),
      '2026-05-13 06:00:00',
      '2026-05-13 14:00:00',
      150.00,
      711.0,
      CURRENT_TIMESTAMP
    ),
    (
      (SELECT id FROM empresa WHERE nome_fantasia = 'Nordeste Bus'),
      (SELECT id FROM cidade WHERE nome = 'Salvador'),
      (SELECT id FROM cidade WHERE nome = 'Recife'),
      '2026-05-14 10:00:00',
      '2026-05-14 18:00:00',
      130.00,
      806.0,
      CURRENT_TIMESTAMP
    );