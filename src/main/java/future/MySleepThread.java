package future;

import java.util.Random;
import java.util.concurrent.Callable;

public class MySleepThread implements Callable<Boolean> {
    private int timeout = 0;

    public MySleepThread(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public Boolean call() throws Exception {
        int sleepTime = (new Random().nextInt(10) +1 ) * 1000;
        System.out.println(Thread.currentThread().getName() + "--sleep--time--:\t" + sleepTime / 1000 + " 秒");

        Thread.sleep(sleepTime);

        //判断该线程休息的时间，是否小于 超时时间
        //如果小于的话，就返回true
        return sleepTime < timeout;
    }
}
