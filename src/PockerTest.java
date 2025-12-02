import java.util.ArrayList;
import java.util.Arrays.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PockerTest {
    public static void run(ArrayList<Integer> sequence) {
        double[] frequence = new double[7];

        double [] pi = {0.0001, 0.0045, 0.009, 0.072, 0.108, 0.504, 0.3024};

        for (int i = 0; i <= sequence.size() - 5; i += 5) {
            ArrayList<Integer> subSequence = new ArrayList<>(sequence.subList(i, i + 5));

            Categories category = handCategory(subSequence);
            frequence[category.getValue()]++;
        }

        System.out.println("\n===== TEST POCKER =====");

        for (int i = 0; i < 7; i++) {
            System.out.println(Categories.values()[i] + " \t " + frequence[i] + " \t " +  pi[i]);
        }
    }

    enum Categories {
        POKER(0, "Poker"),
        CARRE(1, "Carré"),
        FULL(2, "Full"),
        BRELAN(3,"Brelan"),
        DOUBLE_PAIRE(4, "2paire"),
        PAIRE(5, "Paire"),
        RIEN(6, "Rien");

        private int value;
        private String name;
        Categories(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static Categories handCategory(ArrayList<Integer> subSequence){
        Collections.sort(subSequence);

        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : subSequence) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        int pairs = 0;
        boolean three = false;
        boolean four = false;
        boolean five = false;

        for (int value : freq.values()) {
            if (value == 5) five = true;
            if (value == 4) four = true;
            if (value == 3) three = true;
            if (value == 2) pairs++;
        }

        if (five) return Categories.POKER;
        if (four) return Categories.CARRE;
        if (three && pairs == 1) return Categories.FULL;
        if (three) return Categories.BRELAN;
        if (pairs == 2) return Categories.DOUBLE_PAIRE;
        if (pairs == 1) return Categories.PAIRE;
        return Categories.RIEN;
    }
}
