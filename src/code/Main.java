package code;

import code.implementations.Afficheur;
import code.implementations.Canal;
import code.implementations.CapteurImpl;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello user!");

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(15);

        Afficheur aff1 = new Afficheur();
        Afficheur aff2 = new Afficheur();
        Afficheur aff3 = new Afficheur();

        CapteurImpl cap1 = new CapteurImpl();

        Canal can1 = new Canal(exec, aff1, cap1);
        Canal can2 = new Canal(exec, aff2, cap1);
        Canal can3 = new Canal(exec, aff3, cap1);

        cap1.attach(can1);
        cap1.attach(can2);
        cap1.attach(can3);

        final ScheduledFuture<?> runTick = exec.scheduleAtFixedRate(cap1::tick, 0, 500, TimeUnit.MILLISECONDS);
        exec.schedule(
                () -> {
                    runTick.cancel(true);
                    System.out.println("aff 1 : " + aff1.anciensAff + "\naff 2 : " + aff2.anciensAff +"\naff 3 : " + aff3.anciensAff);},
                20000 ,
                TimeUnit.MILLISECONDS);


    }


}