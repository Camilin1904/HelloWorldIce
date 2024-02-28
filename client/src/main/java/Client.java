import java.util.Scanner;
import java.net.InetAddress;

public class Client
{
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args)
    {
        java.util.List<String> extraArgs = new java.util.ArrayList<>();

        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args,"config.client",extraArgs))
        {
            //com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10000");
            Demo.PrinterPrx twoway = Demo.PrinterPrx.checkedCast(
                communicator.propertyToProxy("Printer.Proxy")).ice_twoway().ice_secure(false);
            //Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            Demo.PrinterPrx printer = twoway;

            if(printer == null)
            {
                throw new Error("Invalid proxy");
            }
            String input="";
            while(true){
                input = scan.nextLine();
                if(input.equals("exit")) break;
                String SystemName = "";
                try {
                    SystemName = InetAddress.getLocalHost().getHostName();
                }
                catch (Exception E) {
                    System.err.println(E.getMessage());
                }
                input = System.getProperty("user.name") + ":" + SystemName + ":" + input;
                String res = printer.printString(input);
                System.out.println(res);
            }

        }
    }
}