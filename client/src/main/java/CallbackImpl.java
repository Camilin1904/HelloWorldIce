import com.zeroc.Ice.Current;

import Demo.Response;

public class CallbackImpl implements Demo.Callback{
    @Override
    public void callbackClient(Response response, Current current) {
        System.out.println("Maybe");
        System.out.println("Callback Response" + response.value);
    }
}
