#!/bin/bash

if [ "$#" -ne 2 ]
then
  echo "Usage: $0 <name> <rootCAName>"
  exit 1
fi

if [ ! -d "$2CA/" ]
then
  echo "Certificate Authority $2CA folder does not exist"
  exit 1
fi

mkdir -p $1/
cd $1

echo -e "\u001b[32m Generate keystore and keypair \e[0m"
keytool -genkeypair -alias $1 -dname "cn=$1" -validity 10000 -keyalg RSA -keysize 2048 -keystore $1Keystore.jks -keypass $11920 -storepass $11920

echo -e "\u001b[32m Generate signed certificate \e[0m"
keytool -keystore $1Keystore.jks -storepass $11920 -certreq -alias $1 | keytool -keystore ../$2CA/$2.jks -storepass $21920 -gencert -alias $2 -ext ku:c=dig,keyEnc -rfc > $1.pem

echo -e "\u001b[32m Add to truststore \e[0m"
keytool -keystore $1Truststore.jks -storepass $11920 -importcert -trustcacerts -noprompt -alias $2 -file ../$2CA/$2.pem
keytool -keystore $1Truststore.jks -storepass $11920 -importcert -alias $1 -file $1.pem

cd ..
