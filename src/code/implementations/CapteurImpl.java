package code.implementations;

import code.diffusion.AlgoAtomique;
import code.diffusion.AlgoDiffusion;
import code.interfaces.Capteur;
import code.interfaces.ObserveurDeCapteurAsync;

import java.util.ArrayList;

public class CapteurImpl implements Capteur {

    public int valeur = 0;

    public boolean attente = false;

    public AlgoDiffusion algo1 = new AlgoAtomique();

    public ArrayList<ObserveurDeCapteurAsync> obsLinked = new ArrayList<>();


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

        if (!attente) {
            valeur++;
            System.out.println("valeur : "+valeur);
            algo1.execute(this);
        }
        algo1.verifie(this);
        System.out.println("tic");
    }

    public void miseEnAttente(){
        attente = true;
    }

    public void stopAttente(){
        attente = false;
    }

}
