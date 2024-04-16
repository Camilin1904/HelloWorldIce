import java.io.*;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;


public class Server
{
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args)
    {
        java.util.List<String> extraArgs = new java.util.ArrayList<String>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        AtomicInteger leftover = new AtomicInteger(0);
        AtomicInteger processed = new AtomicInteger(0);
        Vector<Long> jitter = new Vector<>();

        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args,"config.server",extraArgs))
        {
           
            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("Printer");
            com.zeroc.Ice.Object object = new PrinterI(leftover, processed,jitter);
            adapter.add(object, com.zeroc.Ice.Util.stringToIdentity("SimplePrinter"));
            adapter.activate();
            waitForShutdown(leftover, processed,jitter);
            communicator.shutdown();
              
        }  
    }

    private static void waitForShutdown(AtomicInteger leftover,AtomicInteger processed,Vector<Long> jitter){
        String s;
        System.out.println("Type exit to close the server");
            while (true) {
                s = scan.nextLine();
                if (s.equalsIgnoreCase("exit")) {
                    int processedF = processed.intValue();
                    int unprocessed = leftover.intValue();
            
                    int totalRequests = unprocessed + processedF;
                    double unprocessedRate = unprocessed/totalRequests;
            
                    System.out.println("Statistics\n"+
                    "Total requests: " + totalRequests + "\n" +
                    "Processed requests: " + processedF + "\n"+
                    "Unprocessed requests: "+ unprocessed + "\n"+
                    "Unprocessed Rate: " + unprocessedRate + "%\n"+ 
                    "Jitter: " + calculateJitter(jitter));
                    
                   System.exit(0);
                }
              
            
            } 
    }

    private static long calculateJitter(Vector<Long> jitter){

        long result = 0;

        for (int i = 0; i < jitter.size(); i++) {
            result += jitter.get(i);
        }

        long media = result/jitter.size();
        long sum = 0;

        for (int i = 0; i < jitter.size(); i++) {
            sum += Math.pow((result-media),2);
        }

        result = (long) Math.sqrt(sum/(jitter.size()-1));
        

        return result;

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