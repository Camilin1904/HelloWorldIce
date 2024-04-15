import Demo.CallbackPrx;
import com.zeroc.Ice.ObjectPrx;

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
            Demo.PrinterPrx service = Demo.PrinterPrx.checkedCast(
                communicator.propertyToProxy("Printer.Proxy")).ice_twoway().ice_timeout(-1).ice_secure(false);
            //Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            Demo.PrinterPrx printer = service;

            if(printer == null)
            {
                throw new Error("Invalid proxy");
            }
            String input="";

            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("Callback");
            com.zeroc.Ice.Object object = new CallbackImpl();
            ObjectPrx prx = adapter.add(object, com.zeroc.Ice.Util.stringToIdentity("CallbackService"));
            adapter.activate();

            CallbackPrx clprx=CallbackPrx.uncheckedCast(prx);
            while(true){
                input = scan.nextLine();
                long time = System.currentTimeMillis();
                if(input.equals("exit")) break;
                String SystemName = "";
                try {
                    SystemName = InetAddress.getLocalHost().getHostName();
                }
                catch (Exception E) {
                    System.err.println(E.getMessage());
                }
                input = System.getProperty("user.name") + ":" + SystemName + ":" + input + ";Time:" + time;
                printer.printString(input, clprx);
                //System.out.println(res);
            }

        }
    }
}