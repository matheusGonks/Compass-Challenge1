
# Ecommerce

## Como executar

Como a  aplicação faz uso de um banco de dados no PostgreSQL anexei o arquivo do banco de dados "ecommerce.sql", que possui as tabelas que são 
utilizadas pela aplicação. A única tabela que já possui registros logo do início é a tabela de Produtos. Para executar a aplicação é preciso antes executar o servidor e criar o banco de dados a partir do arquivo **ecommerce.sql** .

A aplicação em si utiliza o Maven para configurar as dependencias, de forma que elas já estão definidias em **pom.xml**.

A classe que chama o método Main é a própria classe Main.java. Projeto foi desenvolvido no IntelliJ, então executá-lo lá será mais fácil pois é a build correspondente.

As conexões com o banco de dados são feitas pelas próprias classes de conexão com banco de dados e as conexões são estabelecidas e fechadas em cada requisição. Caso na sua máquina seja necessário autenticação local do user **postgres**, que ó user utilizado, os atributos de conexão  *url, username e senha* estão configurados em **LoginDB.java , InventoryDB.java, ShoppingCartDB.java**. 

## Como funciona 

Ao iniciar a aplicação, você pode entrar como Admin ou cliente.

No caso de Admin, você tem a capacidade de manipular o Inventário da loja, ou seja, todos os produtos em estoque, sendo possível **Cadastrar, Alterar, Deletar ou Ver** os produtos do Inventário da loja.  

No caso de logar como um Cliente, você insere o seu nome e o email, que é único no sistema. Se seu usuário já existir no Banco de dados, você já acessa com o Cart associado ao seu ID, se não, é criado um novo registro com o nome e o email inserido.

A partir daí você pode interagir com o estoque da loja, podendo:
- Visualizar o inventário de produtos da loja
- Visualizar os produtos no seu carrinho.
- Adicionar, Remover produtos do Carrinho. (A adição de produtos que já existem no carrinho incrementa a quantidade).
- Concluir a compra que no caso da aplicação simplesmente esvazia o carrinho e atualiza a quantidade de produtos no inventário.

*Observação* : o ID de um produto é o mesmo tanto no carrinho quanto no inventário

