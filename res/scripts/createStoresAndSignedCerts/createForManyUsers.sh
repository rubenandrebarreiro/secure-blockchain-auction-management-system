#!/bin/bash

if [ "$#" -ne 1 ]
then
  echo "Usage: $0 <rootCAName>"
  exit 1
fi

PATH=$(echo $PATH:$(pwd))
#echo $PATH
declare -a names=("auctionServer" 
                  "eduardo"
                  "ruben"
                  "hj"
                  "luis"
                  "legatheaux")

if [ ! -d "$1CA/" ]
then
  createRootCA.sh $1
fi

for name in "${names[@]}"
do
  createUserCA.sh $name $1
done
