# 🧱 FrameworkJava

Esse framework foi criado com base nos estudos em Java, com foco na simplicidade e produtividade no desenvolvimento de aplicações Java com acesso a banco de dados PostgreSQL. Ele oferece funcionalidades básicas de **CRUD dinâmico**, utilizando **reflexão** para gerar tabelas, inserir, atualizar, deletar e recuperar objetos diretamente do banco de dados.

---

## 🚀 Funcionalidades

- 🔌 Conexão direta com PostgreSQL (`AFDAL`)
- 🧠 Mapeamento dinâmico de objetos usando reflexão (`ALDAL`)
- 🗃️ Geração automática de tabelas baseado em classes Java
- ✍️ Operações de CRUD:
  - `set` – Insere dados na tabela
  - `get` – Busca dados e preenche o objeto
  - `update` – Atualiza dados com base em chaves
  - `delete` – Deleta dados com base no conteúdo do objeto
- ⚠️ Controle de erros centralizado via classe `Erro`

---

## 🛠️ Estrutura Principal

### 🔧 AFDAL (Abstract Framework Data Access Layer)
Classe responsável por:

- Conectar e desconectar do banco
- Executar comandos SQL (DML e DDL)
- Retornar resultados de `SELECT`

### 🧠 ALDAL (Abstract Layer for Data Access via Reflection)
Utiliza Java Reflection para:

- Criar tabelas com base na estrutura das classes
- Montar e executar dinamicamente comandos `INSERT`, `UPDATE`, `DELETE`, `SELECT`

### ⚠️ Erro
Classe utilitária para rastreamento e manipulação centralizada de erros da aplicação.

---

##COMO UTILIZAR
git clone https://github.com/Murilo013/FrameworkJava.git
o aquivo .jar do framework está disponível em dist/frameworkpostgresql.jar

## 🧪 Exemplo de Uso

```java
// Classe de exemplo
public class Usuario {
    private int id;
    private String nome;

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}

public class Main {
    public static void main(String[] args) {
        AFDAL.conectdb("meubanco", "usuario", "senha"); // envie suas propriedades de conexão como nome do banco, usuario e senha

        Usuario user = new Usuario();
        user.setId(1);
        user.setNome("Murilo");

        ALDAL.set(user); // Insere
        ALDAL.get(user); // Busca
        user.setNome("João");
        ALDAL.update(user, user); // Atualiza
        ALDAL.delete(user); // Deleta
    }
}
