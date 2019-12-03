#!/bin/bash
mvn exec:java -Dexec.mainClass="main.java.sys.rest.client.Client" -Dexec.args="$1 $2"
