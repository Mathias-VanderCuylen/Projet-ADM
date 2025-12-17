import java.util.ArrayList;
import static java.lang.Math.floor;

public class FrequencyTest {
    public static void run(ArrayList<Integer> sequence) {
        System.out.println("\n===== TEST DES FREQUENCES =====");

        System.out.println("\nEtape 1 : Hypothèse H0 :");
        System.out.println("H0 : Les yn suivent une distribution uniforme sur l'ensemble {0,1,2,...,9}. \nChaque chiffre de 0 à 9 a la même probabiité.");
        System.out.println("H1 : Les yn ne suivent pas une distribution uniforme sur {0,1,2,...,9}.");

        System.out.println("\nEtape 2 : Niveau d'incertitude :");
        System.out.println("α = 5%");

        System.out.println("\nEtape 3 : Tableau de fréquences observée / théorique :");
        int xi = 10;
        int period = sequence.size();
        double[] frequence = new double[xi];
        double un;
        int yn;

        for (int xn : sequence) {
            un = (double) xn / period;
            yn = (int) floor((un * xi));
            frequence[yn]++;
        }

        System.out.println("Xi \t ri \t pi \t n*pi \t (ri-n*pi)²/(n*pi)");

        int npi = period / xi;
        double chi2Total = 0;
        double riTotale = 0;
        double ri;
        double chi2;

        for (int i = 0; i < xi; i++) {
            ri = frequence[i];
            chi2 = ((ri - npi) * (ri - npi)) / (double)npi;
            chi2Total += chi2;
            riTotale += ri;
            System.out.println(i + " \t " + ri + " \t " + (1.0 / xi) + " \t " + npi + " \t " + chi2);
        }
        System.out.println("Totale ri = " + riTotale);
        System.out.println("χ² total observé = " + chi2Total);

        System.out.println("\nEtape 4 : Si npi >= 5 alors n >= 50.");
        System.out.println("npi = " + npi + " et n = " + period);

        System.out.println("\nEtape 5 : Le nombre de dégré le liberté.");
        System.out.println("v = " + (xi - 1));
        System.out.println("Consulter les tables");
        System.out.println("Pour un α de 0,05 et un v de 9 on a un χ²théorique de 16,92");
        System.out.println("Zone de non-rejet : ]-∞ ; 16,92]");

        // ===== Étape 6 : Décision automatique =====
        String decision = (chi2Total <= 16.92) ? "H0 ACCEPTÉE" : "H0 REJETÉE";
        System.out.println("\nEtape 6 : Décision automatique");
        System.out.println("χ²observé = " + chi2Total + " → " + decision);
    }
}
