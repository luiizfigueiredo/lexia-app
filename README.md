# Lexia

O Lexia e um aplicativo Android de Comunicacao Alternativa e Aumentativa (CAA), criado para apoiar a comunicacao de pessoas com dificuldades de fala.

A proposta e oferecer uma prancha de comunicacao simples, na qual a pessoa pode selecionar pictogramas, montar frases e ouvir o resultado por meio da voz do dispositivo.

## Visao geral

O projeto esta sendo desenvolvido inicialmente como um MVP focado no Modo Crianca, com uma experiencia visual direta, acessivel e livre de dependencia de internet.

Entre os objetivos do app estao:

- Facilitar a comunicacao por meio de cartoes e categorias.
- Permitir a montagem de frases de forma intuitiva.
- Reproduzir frases usando o mecanismo de voz nativo do Android.
- Funcionar prioritariamente de forma offline.

Funcionalidades de personalizacao, gerenciamento por pais ou terapeutas e sincronizacao em nuvem ficam previstas para fases futuras.

## Tecnologias

- Kotlin
- Jetpack Compose
- Material 3
- Android SDK
- MVVM com StateFlow
- Gradle Kotlin DSL

## Estrutura

O projeto segue uma organizacao orientada a funcionalidades, separando telas, componentes compartilhados, dados e configuracoes comuns da aplicacao.

## Como executar

Requisitos:

- Java 17
- Android SDK com a plataforma Android 35

Para gerar a versao de debug, execute:

```bash
./gradlew assembleDebug
```

Para verificar o estilo do codigo:

```bash
./gradlew ktlintCheck
```

## Status

O Lexia esta em desenvolvimento. A interface inicial e a base do projeto Android ja estao configuradas, enquanto as interacoes e os recursos de comunicacao continuam sendo construidos.
