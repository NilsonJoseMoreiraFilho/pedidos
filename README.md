# API de Pedidos

API de pedidos desenvolvida para avaliação.

# Alguns detalhes da API

Essa API foi desenvolvida de acordo com a especificação que foi passada para a avaliação.

Consiste em dois serviços, um de consulta (que pode ser utilizado para buscar todos os pedidos ou filtrando por número de controle e data de cadastro), e um de cadastro, para inserção de pedidos novos pedidos.

As regras de negócio foram desenvolvidas de acordo com o documento recebido, que está disponível em /documentacao/teste.doc.

Para o desenvolvimento, foram utilizados módulos do Spring Framework.

Alguns padrões de projeto, como Façade, Factory e Repository foram adotados e utilizados de forma adaptativa, visando atender a necessidade do projeto.

A integração com o banco de dados MySQL foi realizada utilizando o módulo do Spring JPA, que, por sua vez, utiliza Hibernate.

Java utilizado: 1.8.

Sobre testes unitários, foram desenvolvidos alguns testes automatizados como exemplo, para os diferentes tipos de classes presentes no projeto. Para os testes unitários, utilizei o Mockito para criar mocks das dependências injetadas, testando de fato cada componente separado.
Também foi desenvolvido um teste de integração "end-to-end" que testa a execução completa de um serviço.

Outro detalhe é que os testes automatizados são utilizados em uma base separada, que é limpa e repopulada a cada teste.

Sobre o tratamento de exceções, também foram desenvolvidos alguns exemplos, inclusive utilizando mecanismos disponibilizados pelo Spring Framework.

# Execução

Após a importação do projeto, os seguintes passos precisam ser executados:

1. Executar o script CREATE_BD.sql, presente na pasta SQL. Ele é responsável pela criação inicial das bases.
2. Ajustar os arquivos de configuração "application.properties" e "application-test.properties" na pasta src/main/resources. Esses arquivos contem informações sobre a conexão com o banco de dados e a porta onde a aplicação deve subir.
3. Executar normalmente a aplicação / testes. A criação das tabelas fica por conta do Spring (inclusive o preenchimento inicial da tabela de clientes). 
