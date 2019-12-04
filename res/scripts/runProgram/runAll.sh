#!/bin/bash
repositoryPort=8080
clientURL=localhost
clientPort=8443

mvn clean compile
gnome-terminal -e 'sh -c "./runAuctionServerRepository.sh'" $repositoryPort "'"'
gnome-terminal -e 'sh -c "./runAuctionServer.sh;"'
sleep 1
gnome-terminal -e 'sh -c "./runClient.sh'" $clientURL $clientPort "'"'
