public interface Capteur {

    public void attach(ObserveurDeCapteurAsync obs);

    public int getValue();

    public void tick();

}
