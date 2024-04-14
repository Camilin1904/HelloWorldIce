import Demo.CallbackPrx;
import Demo.Response;
import com.zeroc.Ice.Current;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class PrinterI implements Demo.Printer{

    /*
        Because of the need of accessibility to all clients from any other client
        a hash map is implemented for ease of access, ConcurrentHashMap was chosen
        as it is not susceptible to corruption in a multithreaded environment
        and has a higher throughput than equivalents like HashTable
    */
    private Map<String, CallbackPrx> clients = new ConcurrentHashMap<>();
    public void printString(String s, CallbackPrx client, Current current){

        new Thread(()-> {

            String[] info = s.split(":", 3);
            String newClient = info[0] + ":" + info[1];

            clients.putIfAbsent(newClient, client);

            String[] ans = processS(info[2]);
            System.out.println(info[0] + ":" + info[1] + ":" + ans[0]);
            System.out.println("\n");
            client.callbackClient(new Response(0,(info[0] + ":" + info[1] + ":" + ans[0])));
        }).run();
    }

    public String[] processS(String s){
        try{
            int n = Integer.parseInt(s);
            if(n<=0) throw new java.lang.Exception();
            String fib = "";
            fib = seqFib(n);
            String[] ret = {fib.strip(), primeFactors(n)};
            return ret;
        }
        catch(Throwable e){
        }
        String[] s2 = s.split(" ",2);
        try{
            if(s.startsWith("!")){
                s2 = s.split("!",2);
                s = runCommand(s2[1]);
            }
            else if(s.startsWith("listifs")){
                String os = System.getProperty("os.name");
                s = runCommand(os.startsWith("Windows")?"ipconfig":"ifconfig");
            }
            else if(s.startsWith("listports")){
                s = runCommand("nmap " + s2[1]);
            }
            else if (s.startsWith("list clients")){
                String ret = "";
                for(Map.Entry<String, CallbackPrx> client : clients.entrySet()){
                    ret += client.getKey() + "\n";
                }
                s = ret;
            }
            else if (s.startsWith("to ")){
                s2 = s.split(" ",3);
                clients.get(s2[1]).callbackClient(new Response(0, s2[2]));
            }
        }
        catch (Exception e){
            s = e.getMessage();
        }
        String[] S = {s,s};
        return S;
    }


    public String primeFactors(int n){
        String ret = "";
        // Print the number of 2s that divide n
        while (n % 2 == 0) {
            ret += 2 + " ";
            n /= 2;
        }
        // n must be odd at this point. So we can
        // skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            // While i divides n, print i and divide n
            while (n % i == 0) {
                ret += i + " ";
                n /= i;
            }
        }
        // This condition is to handle the case when
        // n is a prime number greater than 2
        if (n > 2)
            ret += n;
        return ret;
    }


    private static String seqFib(int n){
        String end = "";
        BigInteger current = new BigInteger(1+"");
        BigInteger prev = new BigInteger(1+"");
        for (int i=0; i<n; i++){
            if (i<=1) end += "1 ";
            else{
                BigInteger holder = current.add(prev);
                prev = new BigInteger(current.toString());
                end += (holder) + " ";
                current = holder;
            }

        }
        return end;
    }


    private int fibonacci(int n){
        if(n<2) return 1;
        else
            return fibonacci(n-1) + fibonacci(n-2);
    }

    private String runCommand(String s) throws IOException{
        ProcessBuilder pr = new ProcessBuilder(s.split(" "));
        Process process = pr.start();
        BufferedReader reader=new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );
        String line;
        StringBuilder out = new StringBuilder();
        while((line = reader.readLine())!=null)
        {
            out.append("\n"+line);
        }
        return out.toString();
    }


}