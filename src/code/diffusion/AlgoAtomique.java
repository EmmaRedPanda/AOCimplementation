package code.diffusion;

import code.implementations.CapteurImpl;

import java.util.ArrayList;
import java.util.concurrent.Future;

public class AlgoAtomique implements AlgoDiffusion{
    public ArrayList<Future<Integer>> futures = new ArrayList<>();

    @Override
    public void execute(CapteurImpl cap) {
        boolean done = true;
        cap.miseEnAttente();
        for(int i=0;i<cap.obsLinked.size();i++){
            Future f = cap.obsLinked.get(i).update();
            futures.add(f);
            if(!futures.get(i).isDone()){
                done = false;
            }
        }
        if(done){
            futures = new ArrayList<>();
            cap.stopAttente();
        }

    }

    @Override
    public void verifie(CapteurImpl cap) {
        boolean done = true;
        for(int i=0;i<cap.obsLinked.size();i++){
            if(!futures.get(i).isDone()){
                done = false;
            }
        }
        if(done){
            futures = new ArrayList<>();
            cap.stopAttente();
        }

    }
}
