package code.interfaces;

import java.util.concurrent.Future;

public interface ObserveurDeCapteurAsync {

    public Future<Void> update();

}
