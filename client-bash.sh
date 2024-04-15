#!/bin/bash

echo "Corriendo clientes..."

log_file="logs/timeout.log"
> "$log_file"

exec > >(tee -a "$log_file") 2>&1
for i in {0..100} 
do
    java -jar client/build/libs/Client.jar &
done

exec >&-
