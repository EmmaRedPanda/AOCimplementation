import java.util.concurrent.ExecutionException;

public interface ObserveurDeCapteur {

    public void update(CapteurAsync cap) throws ExecutionException, InterruptedException;

}
