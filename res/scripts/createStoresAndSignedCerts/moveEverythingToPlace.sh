#!/bin/bash

# Move keystores
mv **/*Keystore.jks ../../keystores

# Move truststores
mv **/*Truststore.jks ../../truststores

# Move chain certificates
mv **/*Chain.pem ../../certificates

# Remove non-chain certificates
rm **/*.pem

# Remove all folders
find . -type d -exec rm -rf {} +
