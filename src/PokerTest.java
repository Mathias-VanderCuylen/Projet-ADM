import java.util.*;

public class PokerTest {

    public static void run(ArrayList<Integer> sequence, int m) {

        System.out.println("\n===== TEST DU POKER =====");

        // Étape 1 : Hypothèses
        System.out.println("\nEtape 1 : Hypothèses");
        System.out.println("H0 : Les chiffres des nombres générés sont indépendants et uniformes.");
        System.out.println("H1 : Les chiffres ne sont pas indépendants ou pas uniformes.");

        // Étape 2 : Niveau de signification
        double alpha = 0.05;
        System.out.println("\nEtape 2 : Niveau de signification");
        System.out.println("α = 5%");

        // Catégories
        String[] categories = {
                "Poker",
                "Carré",
                "Full",
                "Brelan",
                "Double paire",
                "Paire",
                "Tous différents"
        };

        // Probabilités théoriques (5 chiffres)
        double[] pi = {
                0.0001,
                0.0045,
                0.009,
                0.072,
                0.108,
                0.504,
                0.3024
        };

        int[] ri = new int[7];
        int N = sequence.size();

        // Étape 3 : Classification
        for (int xn : sequence) {
            int[] digits = extractDigits(xn, m);
            int cat = category(digits);
            ri[cat]++;
        }

        System.out.println("\nEtape 3 : Tableau avant regroupement");
        System.out.println("Catégorie \t ri \t pi \t n*pi");

        for (int i = 0; i < 7; i++) {
            System.out.printf("%-15s %d \t %.4f \t %.2f\n",
                    categories[i], ri[i], pi[i], N * pi[i]);
        }

        // Étape 4 : Regroupement
        int[] riReg = new int[3];
        double[] piReg = new double[3];

        // Groupes :
        // G1 : Poker + Carré + Full
        // G2 : Brelan + Double paire
        // G3 : Paire + Tous différents
        riReg[0] = ri[0] + ri[1] + ri[2];
        piReg[0] = pi[0] + pi[1] + pi[2];

        riReg[1] = ri[3] + ri[4];
        piReg[1] = pi[3] + pi[4];

        riReg[2] = ri[5] + ri[6];
        piReg[2] = pi[5] + pi[6];

        System.out.println("\nEtape 4 : Tableau après regroupement");
        System.out.println("Groupe \t ri \t n*pi \t (ri-n*pi)²/(n*pi)");

        double chi2 = 0;
        for (int i = 0; i < 3; i++) {
            double npi = N * piReg[i];
            double term = (riReg[i] - npi) * (riReg[i] - npi) / npi;
            chi2 += term;

            System.out.printf("G%d \t %d \t %.2f \t %.4f\n",
                    i + 1, riReg[i], npi, term);
        }

        // Étape 5 : Degrés de liberté
        int v = 3 - 1;
        double chi2Theo = 5.99; // α = 5%, v = 2

        System.out.println("\nEtape 5 : Degrés de liberté");
        System.out.println("v = " + v);
        System.out.println("χ² théorique = " + chi2Theo);

        // ===== Étape 6 : Décision automatique =====
        String decision = (chi2 <= chi2Theo) ? "H0 ACCEPTÉE" : "H0 REJETÉE";
        System.out.println("\nEtape 6 : Décision automatique");
        System.out.println("χ²observé = " + chi2 + " → " + decision);
    }

    // Extraction de 5 chiffres décimaux
    private static int[] extractDigits(int xn, int m) {
        double u = (double) xn / m;
        int[] digits = new int[5];

        for (int i = 0; i < 5; i++) {
            u *= 10;
            digits[i] = (int) u;
            u -= digits[i];
        }
        return digits;
    }

    // Détermination de la catégorie
    private static int category(int[] digits) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int d : digits) {
            freq.put(d, freq.getOrDefault(d, 0) + 1);
        }

        Collection<Integer> values = freq.values();

        if (values.contains(5)) return 0; // Poker
        if (values.contains(4)) return 1; // Carré
        if (values.contains(3) && values.contains(2)) return 2; // Full
        if (values.contains(3)) return 3; // Brelan

        int pairs = 0;
        for (int v : values) if (v == 2) pairs++;

        if (pairs == 2) return 4; // Double paire
        if (pairs == 1) return 5; // Paire

        return 6; // Tous différents
    }
}
