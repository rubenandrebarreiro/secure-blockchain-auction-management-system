#!/bin/bash

if [ "$#" -ne 1 ]
then
  echo "Usage: $0 <name> "
  exit 1
fi

mkdir -p $1CA/
cd $1CA

keytool -genkeypair -alias $1 -dname "cn=RootCA,c=PT" -validity 10000 -keyalg RSA -keysize 2048 -ext bc=ca:true -keystore $1Keystore.jks -keypass $11920 -storepass $11920
keytool -exportcert -rfc -keystore $1Keystore.jks -alias $1 -storepass $11920 > $1.pem

cd ..
