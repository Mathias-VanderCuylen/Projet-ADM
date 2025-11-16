//    // Un = Xn / m
//    // Yn = (un * 10) (int)
//
//    // Etape 1 => Hypothèse 0 : la suite de nombres pseudo-aléatoire est acceptable pour ce test
//
//    // Etape 2 => niveau d'incertitude : a = 5%
//
//    // Etape 3 => tableau recensé des fréquences o

import java.util.ArrayList;

public class FrequencyTest {
    public static void run(ArrayList<Integer> sequence, int m) {
        System.out.println("\n===== TEST DES FREQUENCES =====");

        System.out.println("\nEtape 1 : Hypothèse H0 :");
        System.out.println("\nH0 = la suite de nombres pseudo-aléatoire est acceptable pour ce test");

        System.out.println("\nEtape 2 : Niveau d'incertitude :");
        System.out.println("\nα = 5%");

        System.out.println("\nEtape 3 : Tableau de fréquences observée / théorique :\n");
        int interval = 10;
        int period = sequence.size();
        double[] freqObs = new double[interval];
        double[] freqTh = new double[interval];

        for (int xn : sequence) {
            double un = (double) xn / m;
            int yn = (int) (un * interval);
            if (yn == interval) yn--;
            freqObs[yn]++;
        }

        System.out.println("Xi \t ri \t pi \t n*pi \t (ri-n*pi)²/(n*pi)");

        int npi = period / interval;
        double chi2Total = 0;

        for (int i = 0; i < interval; i++) {
            double chi2 = ((freqObs[i] - npi) * (freqObs[i] - npi)) / (double)npi;
            chi2Total += chi2;
            System.out.println(i + " \t " + freqObs[i] + " \t " + (1.0 / interval) + " \t " + npi + " \t " + chi2);
        }
        System.out.println("χ² total observé = " + chi2Total);




    }
}