package code.interfaces;

import java.util.concurrent.Future;

public interface CapteurAsync {

    public Future<Integer> getValue();

    public boolean getCapteurStatus();

}
