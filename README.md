# Não Deixe sobrar

Configurações, tecnologias utilizadas e tudo o que for necessário para compilar e executar o projeto.


# Tecnologias/Ferramentas
### Banco de dados
Database: **Postgres 9.4**

Client Database: **pgAdmin 4**

### Java
JRE: **1.8**

Eclipse: **Oxygen.3a Release (4.7.3a)**

Application Server: **Tomcat v8.0**

WebService: **REST**

### Outros
Client WebService: **Postman**


## Configuração

1. Clonar o projeto em um diretório de sua escolha.
2. Executar um restore do arquivo "naodeixesobrar" que está na pasta "scripts" via linha de comando ou IDE (pgAdmin) para criar o banco de dados de forma local.
3. Abrir o eclipse e importar o projeto através do menu "File > Import".  
4. Na tela que foi aberta, filtrar por "Maven" e escolher a opção "Existing Maven Projects".  Clicar em Next.
5. Em "Root Directory", selecionar o caminho onde foi clonado o projeto no passo 1.  Depois que o "pom.xml" aparecer na caixa "Projects", clicar em Finish.  O projeto será carregado para a Workspace.
6. Clicar com o botão direito no projeto e selecionar "Maven > Update Project".  Clicar em "Ok" para baixar todas as dependências.
7. Caso seja necessário, configurar o Build Path (botão direito no projeto, "Build Path > Configure Build Path" e caso haja algum erro com o por exemplo a configuração da JRE, resolver).
8. Executar o projeto clicando com o botão direito no projeto e acessar o menu "Run As > Run on Server" (levando em consideração que o Tomcat já esteja configurado no eclipse).


