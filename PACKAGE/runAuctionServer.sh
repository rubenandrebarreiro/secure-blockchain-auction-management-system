#!/bin/bash
cd ..
mvn exec:java -Dexec.mainClass="main.java.sys.rest.server.auction.AuctionServerEntryPoint"
cd PACKAGE
