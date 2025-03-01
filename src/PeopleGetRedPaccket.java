import java.util.List;

/**
 * @author : [wswen]
 * @description : [一句话描述该类的功能]
 */
public class PeopleGetRedPaccket extends Thread{
    private List<Integer> redPacket;
    public PeopleGetRedPaccket(List<Integer> redPacket, String name) {
        super(name);
        this.redPacket = redPacket;
    }

    @Override
    public void run() {
//        获取线程名称
        String name = Thread.currentThread().getName();
//      100个员工抢redPacket中的红包（每名员工可以重复抢）
        while (true) {
            synchronized (redPacket) {
                if (redPacket.size() == 0) {
                    break;
                }

                int index = (int) (Math.random() * redPacket.size());
                System.out.println(name + "抢到了" + redPacket.get(index) + "元的红包");
                redPacket.remove(index);
                if (redPacket.size() == 0) {
                    System.out.println("红包已经被抢光，活动结束");
                    break;
                }

            }
        }
    }
}
