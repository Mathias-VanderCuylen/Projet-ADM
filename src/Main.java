public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*Sequence sequence = new Sequence();
        if (!sequence.isValid()) {
            System.out.println("Le théoreme de Hull-Dobell n'est pas vérifié.");
            System.out.println(sequence);
        } else {
            System.out.println(sequence);
            FrequencyTest.run(sequence.getSequence());
            PokerTest.run(sequence.getSequence(), sequence.getM());
        }*/

        Da da = new Da();
        System.out.println("Nombre de stations optimal : " + da.nbStationsOptimal(5));
    }
}
