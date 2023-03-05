package code.implementations;

import code.interfaces.CapteurAsync;
import code.interfaces.ObserveurDeCapteurAsync;

import java.util.concurrent.*;

public class Canal implements CapteurAsync, ObserveurDeCapteurAsync {

    public ScheduledExecutorService exec;
    public Afficheur aff;
    public CapteurImpl cap;

    public Canal(ScheduledExecutorService exec, Afficheur aff, CapteurImpl cap){
        this.exec = exec;
        this.aff = aff;
        this.cap = cap;
    }

    @Override
    public Future<Integer> getValue() {

        long monDelai = ThreadLocalRandom.current().nextLong(500,1500);
        return exec.schedule(() -> {return cap.getValue();}, monDelai, TimeUnit.MILLISECONDS);

    }

    @Override
    public boolean getCapteurStatus() {
        return this.cap.attente;
    }

    @Override
    public Future<Void> update() {

        long monDelai = ThreadLocalRandom.current().nextLong(500,1500);
        return (Future<Void>) exec.schedule(() -> {
            try {
                aff.update(this);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, monDelai, TimeUnit.MILLISECONDS);

    }

}
