package future;

import java.util.concurrent.*;

//方案A
//并行，先来先处理
public class PlanB {
    public static void main(String[] args) throws Exception{
        //指定一个线程池
        ExecutorService pool = Executors.newCachedThreadPool();

        //返回的结果是按照，任务的完成顺序返回的
        //如线程A，先完成，就先获取，
        CompletionService<Boolean> queue = new ExecutorCompletionService<Boolean>(pool);

        for(int i = 0; i < 5; i++) {
            queue.submit(new MySleepThread(4));
        }

        boolean flag = true;
        //开始依次遍历获取执行结果， 串行执行
        for(int i = 0; i< 5; i++) {
            //一定要清楚，这里获得的结果，一定是先完成任务的线程，
            flag = queue.take().get();
            if (!flag) {
                // 只要一个线程，执行结果不符合要求，就立刻结束此循环，
                // 不再关心其他线程的执行结果
                break;
            }
        }

        if (flag) {
            System.out.println("-----全部执行成功----");
        } else {
            System.out.println("-----某个task---执行失败----异常逻辑处理---如回滚");
        }

        pool.shutdown();
    }
}
