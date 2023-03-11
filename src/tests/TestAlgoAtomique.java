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

        int mySize = ((int)(Math.random()*5)+1);
        ArrayList<Integer> res = new ArrayList<>();
        for(Integer c=1; c<=mySize; c++){
            res.add(c);
        }
        System.out.println("taille attendue : "+mySize);
        System.out.println("reference : "+res);

        final ScheduledFuture<?> runTick = exec.scheduleAtFixedRate(cap1::tick, 0, 500, TimeUnit.MILLISECONDS);
        exec.schedule(
                () -> {runTick.cancel(true);},
                20000 ,
                TimeUnit.MILLISECONDS);

        final ScheduledFuture<?> runTick2 = exec.scheduleAtFixedRate(
                () -> {
                    if ((cap1.valeur) == mySize) {
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

        int mySizeBis = ((int)(Math.random()*5)+1);
        ArrayList<Integer> res = new ArrayList<>();
        for(Integer d=1; d<=(mySizeBis+1); d++){
            res.add(d);
        }
        System.out.println("taille attendue : "+mySizeBis);
        System.out.println("reference : "+res);

        final ScheduledFuture<?> runTick = exec.scheduleAtFixedRate(cap1::tick, 0, 500, TimeUnit.MILLISECONDS);
        exec.schedule(
                () -> {runTick.cancel(true);},
                20000 ,
                TimeUnit.MILLISECONDS);

        final ScheduledFuture<?> runTick2 = exec.scheduleAtFixedRate(
                () -> {
                    if ((cap1.valeur) == mySizeBis) {
                        runTick.cancel(true);
                    }
                }, 0, 500, TimeUnit.MILLISECONDS);

        Thread.sleep(20000);

        assertNotEquals(aff1.anciensAff, res);
        assertNotEquals(aff2.anciensAff, res);
        assertNotEquals(aff3.anciensAff, res);


    }

}