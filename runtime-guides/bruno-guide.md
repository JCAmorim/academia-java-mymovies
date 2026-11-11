# 🧪 Guia Prático do Cliente HTTP Bruno

> **Academia Java Backend Developer**  
> Ficheiro de referência para testes de chamadas HTTP, gestão de coleções, ambientes e autenticação JWT na API MyMovies.

---

## 📌 Porquê o Bruno?

O **Bruno** é um cliente HTTP *Open Source* e *offline-first*. Ao contrário de outras ferramentas, o Bruno armazena as requisições em ficheiros de texto simples (com extensão `.bru`) diretamente na pasta do projeto (`http/`), permitindo que as chamadas sejam versionadas no Git juntamente com o código Java.

---

## Pré-Requisito

* Instalação do Bruno na máquina local
  https://www.usebruno.com/downloads 

---


## 🚀 1. Abrir a Coleção do Projeto

1. Inicie a aplicação **Bruno**.
2. Clique em **Open Collection**.
3. Navegue até à pasta do projeto e selecione o diretório **`http/`**.
4. A estrutura de chamadas organizadas por aula aparecerá no painel esquerdo.

---

## 📝 2. Estrutura de uma Requisição HTTP

Para testar qualquer endpoint da API, verifique os 4 elementos principais:

1. **HTTP Method:** `GET`, `POST`, `PUT`, `DELETE` ou `PATCH`.
2. **URL:** Endereço do recurso (ex: `http://localhost:8080/api/movies`).
3. **Headers:** Cabeçalhos da requisição (ex: `Content-Type: application/json`).
4. **Body:** O JSON enviado nas requisições `POST` e `PUT`.

---

## ⚙️ 3. Configurar Ambientes (Environments)

Para evitar repetir `http://localhost:8080` em todas as chamadas:

1. No canto superior direito do Bruno, selecione **No Environment > Configure**.
2. Crie um ambiente chamado **Local**.
3. Adicione a variável:
   * **Name:** `baseUrl`
   * **Value:** `http://localhost:8080`
4. Nas URLs das requisições, passe a utilizar: `{{baseUrl}}/api/movies`.

---

## 🔐 4. Autenticação com Token JWT (Aula 6)

Na Aula 6, quando os endpoints forem protegidos pelo Spring Security:

1. Execute a requisição `POST {{baseUrl}}/api/v1/auth/login` informando utilizador e senha.
2. Copie o valor do `token` (JWT) retornado no corpo da resposta.
3. Na requisição que deseja testar, vá à aba **Auth**.
4. Selecione o tipo **Bearer Token**.
5. Cole o JWT no campo **Token**.
6. Clique em **Send**. O Bruno enviará automaticamente o cabeçalho:  
   `Authorization: Bearer eyJhbGciOiJKV1QiLC...`

---

## 💡 5. Dicas de Diagnóstico Rápido

* **Status `200 OK` / `201 Created`:** Requisição processada com sucesso.
* **Status `400 Bad Request`:** Falha de validação nos dados enviados (verifique o corpo da resposta).
* **Status `401 Unauthorized`:** Token ausente, expirado ou inválido.
* **Status `403 Forbidden`:** Utilizador autenticado, mas sem permissão/role para o recurso.
* **Status `404 Not Found`:** Endpoint incorreto ou ID de recurso inexistente no banco de dados.
