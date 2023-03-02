import java.lang.reflect.Type;
import java.util.ArrayList;

public class CapteurImpl implements Capteur{

    public Type t;
    public int valeur = 0;

    ArrayList<ObserveurDeCapteurAsync> obsLinked = new ArrayList<>();


    @Override
    public void attach(ObserveurDeCapteurAsync obs){
        obsLinked.add(obs);
    }

    @Override
    public int getValue(){
        return valeur;
    }

    @Override
    public void tick() {
        valeur++;
        for(int i=0;i<obsLinked.size();i++){
            obsLinked.get(i).update();
        }
        System.out.println("tic");
    }

}
