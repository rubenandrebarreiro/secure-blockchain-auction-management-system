#!/bin/bash
repositoryPort=8080
clientURL=localhost
clientPort=8443

clientKeystorePath="res/keystores/eduardoKeystore.jks"
clientTruststorePath="res/truststores/eduardoTruststore.jks"
clientKeystorePassword="eduardo1920"
clientTruststorePassword="eduardo1920"

mvn clean compile
gnome-terminal -e 'sh -c "./runAuctionServerRepository.sh'" $repositoryPort "'"'
gnome-terminal -e 'sh -c "./runAuctionServer.sh;"'
sleep 1
gnome-terminal -e 'sh -c "./runClient.sh'" $clientURL $clientPort $clientKeystorePath $clientKeystorePassword $clientTruststorePath $clientTruststorePassword"'"'