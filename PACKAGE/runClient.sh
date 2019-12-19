#!/bin/bash
if [ "$#" -ne 6 ]
then
  echo "Usage: $0 <hostURL> <hostSslSocketPort> <keystorePath> <keystorePassword> <truststorePath> <truststorePassword>"
  exit 1
fi

cd ..
mvn exec:java -Dexec.mainClass="main.java.sys.rest.client.Client" -Dexec.args="$1 $2 $3 $4 $5 $6"
cd PACKAGE
