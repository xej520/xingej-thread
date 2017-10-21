package other;
//主要是学习一下java里的
// 位移运算
// 原因：Java 多线程源码，以及Netty源码中 偶尔会有位移运算
// 希望弥补一下，这方面的知识欠缺
public class WeiYiTest {

    public static void main(String[] args) {
        System.out.println("---->:\t" + (5 >> 2));
        System.out.println("---->:\t" + (-5 >> 3));
        System.out.println("---->:\t" + (-5 >> 2));
    }

}
