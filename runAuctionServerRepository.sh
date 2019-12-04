#!/bin/bash
if [ "$#" -ne 1 ]
then
  echo "Usage: $0 <port> "
  exit 1
fi

mvn exec:java -Dexec.mainClass="main.java.sys.rest.server.auction.repository.AuctionRepositoryServer" -Dexec.args="$1"
