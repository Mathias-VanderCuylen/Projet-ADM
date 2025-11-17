import java.util.ArrayList;
import java.util.Scanner;

public class Sequence {
    private int x0, a, c, m;
    private ArrayList<Integer> sequence;

    public Sequence() {
        getParams();
        if (areCoprime(c, m) && isDivisibleByAll(a-1, primeFactors(m)) && isMultipleOf4(m, a)) {
            this.sequence = new ArrayList<>();
            generateSuite();
            System.out.println("Le théorème de Hull-Dobell est vérifié. Et la période de la suite est : " + sequence.size());
        } else {
            System.out.println("Le théoreme de Hull-Dobell n'est pas vérifié.");
        }
    }

    public ArrayList<Integer> getSequence() {
        return sequence;
    }

    private void getParams() {
//        Scanner Keyboard = new Scanner(System.in);
//        System.out.println("Entrez la valeur initiale (X0)");
//        this.x0 = Keyboard.nextInt();
//        System.out.println("Entrez le multiplicateur (a)");
//        this.a = Keyboard.nextInt();
//        System.out.println("Entrez l'incrément (c)");
//        this.c = Keyboard.nextInt();
//        System.out.println("Entrez le module (m)");
//        this.m = Keyboard.nextInt();
        this.x0 = 1;
        this.a = 21;
        this.c = 3;
        this.m = 1000;
    }

    private void generateSuite() {
        int xn = this.x0;
        do {
            xn = (a * xn + c) % m;
            this.sequence.add(xn);
        } while (xn != x0);
    }

    private boolean areCoprime(int nb1, int nb2){
        while (nb1 != nb2){
            if (nb1 > nb2){
                nb1 -= nb2;
            }
            else{
                nb2 -= nb1;
            }
        }
        return nb1 == 1;
    }

    private ArrayList<Integer> primeFactors(int number) {
        ArrayList<Integer> factors = new ArrayList<>();

        if (number % 2 == 0){
            factors.add(2);
        }

        while (number % 2 == 0) {
            number /= 2;
        }

        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0)
                factors.add(i);

            while (number % i == 0)
                number /= i;
        }

        if (number > 2) factors.add(number);

        return factors;
    }

    private boolean isDivisibleByAll(int number, ArrayList<Integer> factors) {
        for (int f : factors) {
            if (number % f != 0) return false;
        }
        return true;
    }

    private boolean isMultipleOf4 (int module, int multiplicator) {
       return module % 4 != 0 || (multiplicator - 1) % 4 == 0;
    }

    public String toString() {
        return sequence.toString();
    }
}
