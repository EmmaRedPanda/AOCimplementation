import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Afficheur implements ObserveurDeCapteur{

    public Integer aff = 0;

    public ArrayList<Integer> anciensAff = new ArrayList<>();

    @Override
    public void update(CapteurAsync cap) throws ExecutionException, InterruptedException {

        aff = cap.getValue().get();
        anciensAff.add(aff);

    }

}
