package future.test002;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//测试一：FutureTask 取消执行任务
//首先创建一个FutureTask对象，实现一个内部类Callable
//由于FutureTask具有Runnable的特性，因此，运行FutureTask的方式，就有两种：第一，交给Thread；第二，交个线程池运行
//主线程，休息3秒钟，
//判断子线程是否结束，如果没有结束的话，就强制关闭子线程FutureTask
public class FutureTaskTest {
    public static void main(String[] args) throws Exception{

        FutureTask futureTask = new FutureTask(new Callable() {
            int i = 0;
            @Override
            public Object call() throws Exception {

                while(i < 100) {
                    System.out.println("----->:\t" + "hello, beijing" + " : " + i++);

                    //模拟，1秒钟打印一次
                    Thread.sleep(1000);
                }
                return i;
            }
        });

        Thread thread = new Thread(futureTask);

        thread.start();

        //主线程休息3秒钟，
        Thread.sleep(3000);

        if (!futureTask.isDone()) {
            System.out.println("-----结束----子线程的运行-----");
            futureTask.cancel(true);
        }
    }
}
