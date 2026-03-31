# Onboarding Energy Box API

Este é o backend do projeto Onboarding Energy Box, desenvolvido em Spring Boot. Este guia explica como configurar e rodar a aplicação localmente utilizando o IntelliJ IDEA.

## 🛠️ Pré-requisitos

* **Java 11** ou superior
* **IntelliJ IDEA** (Community ou Ultimate)
* **Microsoft SQL Server** rodando localmente ou em rede

---

## ⚙️ Configuração do Banco de Dados

Antes de rodar a aplicação, você precisa configurar a conexão com o banco de dados no arquivo `src/main/resources/application.properties` (ou `.yml`).

```properties
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=NOME_DO_SEU_BANCO;encrypt=true;trustServerCertificate=true;
```
## ⚠️ Configurando a Autenticação do SQL Server

Ao tentar conectar ao SQL Server, você pode se deparar com o seguinte erro no console:

java.lang.UnsatisfiedLinkError: Unable to load authentication DLL mssql-jdbc_auth-13.2.1.x64

Isso acontece porque o driver JDBC da Microsoft precisa de um arquivo nativo do Windows (.dll) para conseguir fazer o login usando a sua conta de usuário do Windows (Autenticação Integrada).

### Logar usando as credenciais do seu usuário do Windows

Siga este passo a passo para configurar a DLL no IntelliJ IDEA:

1. Baixar a DLL

    - Baixe o Microsoft JDBC Driver for SQL Server (versão equivalente à do seu projeto, ex: 13.2).
        - https://learn.microsoft.com/pt-br/sql/connect/jdbc/release-notes-for-the-jdbc-driver?view=sql-server-ver17#132

    - Extraia o arquivo .zip baixado.

    - Navegue até a pasta sqljdbc_13.2\enu\auth\x64\ e encontre o arquivo mssql-jdbc_auth-13.2.1.x64.dll.

    - Copie este arquivo e cole em uma pasta de fácil acesso (ex: crie uma pasta libs na raiz do seu projeto e cole lá, ou coloque em C:\dlls\).

2. Configurar o IntelliJ IDEA
    - Agora precisamos avisar ao Java onde essa DLL está.

    - No topo do IntelliJ, ao lado do botão verde de "Play" (Run), clique no nome da aplicação (OnboardingEnergyBoxApplication) e selecione Edit Configurations...

    - Selecione a sua aplicação Spring Boot no menu lateral esquerdo.

    - Procure por VM options.

    Nota: Se você não estiver vendo esse campo no IntelliJ mais recente, clique em Modify options (ou no link "Add VM options" / "Alt+V") para exibir a caixa de texto.

    - Cole o seguinte comando na caixa VM options, substituindo o caminho para a pasta onde você salvou a sua DLL:

```Plaintext
-Djava.library.path="C:\caminho\para\a\pasta\da\dll"
Exemplo: -Djava.library.path="C:\dev\dll"
```

Clique em Apply e depois em OK.

## ▶️ Como Rodar a Aplicação
Com o banco de dados configurado:

  - Abra a classe principal OnboardingEnergyBoxApplication.java.

  - Clique no ícone de "Play" verde ao lado do método main, ou use o atalho Shift + F10.

A aplicação estará rodando na porta padrão do Tomcat (geralmente http://localhost:8080).
