import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author : [wswen]
 * @description : [一句话描述该类的功能]
 */
public class ThreadTest {
    public static void main(String[] args) {
        //目标：写一个抢红包游戏来模拟多线程的应用
        //1.某企业有100名员工，员工编号为1-100
        //2.现在公司举办年会活动，准备200个红包，小红包金额在【1-30】元之间，大红包金额在【31-100】元之间
        //3.系统模拟上述要求产生200个红包
        //4.系统模拟100个员工抢红包，需要输出哪个员工抢到哪个红包的过程，活动结束时提示活动结束
        //5.活动结束后需要对员工抢到的红包总金额进行降序展示，例如：3号员工抢到金额总和为：200元，2号员工抢到金额总和为：150元，...
//        分析: 100个员工就是100个线程,来抢200个红包,红包是共享资源,需要加锁
//              小红包占比80%,大红包占比20%

        List<Integer> redPacket = getRedPacket();
//         定义线程类，创建100个员工线程抢红包
        for (int i = 1; i <= 100; i++) {
            new PeopleGetRedPaccket(redPacket, "员工" + i).start();
        }
    }

    //写一个红包类，先准备两百个随机的红包，放在List中返回
    public static List<Integer> getRedPacket() {
//        准备200个红包，小红包金额在【1-30】元之间，大红包金额在【31-100】元之间
//        小红包占比80%,大红包占比20%
        List<Integer> redPacket = new ArrayList<>();
        for (int i = 0; i < 160; i++) {
            redPacket.add((int) (Math.random() * 30 + 1));
        }   //小红包
        for (int i = 0; i < 40; i++) {
            redPacket.add((int) (Math.random() * 70 + 31));
        }   //大红包
        return redPacket;
    }

    //写一个员工类，实现Runnable接口，模拟员工抢红包的过程

}