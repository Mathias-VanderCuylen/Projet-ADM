public class Client {
    public enum Priorite {
        ORDINAIRE,
        PRIORITAIRE,
        ABSOLU
    }

    private Priorite type;
    private double duree;

    public Client(Priorite type) {
        this.type = type;
    }

    public Priorite getType() {
        return type;
    }

    public void setType(Priorite type) {
        this.type = type;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
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
