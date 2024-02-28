import com.zeroc.Ice.Current;

import java.io.*;

public class PrinterI implements Demo.Printer{
    public String printString(String s, Current current){
        String[] info = s.split(":",3);

        String[] ans = processS(info[2]);
        System.out.println(info[0] + ":" + info[1] + ":" + ans[0]);
        System.out.println("\n");
        return info[0] + ":" + info[1] + ":" + ans[1];
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


    private String seqFib(int n){
        String end = "";
        for (int i=0; i<n; i++){
            end += fibonacci(i) + " ";
        }
        return end.trim();
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