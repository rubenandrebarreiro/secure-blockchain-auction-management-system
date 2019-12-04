#!/bin/bash
declare -a toDo=("keystores"
                 "cruststores"
                 "certificates")

#keystores
mv **/*Keystore.jks ../../keystores

#truststores
mv **/*Truststore.jks ../../truststores

#certificates
mv **/*.pem ../../certificates

#remove all folders
find . -type d -exec rm -rf {} +
