import com.zeroc.Ice.Current;

import Demo.Response;

public class CallbackImpl implements Demo.Callback{
    @Override
    public void callbackClient(Response response, Current current) {

        String[] answer = response.value.split(";Time:", 2);
        long time = System.currentTimeMillis();
        time = time - Long.parseLong(answer[1]);
        double realTime = time;

        System.out.println("\nCallback Response\n" + answer[0] + "\nResponse Time: " + realTime);
    }
}
