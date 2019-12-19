# Secure Blocchain-Enabled Auction Management System

O programa está dividido em 3 partes. Dentro da pasta **PACKAGE** encontram-se scripts para executar cada uma das partes ou as 3 de uma só vez. Os scripts estão feitos para serem corridos a partir da própria pasta **PACKAGE**.

É de notar que o script *runAll.sh* está depedente do terminal *gnome-terminal* portanto não corre em sistemas sem esse terminal sem modificações. Neste caso, é para correr os scripts indicados de seguida para cada programa a executar.

**AuctionServerRepository**
É o servidor que guarda os dados em relação a **auctions** e **bids**.
É iniciado com o script *runAuctionServerRepository.sh* e inicia o servidor automaticamente com a porta 8080.

**AuctionServer**
É o servidor que interage com os clientes e o repositório. Precisa de ser iniciado antes dos clientes. 

É iniciado com o script *runAuctionServer.sh*. Inicia um socket na porta 8443 para ligações TLS e as comunicações para o repositório são feitas sobre a porta 8080.

As configurações que o servidor vai usar sobre TLS está em  *res/configurations/auction-server-tls-configuration.conf*. 
As configurações que o servidor vai usar sobre keystores e truststores está em *res/configurations/auction-server-keystore-configuration.conf*

**Client**
A aplicação cliente é a que um utilizador usa para se ligar ao **AuctionServer** e que permite efetuar operações sobre **auctions** e **bids**. 

É iniciado com o script *runClient.sh*  e precisa de 6 argumentos. 
1. O endereço de ligação ao AuctionServer. 
2. A porta TLS.
3. O caminho da keystore do utilizador.
4. A password da keystore.
5. O caminho da truststore do utilizador.
6. A password da truststore.

Outras configurações sobre TLS estão no ficheiro *res/configurations/client-tls-configuration.conf*

Quando se liga o cliente, é pedida um utilizador e palavra passe. Para efeitos de teste, para a criação de tudo o que são utilizadores e palavras passe, foram usadas as seguintes regras:
1. O utilizador pode ser o nome que quiser
2. A palavra passa do utilizador será *nomeDoUtilizador*1920
Os utilizadores que o cliente aceita estão definidos em *res/database/client/users.db*. As passwords são o resultado da função de hash SHA-256.

Como exemplo, utilizador: henrique, palavra-passe: henrique1920

Depois de o login ficar feito, é indicada uma lista de operações que o cliente pode efetuar.

***
###Criação de stores e certificados
***
Na pasta *res/scripts/createStoresAndSignedCerts/* encontram-se vários scripts, *createRootCA.sh* para a criação de uma root CA, *createUserCA.sh* para a criação de um user com certificado assinado a partir de uma rootCA criada anteriormente e *createForManyUsers.sh* que contem uma lista de utilizadores que pode ser costumizada a gosto.

Finalmente, o último script, *moveEverythingToPlace.sh* pega nos ficheiros gerados pelos scripts indicados anteriormente e introduz tudo nos locais necessários para os servidores e cliente utilizarem.
 
