package me.code41.seed.common.util;

/**
 * Created with IntelliJ IDEA.
 * User: sunyan5
 * Date: 16-3-12
 * Time: 下午12:18
 * To change this template use File | Settings | File Templates.
 */
import java.util.Random;

public class RandomUtils {

    public static void main(String[] args) {
        for (int i = 0; i < 5000; i++) {
            System.out.println(String.valueOf(getHighPosi())+String.valueOf(getLowPosi()));
        }

    }


    /**
     * 高位+低位      一共13位
     * 此方法高位
     * @return
     */
    public static int getHighPosi() {
        int n = 999999;
        Random random = new Random();
        boolean[] bs = new boolean[n];
        int count=0;
        while (true) {
            int i = random.nextInt(n);
            //System.out.println("i=" + i);
            if (!bs[i]) {
                bs[i] = true;
                count=i;
                break;
            }
        }
        if(count==0){
            count=getHighPosi();
        }
        return count;
    }



    /**
     * 高位+低位      一共13位
     * 此方法低位
     * @return
     */
    public static int getLowPosi() {
        int n = 99999;
        Random random = new Random();
        boolean[] bs = new boolean[n];
        int count=0;
        while (true) {
            int i = random.nextInt(n);
            //System.out.println("i=" + i);
            if (!bs[i]) {
                bs[i] = true;
                count=i;
                break;
            }
        }
        if(count==0){
            count=getLowPosi();
        }
        return count;
    }


}