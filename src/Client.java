public class Client {
    public enum Priorite {
        ORDINAIRE,
        PRIORITAIRE,
        ABSOLU
    }

    private Priorite type;
    private int duree;

    public Client(Priorite type) {
        this.type = type;
    }

    public Priorite getType() {
        return type;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void decrementeDuree() {
        this.duree--;
    }

    @Override
    public String toString() {
        return "Client " + type + " avec durée " + duree;
    }
}
