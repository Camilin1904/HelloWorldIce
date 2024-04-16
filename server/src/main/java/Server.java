import java.io.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public class Server
{
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args)
    {
        java.util.List<String> extraArgs = new java.util.ArrayList<String>();

        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args,"config.server",extraArgs))
        {
            AtomicInteger leftover = new AtomicInteger(0);
            AtomicInteger processed = new AtomicInteger(0);
            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("Printer");
            com.zeroc.Ice.Object object = new PrinterI(leftover, processed);
            adapter.add(object, com.zeroc.Ice.Util.stringToIdentity("SimplePrinter"));
            adapter.activate();
            communicator.waitForShutdown();
        }


    }

    public static void f(String m)
    {
        String str = null, output = "";

        InputStream s;
        BufferedReader r;

        try {
            Process p = Runtime.getRuntime().exec(m);

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream())); 
            while ((str = br.readLine()) != null) 
                output += str + System.getProperty("line.separator");
            br.close(); 
        }
        catch(Exception ex) {
        }
    }



}