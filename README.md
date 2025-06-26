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

### COMO UTILIZAR
- git clone https://github.com/Murilo013/FrameworkJava.git
- O aquivo .jar do framework está disponível em: `dist/frameworkpostgresql.jar`

## 🧪 Exemplo de Uso

```java
Classe de Exemplo

public class ExemploCRUD {
    public static void main(String[] args) {

        // Conecta no banco (ajuste nome do banco, usuário e senha)
        AFDAL.conectdb("nomedobanco", "usuario", "senha");
        
        // Cria o objeto Livro e preenche dados
        Livro livro = new Livro();
        livro.setTitulo("Tituloteste");
        livro.setAutor("Autorteste");
        livro.setEditora("Editorateste");
        livro.setAnoedicao("2025");
        livro.setLocalizacao("SP");
        
        // 1. Cria a tabela baseada na classe Livro 
        ALDAL.geraTabela(livro);
        
        // 2. Insere o livro no banco
        ALDAL.set(livro);
        
        // 3. Consulta o livro no banco pelo título
        Livro consulta = new Livro();
        consulta.setTitulo("Tituloteste");
        ALDAL.get(consulta);
        System.out.println("Autor: " + consulta.getAutor());
        System.out.println("Editora: " + consulta.getEditora());
        System.out.println("Anoedicao: " + consulta.getAnoedicao());
        System.out.println("Localizacao: " + consulta.getLocalizacao());
        
        // 4. Atualiza o autor do livro
        Livro dadosAtualizados = new Livro();
        dadosAtualizados.setAutor("Autor Atualizado");

        Livro chaves = new Livro();
        chaves.setTitulo("Tituloteste");  //  localizar o registro para atualizar
        ALDAL.update(dadosAtualizados, chaves);
        
        // 5. Consulta novamente para verificar atualização
        Livro consultaAtualizada = new Livro();
        consultaAtualizada.setTitulo("Testetitulo");
        ALDAL.get(consultaAtualizada);
        System.out.println("Consulta atualização: " + consultaAtualizada.getAutor());
        
        // 6. Deleta o livro pelo título
        Livro deletar = new Livro();
        deletar.setTitulo("Tituloteste");
        ALDAL.delete(deletar);
                 
        // Desconecta do banco
        AFDAL.desconecta();
    }
}

