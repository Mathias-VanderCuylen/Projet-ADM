public class Main {
    public static void main(String[] args) throws InterruptedException {
        Sequence sequence = new Sequence();
        System.out.println(sequence);
        FrequencyTest.run(sequence.getSequence());
        PockerTest.run(sequence.getSequence());
    }
}