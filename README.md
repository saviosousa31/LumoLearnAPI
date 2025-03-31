# LumoLearn API 🎮📚
_Este repositório contém o código-fonte da API RESTful para um aplicativo de Quiz desenvolvido em Java._



**LumoLearn** é uma aplicação de Quiz desenvolvida em Java, utilizando o **Spring Framework** e um banco de dados MySQL. O projeto tem como **objetivo** criar uma plataforma de **aprendizado** onde os usuários podem **criar** Cursos, Temas e Questões **personalizadas**, jogar quizzes de maneira individual e acompanhar seu desempenho. A solução será escalável e modular, com planos para integrar Inteligência Artificial para geração automática de questões e futuras funcionalidades de interação social, como desafios entre amigos. 🤖🎓

## Funcionalidades Principais 🚀

### 1. Cadastro de Cursos e Temas 📋
- O usuário poderá cadastrar **Cursos** e **Temas** relacionados aos cursos.
- Cada **Tema** estará vinculado a um **Curso**, e o usuário poderá adicionar **Questões** específicas a cada **Tema**.

### 2. Cadastro de Questões ❓
- O usuário pode adicionar questões com **5 alternativas** (apenas uma correta).
- Cada questão será associada a um **Tema** e **Curso** específicos, respectivamente.

### 3. Jogo Individual (Modo Local) 🎮
- O usuário poderá jogar quizzes com base nos **Cursos**, **Temas** ou no modo **Aleatório**.
- O usuário escolherá a quantidade de questões a ser respondida.
- Após cada resposta, a aplicação marcará se a alternativa escolhida está correta ou incorreta e mostrará a explicação da questão.
- No final do jogo, o histórico será exibido, mostrando quais questões foram acertadas e quais foram erradas.

### 4. Futuras Funcionalidades 🌟
- Integração com uma Inteligência Artificial para gerar questões automaticamente.
- Funcionalidade de desafiar amigos e criar competições.
- Contagem de dias consecutivos jogando e itens de loja para personalização.

## Tecnologias Utilizadas 🛠️

- **Backend**:
  - Java 21
  - Spring Boot
  - Maven
  - JUnit (Testes Unitários)

- **Banco de Dados**:
  - MySQL (Inicialmente)
  - Possível migração para MongoDB no futuro

- **Containerização**:
  - Docker
  - Kubernetes

- **Mensageria**:
  - Kafka ou RabbitMQ (Para comunicação assíncrona entre microsserviços)

#### Desenvolvido por:
Sávio Soares Sousa 👨‍💻
