#!/usr/bin/expect -f
cd Documents/CarmonaLibrerosCallback

simulateClient(){
    echo "10000"  
    sleep 200
    echo "exit"
}

simulateClient | java -jar client.jar

 
