import java.util.ArrayList;

public class PokerTest {

    public static void run(ArrayList<Integer> sequence, int m) {
        int n = sequence.size();

        if (n < 5) {
            System.out.println("Pas assez de valeurs pour le test du poker.");
            return;
        }

        // Probabilités théoriques (7 catégories)
        double[] p = {0.3024, 0.5040, 0.1080, 0.0720, 0.0090, 0.0045, 0.0001};
        int[] observed = new int[p.length];
        String[] names = {
                "Toutes différentes", "Une paire", "Deux paires", "Brelan",
                "Full House", "Carré", "Quintuple"
        };

        // Analyse de chaque nombre
        for (int x : sequence) {
            double u = (double)x / m;
            int number = (int)(u * 100000);
            int[] digits = new int[5];

            for (int i = 4; i >= 0; i--) {
                digits[i] = number % 10;
                number /= 10;
            }

            int[] cnt = new int[10];
            for (int d : digits) cnt[d]++;

            int max = 0, pairs = 0;
            for (int c : cnt) {
                if (c > max) max = c;
                if (c == 2) pairs++;
            }

            if (max == 5) observed[6]++;
            else if (max == 4) observed[5]++;
            else if (max == 3 && pairs == 1) observed[4]++;
            else if (max == 3) observed[3]++;
            else if (pairs == 2) observed[2]++;
            else if (pairs == 1) observed[1]++;
            else observed[0]++;
        }

        // Affichage
        System.out.println("\n===== TEST DU POKER =====");
        for (int i = 0; i < observed.length; i++) {
            System.out.println(names[i] + " : " + observed[i]);
        }

        // Chi²
        double chi2 = 0;
        for (int i = 0; i < p.length; i++) {
            double expected = p[i] * n;
            chi2 += Math.pow(observed[i] - expected, 2) / expected;
        }

        System.out.println("\nChi² calculé = " + chi2);
        System.out.println("Valeur critique (ddl = 6, α=0.05) = 12.59");

        if (chi2 < 12.59)
            System.out.println("→ ACCEPTÉ");
        else
            System.out.println("→ REJETÉ");
    }
}
