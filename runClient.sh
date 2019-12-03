#!/bin/bash
if [ "$#" -ne 2 ]
then
  echo "Usage: $0 <hostURL> <hostSslSocketPort>"
  exit 1
fi

mvn exec:java -Dexec.mainClass="main.java.sys.rest.client.Client" -Dexec.args="$1 $2"
