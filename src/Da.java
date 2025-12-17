import java.util.ArrayList;
// import java.util.Scanner;

public class Da {
    // private static Scanner clavier;
    private static final double COUT_ATTENTE;
    private static final Sequence sequence;
    private static int iSequence;

    static {
        // clavier = new Scanner(System.in);
        COUT_ATTENTE = 0.3;
        sequence = new Sequence(1, 21, 3, 120);
        iSequence = 0;
    }

    public int nbStationsOptimal(int nbStationsMax) {
        ArrayList<Double> prixPourStations = prixTotalPourNbStations(nbStationsMax);
        
        int nbStationsOptimal = 0;
        int taille = prixPourStations.size();

        for (int i = 1; i < taille; i++) {
            if (prixPourStations.get(i) < prixPourStations.get(nbStationsOptimal)) {
                nbStationsOptimal = i;
            }
        }

        return nbStationsOptimal + 1;
    }

    private ArrayList<Double> prixTotalPourNbStations(int nbStationsMax) {
        ArrayList<Double> totalParStation = new ArrayList<>();

        Client[] station;
        double coutTotal;
        ArrayList<Client> fileOrdinaire;
        ArrayList<Client> filePrioritaire;
        int temps;
        int nbNvClientOrdinaire;
        int nbNvClientPrioritaire;
        Client nvClient;
        double cout;
        int tailleFile;
        int nbPrioritaireAbsolu;

        for (int nbStations = 1; nbStations <= nbStationsMax; nbStations++) {
            station = new Client[nbStations];
            coutTotal = 0;

            fileOrdinaire = new ArrayList<>();
            filePrioritaire = new ArrayList<>();

            temps = 0;

            do {
                nbNvClientOrdinaire = loiPoisson(1.5);
                nbNvClientPrioritaire = loiPoisson(0.7);

                for (Client client : station) {
                    System.out.println(client);
                }
                System.out.println(fileOrdinaire + "\n" + filePrioritaire + "\n" + nbNvClientOrdinaire + "\n" + nbNvClientPrioritaire);

                while (nbNvClientOrdinaire > 0) {
                    nvClient = new Client(Client.Priorite.ORDINAIRE);
                    nvClient.setDuree(probabiliteDuree());
                    fileOrdinaire.add(nvClient);

                    System.out.println(nvClient);
                    nbNvClientOrdinaire--;
                }

                while (nbNvClientPrioritaire > 0) {
                    nvClient = new Client(probabilitePrioritaire());
                    nvClient.setDuree(probabiliteDuree());
                    filePrioritaire.add(nvClient);

                    System.out.println(nvClient);
                    nbNvClientPrioritaire--;
                }

                System.out.println("File ordinaire :");
                for (Client client : fileOrdinaire) {
                    System.out.println(client);
                }

                System.out.println("File prioritaire :");
                for (Client client : filePrioritaire) {
                    System.out.println(client);
                }

                for (int iStation = 0; iStation < nbStations; iStation++) {
                    if (station[iStation] == null) {
                        cout = (station[iStation].getType() == Client.Priorite.ORDINAIRE ? 0.47 : 0.55);
                        coutTotal += cout;

                        station[iStation].decrementeDuree();

                        if (station[iStation].getDuree() == 0) {
                            station[iStation] = null;
                        }
                    }
                }

                for (int iStation = 0; iStation < nbStations; iStation++) {
                    if (station[iStation] != null) {
                        if (!filePrioritaire.isEmpty()) {
                            station[iStation] = filePrioritaire.getFirst();
                            filePrioritaire.removeFirst();

                        } else {
                            if (!fileOrdinaire.isEmpty()) {
                                station[iStation] = fileOrdinaire.getFirst();
                                fileOrdinaire.removeFirst();
                            } else {
                                coutTotal += COUT_ATTENTE;
                            }
                        }
                    }
                }

                nbPrioritaireAbsolu = 0;
                for (Client client : filePrioritaire) {
                    if (client.getType() == Client.Priorite.ABSOLU) {
                        nbPrioritaireAbsolu++;
                    }
                }

                coutTotal += (
                    (nbPrioritaireAbsolu * 0.75) +
                    ((filePrioritaire.size() - nbPrioritaireAbsolu) * 0.583) +
                    (fileOrdinaire.size() * 0.25)
                );

                for (Client client : station) {
                    System.out.println(client);
                }

                for (Client client : fileOrdinaire) {
                    System.out.println(client);
                }

                for (Client client : filePrioritaire) {
                    System.out.println(client);
                }

                temps++;
            } while (temps < 20);

            totalParStation.add(coutTotal);
        }

        return totalParStation;
    }

    private int loiPoisson(double lambda) {
        double L = Math.exp(-lambda);
        int k = 0;
        double p = 1.0;

        do {
            k++;
            p *= sequence.getSequence().get(iSequence++);
        } while (p > L);

        return k - 1;
    }

    private int probabiliteDuree() {
        double number = sequence.getSequence().get(iSequence++);

        if (number < 0.40) {
            return 1;
        } else if (number < 0.70) {
            return 2;
        } else if (number < 0.87) {
            return 3;
        } else if (number < 0.92) {
            return 4;
        } else if (number < 0.97) {
            return 5;
        } else {
            return 6;
        }
    }

    private Client.Priorite probabilitePrioritaire() {
        return (sequence.getSequence().get(iSequence++) < 0.3 ? Client.Priorite.PRIORITAIRE : Client.Priorite.ABSOLU); // 30% prioritaire, 70% absolu
    }
}
