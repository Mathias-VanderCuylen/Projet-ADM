public class Main {
    public static void main(String[] args) {
        Sequence sequence = new Sequence();
        if (!sequence.isValid()) {
            System.out.println("Le théoreme de Hull-Dobell n'est pas vérifié.");
            System.out.println(sequence.getSuite());
        } else {
            System.out.println(sequence.getSuite());
            FrequencyTest.run(sequence.getSuite());
            PokerTest.run(sequence.getSuite(), sequence.getM());
        }

        Da da = new Da();
        System.out.println("Nombre de stations optimal : " + da.nbStationsOptimal(20));
    }
}
