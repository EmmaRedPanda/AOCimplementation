package code.implementations;

import code.interfaces.CapteurAsync;
import code.interfaces.ObserveurDeCapteur;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Afficheur implements ObserveurDeCapteur {

    public String nom = "";

    public Integer aff = 0;

    public ArrayList<Integer> anciensAff = new ArrayList<>();

    public Afficheur(String nom){
        this.nom = nom;
    }

    @Override
    public void update(CapteurAsync cap) throws ExecutionException, InterruptedException {

        aff = cap.getValue().get();
        anciensAff.add(aff);
        System.out.println(this.nom + " : " + anciensAff);

    }

}
