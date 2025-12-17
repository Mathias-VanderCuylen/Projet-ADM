public class Main {
    public static void main(String[] args) throws InterruptedException {
        Sequence sequence = new Sequence();
        if (sequence.getSequence().isEmpty()) {
            System.out.println("Le théoreme de Hull-Dobell n'est pas vérifié.");
        } else {
            System.out.println(sequence);
            FrequencyTest.run(sequence.getSequence());
            PokerTest.run(sequence.getSequence(), 120);
        }
    }
}