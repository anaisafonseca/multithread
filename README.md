# Implementação de um servidor Web Multithreaded

## 1. Objetivo
O objetivo deste trabalho é a implementação de um servidor Web "multithreaded", que deverá ser capaz de processar múltiplas requisições simultâneas de serviços em paralelo.


## 2. Introdução
O servidor Web é um dos componentes da aplicação Web e baseia-se em um programa servidor. Ao ser executado, o processo servidor é criado e ele mantém se "na escuta" para clientes Web que desejam extrair documentos por meio do protocolo HTTP. 

$\space$ Através de conexões TCP, esse servidor recebe pedidos (requests) e envia respostas (responses) até que o cliente decida finalizar a conexão.


## 3. Desenvolvimento
O código apresentado foi desenvolvido baseando-se no texto "Tarefa de Programação 1: Construindo um servidor Web multithreaded", disponível na sub-pasta "Primeiro Trabalho" da pasta "Trabalhos" do canal Geral da equipe Redes de Comunicações I no Microsoft Teams.

A versão 1.0 do HTTP foi implementada, definida na RFC-1945, na qual requisições HTTP separadas são enviadas a cada componente da página Web.  Na thread principal, o servidor "escuta" uma porta fixa e, quando recebe uma requisição de conexão TCP, ele ajusta a conexão TCP através de outra porta e atende essa requisição em uma thread separada.

O código desenvolvido possui nome de "WebServer.java" e pode ser visto na pasta "multithreaded" enviada juntamente a este relatório.


## 4. Testes
A fim de testar este seridor, foram utilizados quatro arquivos principais: "index.html", "dancing.gif", "relatorio.pdf" e "milkshake.jpeg". Todos eles estão disponíveis na pasta "multithreaded" mencionada anteriormente.

Antes de explorar cada um dos testes, é válido ressaltar que nosso servidor foi definido para ser executado na porta 2222 do localhost. Então, caso o cliente não especifique a porta, não será possível conectar-se ao servidor. Também, é necessário que o arquivo desejado também seja especificado na URL. Portanto, a única maneira de acessar os quatro arquivos mencionados (html, gif, pdf e jpeg) é através destas quatro URLs:

    1- http://localhost:2222/index.html
    2- http://localhost:2222/dancing.gif
    3- http://localhost:2222/relatorio.pdf
    4- http://localhost:2222/milkshake.jpeg

Se o cliente tentar inserir qualquer outro link, seja sem especificar o arquivo ou o tipo do arquivo (como colocar apenas "dancing" ao invés de "dancing.gif"), foi definido que o servidor envie uma mensagem de resposta com a fonte HTML exibindo o erro na janela do browser:

![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/1c3d3207-97a5-428e-9c0f-8afc8255abe6/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211103%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211103T184435Z&X-Amz-Expires=86400&X-Amz-Signature=476495371bb5f584bf16423c9e55023ac0fc2759fa60ff617a8117f48984c414&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

A seguir, serão explorados os quatro testes feitos, considerando que todas URLs tenham sido inseridas corretamente, como comentado anteriormente.


### 4.1 index.html 
Ao inserirmos a primeira URL para teste, obtemos como resposta do servidor a seguinte página HTML no browser: 

![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/ae45aa4d-1b87-40e6-a817-87cb55a32d06/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211103%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211103T184808Z&X-Amz-Expires=86400&X-Amz-Signature=9611a4b084f4d3a1eca3b94a3a923c8b87f7d4e99932c2611e0e1fa17f5d79d5&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

Já no terminal, podemos confirmar que tudo correu bem. As seguintes informações são printadas:

![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/aa873c59-90e1-435c-aaf0-e86e4ea6e31f/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211103%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211103T184852Z&X-Amz-Expires=86400&X-Amz-Signature=1af8fb2351d64a232a1d88731b16e5a41bcfcf657998c20a3939779175f25595&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

O segundo "bloco" de informações é mostrado pelo fato de o browser carregar o arquivo "index.html".


### 4.2 dancing.gif
Ao acessar a segunda URL para teste, o servidor nos responde o gif "dancing.gif" (presente na pasta multithreaded enviada):

![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/d7982e35-8389-46b0-baae-5ebd427738c1/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211103%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211103T185011Z&X-Amz-Expires=86400&X-Amz-Signature=c5111434410685bb9703c9fb2b90c6be635d63ae65d0219092878d1ec81e47e7&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

Novamente, as informações mostradas no terminal nos confirmam que tudo correu como esperado:

![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/fe19580b-ba5d-467d-92ff-32a075374172/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211103%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211103T185019Z&X-Amz-Expires=86400&X-Amz-Signature=83f4e9a03e28d0aaac502dc464e218fcf5cb51e7e972d194a5b43d0a5e888533&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)


### 4.3 relatorio.pdf
Neste teste, por se tratar de um pdf, o arquivo é baixado ao entrar na terceira URL. Observa-se que o arquivo foi baixado na parte inferior esquerda do navegador:

![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/9947e39a-7e48-4c15-b0cf-6b9b394dc638/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211103%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211103T185057Z&X-Amz-Expires=86400&X-Amz-Signature=cd9971e6461367c32a4ab3f3c4743bc936bea90948e5111fb584a9d11e4e8ad1&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

Já no terminal, confirma-se, novamente, que tudo correu bem:

![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/b7e8dafc-bbbd-4dc7-8c86-f8444f470268/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211103%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211103T185106Z&X-Amz-Expires=86400&X-Amz-Signature=e1ea1a898de9a8c2485ef2a44ec36ff64bf7224c59a0690ed793b6e86ae32427&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)


### 4.4 milkshake.jpeg
Na quarta (e última) URL, o navegador nos reponde a imagem "milkshake.jpeg" (presente na pasta multithreaded enviada):

![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/7a1e646a-97c3-4d98-b68c-1f6a22762280/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211103%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211103T185209Z&X-Amz-Expires=86400&X-Amz-Signature=bb272bf5f8d02fb7b1cbf067ab30daeec4c486883ea675e6ff01fb863a6c006c&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

Aqui, também foi possível conferir que o processo se saiu como esperado através do terminal:

![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/baf89b79-6520-4744-b0dc-1dbf59df746b/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211103%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211103T185217Z&X-Amz-Expires=86400&X-Amz-Signature=78d63acd351f563c3190f65ee057335820e600bd5969d8999dc31f2b7bd20f8c&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22)

Assim, conclui-se os testes com sucesso em todos eles.


## 5. Conclusão 
Esse trabalho permitiu melhor compreensão e análise de um servidor multithreaded, passo importante no desevolvimento dos estudantes da turma da disciplina.
