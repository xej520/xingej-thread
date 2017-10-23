package future.test002;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//测试一： 在FutureTask 内部再启动一个线程，查看是否也能取消掉
//结论: 创建的内部线程，并没有结束
public class FutureTaskTest2 {
    public static void main(String[] args) throws Exception{

        FutureTask futureTask = new FutureTask(new Callable() {
            int i = 0;
            @Override
            public Object call() throws Exception {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(i < 100) {
                            System.out.println("----->:\t" + "hello, beijing" + " : " + i++);

                            //模拟，1秒钟打印一次
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();


                return i;
            }
        });

        Thread thread = new Thread(futureTask);

        thread.start();
        System.out.println("-----结束------------1-----");
        System.out.println("-----结束----3-----:\t" + futureTask.isDone());
        //主线程休息3秒钟，
        Thread.sleep(1000);
        System.out.println("-----结束------------2-----");

        System.out.println("-----结束----3-----:\t" + futureTask.isDone());

        if (!futureTask.isDone()) {
            System.out.println("-----结束----子线程的运行-----");
            futureTask.cancel(true);
        }
    }
}
