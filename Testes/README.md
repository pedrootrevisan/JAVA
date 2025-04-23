
# Projeto Conta Bancária - Testes Automatizados

Este projeto implementa uma conta bancária com três estados (PRATA, OURO, VERMELHO), utilizando o padrão de projeto State e testes unitários com JUnit 5.

## Estrutura do Projeto

- `src/main/java/com/ufu/esof/` → Código fonte principal
- `src/test/java/com/ufu/esof/` → Testes unitários com JUnit
- `pom.xml` → Configuração Maven

## Como Compilar e Rodar

### Pré-requisitos:
- Java 11 ou superior
- Maven 3.6+

### Para compilar:
```bash
mvn compile
```

### Para rodar os testes:
```bash
mvn test
```

### Para importar no IntelliJ:
1. Vá em `File > Open` e selecione o `pom.xml`
2. O IntelliJ reconhecerá o projeto automaticamente como Maven
3. Rode os testes com botão direito na classe `ContaTest.java`

## Testes Automatizados

Os testes validam as seguintes regras:
- Estado muda para OURO quando saldo > 1000
- Rendimento de 5% ao depositar em OURO
- Saques com taxa no estado PRATA
- Saques bloqueados no estado VERMELHO
- Mudanças de estado dinâmicas conforme saldo

---
