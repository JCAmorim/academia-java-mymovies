# 📚 Guia Rápido de Git & GitHub

> **Academia Java Backend Developer**  
> Ficheiro de referência para operações de versionamento, fluxo de Fork, sincronização e navegação entre soluções/checkpoints do curso.

---

## 🎯 Conceitos Básicos

* **Repositório Remoto (Upstream):** O repositório oficial do professor onde as soluções das aulas são publicadas.
* **Fork:** A sua cópia individual e privada do repositório no GitHub.
* **Checkpoint (Tag):** Um marco no tempo que representa a solução final e validada de uma aula específica.

---

## Pré-Requisito

* Instalação do Git na máquina local

  https://git-scm.com/install/ 

---

## 🚀 1. Setup Inicial do Aluno

### Opção A: Utilizando Git (Recomendado)

1. Faça o **Fork** do repositório do professor no GitHub.
   https://github.com/braulioconsani-arch/academia-java-mymovies 
2. Clone o **seu fork** para a sua máquina local:
   ```bash
   git clone https://github.com/SEU_USUARIO/academia-java-mymovies.git
   cd academia-java-mymovies
   ```
3. Adicione o repositório do professor como fonte original (`upstream`):
   ```bash
   git remote add upstream https://github.com/NOME_PROFESSOR/academia-java-mymovies.git
   ```
4. Verifique os remotos configurados:
   ```bash
   git remote -v
   ```

### Opção B: Sem Git (Download ZIP)
1. Vá até à página do repositório no GitHub.
2. Clique em **Code > Download ZIP**.
3. Extraia o ficheiro ZIP numa pasta local e abra no IntelliJ IDEA.

---

## 🔄 2. Fluxo Diário de Trabalho em Sala

À medida que desenvolve o código durante os exercícios da aula:

```bash
# 1. Verificar ficheiros alterados
git status

# 2. Adicionar as alterações ao staging
git add .

# 3. Criar um ponto de salvamento local
git commit -m "feat: implementa busca de filmes por id no controller"

# 4. Enviar as alterações para o seu GitHub pessoal
git push origin main
```

---

## 🔀 3. Como Acessar a Solução Oficial do Professor (Checkpoints)

No final de cada aula, o professor publica uma **Tag de Checkpoint** com a solução completa.

### Passo a passo para carregar uma solução:

1. Atualize as referências e tags do repositório do professor:
   ```bash
   git fetch upstream --tags
   ```

2. Liste todas as tags de solução disponíveis:
   ```bash
   git tag -l "checkpoint/*"
   ```

3. Alterne para a tag da aula desejada (exemplo: Aula 1):
   ```bash
   git checkout checkpoint/aula-01-rest
   ```

4. Para voltar à sua branch principal de trabalho:
   ```bash
   git checkout main
   ```

---

## 📋 Tabela de Tags do Curso

| Aula | Tema | Tag do Checkpoint |
| :--- | :--- | :--- |
| **Aula 1** | Spring Boot & REST Básico | `checkpoint/aula-01-rest` |
| **Aula 2** | API Design, DTOs & Validation | `checkpoint/aula-02-api-design` |
| **Aula 3** | Persistência com JPA/Hibernate | `checkpoint/aula-03-jpa` |
| **Aula 4** | Integração Externa (TMDB) | `checkpoint/aula-04-tmdb` |
| **Aula 5** | Qualidade, Erros e Testes | `checkpoint/aula-05-quality` |
| **Aula 6** | Security, JWT e OAuth2 | `checkpoint/aula-06-security` |
| **Aula 7** | Empacotamento JAR e Container | `checkpoint/aula-07-deployable` |
| **Aula 8** | Kubernetes Local (Minikube) | `checkpoint/aula-08-final` |
