# ChatGPT no Android?

Esse app de chat criado em um fim de semana serve de referência sobre como utilizar a API do ChatGPT para Kotlin, em um projeto Android.


Para usar o app você precisa criar uma chave de acesso no site oficial da OpenAi e inseri-la dentro do aplicativo.
Essa chave tem uma cota diária de uso, então limitei o tamanho das respostas através do pametro `maxTokens`.

[Gere sua chave aqui][generateKey]

<img src="https://user-images.githubusercontent.com/35709152/216869895-f58e5dc6-0c06-4459-acbb-941760815425.gif" alt = "gif demo app" width="300">


[Documentação API ChatGPT][docgpt] 

API Kotlin:
[openai-kotlin][openai-kotlin] feita por [Mouaad Aallam][MouaadAallam]


[openai-kotlin]: https://github.com/Aallam/openai-kotlin
[MouaadAallam]: https://github.com/Aallam


[generateKey]: https://platform.openai.com/account/api-keys

[docgpt]: https://platform.openai.com/docs/libraries/community-libraries
