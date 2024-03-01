### By: Camilo Carmona Valencia and Juan Sebastian Libreros

# Steps to deploy and execute the app

## Local machines

* Step 1: Download the archive .zip that is in Intu. Unzip the archive.

* Step 2: Open a terminal-either Bash or Cmd-and go to the project previously downloaded. You must be in the root folder of the project. Repeat this step one more time. You should have two terminals.

* Step 3: Then run the following comands in one of the two consoles:
    

        ./gradle clean


wait until it finish the cleaning process, then

    ./gradle build

wait until it finish the building process, then

    java -jar server/build/libs/server.jar

repeat the same process on the other terminal but replace the last command with 

    java -jar client/build/libs/client.jar

Note: Be sure that the client has the same IP of the server, and if the local machine, use localhost
## Before the remote machine execution

When you want to deploy the app on another device you must do: 

* First, if you want to run this program from a server you must first pass the jar files to the machine, you must compile the code and use the following commands while on the root folder of the project:

        scp server/src/builds/libs/server.jar <username>@<ip/name>:<folder direction>

* We recomend you to send the ice version included with this project to ensure the program works correctly:

        scp ice-3.7.6.jar <username>@<ip/name>:<folder direction>

* With both of this things in the machine, you must now modify the manifest of the server file so that it detects ice.

* First you must connect to the machine with ssh:

        ssh <username>@<ip/name>

* After running this command you must enter your password, when you are in navigate to the folder where both the server and ice are stored, then run the command "mc" in the console, this must open a text user interface, when you are in it, click on the server jar, navigate to the folder META-INF and click on the manifiesto, it will not open so you must press f4, this will then open a text file, press i to edit it, change the direction of ice presented there to the direction of ice in your folder, and press esc, followed by :wq to save the changes.
    
## Remote machine

In this case, you got to do the same as before, even though there is an extra step.

* You got go to the archives named "config.client" and "config.server". You got do the following:

    1. Go to the file "config.server" and search the line
        
            Ice.Default.Host=<ipconfigserver> 
        
        and change the field \<ipconfigserver> to the one you want to use as the host. 
    2. Do the same in the file "config.client" and put in the same line the same ip of the server

            Ice.Default.Host=<ipconfigserver>

    And that's the only thing that changes, then do the step as the local machine execution





