import java.util.ArrayList;
import java.util.Scanner;

public class Sequence {
    private int x0, a, c, m;
    private ArrayList<Integer> sequence;
    public Sequence() {
        getParams();
        if (areCoprime(c, m) && isMultipleOf(a-1, primeFactors(m)) && isMultipleOf4(m, a)) {
            this.sequence = new ArrayList<>();
            generateSuite();
        } else {
            System.out.println("Le théoreme de Hull-Dobell n'est pas vérifié");
        }
    }

    private void getParams() {
        Scanner Keyboard = new Scanner(System.in);
        System.out.println("Entrez la valeur initiale (X0)");
        this.x0 = Keyboard.nextInt();
        System.out.println("Entrez le multiplicateur (a)");
        this.a = Keyboard.nextInt();
        System.out.println("Entrez l'incrément (c)");
        this.c = Keyboard.nextInt();
        System.out.println("Entrez le module (m)");
        this.m = Keyboard.nextInt();
    }

    private void generateSuite() {
        int xn = this.x0;
        do {
            xn = (a*xn + c)%m;
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
        while (number % 2 == 0) {
            if (!factors.contains(2))
                factors.add(2);
            number /= 2;
        }

        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            while (number % i == 0) {
                if (!factors.contains(i))
                    factors.add(i);
                number /= i;
            }
        }

        return factors;
    }

    private boolean isMultipleOf(int number, ArrayList<Integer> factors) {
        int i = 0;
        while (i < factors.size() && number % factors.get(i) == 0) {
            i++;
        }
        return i == factors.size();
    }

    private boolean isMultipleOf4 (int module, int multiplicator) {
       return module % 4 != 0 || (multiplicator - 1) % 4 == 0;
    }

    public String toString() {
        return sequence.toString();
    }
}
