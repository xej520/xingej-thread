package future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
//方案A
//串行，验证多线程执行的结果
public class PlanA {

    public static void main(String[] args) throws Exception{
        //指定一个线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        List<Future> list = new ArrayList<Future>();

        for(int i = 0; i < 5; i++) {
           Future<Boolean> future = pool.submit(new MySleepThread(4));
           list.add(future);
        }

        boolean flag = true;
        //开始依次遍历获取执行结果， 串行执行
        for(int i = 0; i< 5; i++) {
            flag = (Boolean)list.get(i).get();
            if (!flag) {
                //不再关心后面的结果
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
