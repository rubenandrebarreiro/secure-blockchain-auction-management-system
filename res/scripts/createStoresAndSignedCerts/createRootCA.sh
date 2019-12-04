#!/bin/bash

if [ "$#" -ne 1 ]
then
  echo "Usage: $0 <name> "
  exit 1
fi

mkdir -p rootCA/
cd rootCA

keytool -genkeypair -alias $1 -dname "cn=RootCA" -validity 10000 -keyalg RSA -keysize 2048 -ext bc:c -keystore $1.jks -keypass $11920 -storepass $11920
keytool -exportcert -rfc -keystore $1.jks -alias $1 -storepass $11920 > $1.pem

cd ..
