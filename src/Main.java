import java.io.Console;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Sequence sequence = new Sequence();
        System.out.println(sequence);
        FrequencyTest.run(sequence.getSequence(), 120);
    }
}