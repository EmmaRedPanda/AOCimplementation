package tests;

import code.implementations.Afficheur;
import code.implementations.Canal;
import code.implementations.CapteurImpl;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class TestAlgoAtomique {

    @org.junit.jupiter.api.Test
    void monTest() throws InterruptedException {

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(15);

        Afficheur aff1 = new Afficheur("aff1");
        Afficheur aff2 = new Afficheur("aff2");
        Afficheur aff3 = new Afficheur("aff3");

        CapteurImpl cap1 = new CapteurImpl();

        Canal can1 = new Canal(exec, aff1, cap1);
        Canal can2 = new Canal(exec, aff2, cap1);
        Canal can3 = new Canal(exec, aff3, cap1);

        cap1.attach(can1);
        cap1.attach(can2);
        cap1.attach(can3);

        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(2);
        res.add(3);
        res.add(4);
        res.add(5);
        res.add(6);

        final ScheduledFuture<?> runTick = exec.scheduleAtFixedRate(cap1::tick, 0, 500, TimeUnit.MILLISECONDS);
        exec.schedule(
                () -> {runTick.cancel(true);},
                20000 ,
                TimeUnit.MILLISECONDS);

        final ScheduledFuture<?> runTick2 = exec.scheduleAtFixedRate(
                () -> {
                    if ((cap1.valeur) == (int) 6) {
                        runTick.cancel(true);
                    }
                }, 0, 500, TimeUnit.MILLISECONDS);



        Thread.sleep(20000);


        assertEquals(aff1.anciensAff, res);
        assertEquals(aff2.anciensAff, res);
        assertEquals(aff3.anciensAff, res);


    }

    @org.junit.jupiter.api.Test
    void monTestFaux() throws InterruptedException {

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(15);

        Afficheur aff1 = new Afficheur("aff1");
        Afficheur aff2 = new Afficheur("aff2");
        Afficheur aff3 = new Afficheur("aff3");

        CapteurImpl cap1 = new CapteurImpl();

        Canal can1 = new Canal(exec, aff1, cap1);
        Canal can2 = new Canal(exec, aff2, cap1);
        Canal can3 = new Canal(exec, aff3, cap1);

        cap1.attach(can1);
        cap1.attach(can2);
        cap1.attach(can3);

        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(2);
        res.add(3);
        res.add(4);
        res.add(5);
        res.add(6);
        res.add(7);

        final ScheduledFuture<?> runTick = exec.scheduleAtFixedRate(cap1::tick, 0, 500, TimeUnit.MILLISECONDS);
        exec.schedule(
                () -> {runTick.cancel(true);},
                20000 ,
                TimeUnit.MILLISECONDS);

        final ScheduledFuture<?> runTick2 = exec.scheduleAtFixedRate(
                () -> {
                    if ((cap1.valeur) == (int) 6) {
                        runTick.cancel(true);
                    }
                }, 0, 500, TimeUnit.MILLISECONDS);

        Thread.sleep(20000);

        assertNotEquals(aff1.anciensAff, res);
        assertNotEquals(aff2.anciensAff, res);
        assertNotEquals(aff3.anciensAff, res);


    }

}