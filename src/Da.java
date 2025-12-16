import java.util.ArrayList;
import java.util.Scanner;

public class Da {
    private static Scanner clavier;

    static {
        clavier = new Scanner(System.in);
    }

    public int nbStationsOptimal() {
        int nbStationsMax = clavier.nextInt();
        ArrayList<Double> prixParStation = prixTotalParStation(nbStationsMax);
        
        int nbStationsOptimal = 0;

        for (int i = 0; i < nbStationsMax; i++) {
            if (prixParStation.get(i) < prixParStation.get(nbStationsOptimal)) {
                nbStationsOptimal = i;
            }
        }

        return nbStationsOptimal + 1;
    }

    private ArrayList<Double> prixTotalParStation(int nbStationsMax) {
        ArrayList<Double> totalParStation = new ArrayList<>();

        ArrayList<Client> station;
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

        for (int nbStations = 1; nbStations < nbStationsMax; nbStations++) {
            station = new ArrayList<>();
            coutTotal = 0;

            fileOrdinaire = new ArrayList<>();
            filePrioritaire = new ArrayList<>();

            temps = 0;

            do {
                nbNvClientOrdinaire = loiPoisson(1.5);
                nbNvClientPrioritaire = loiPoisson(0.7);

                System.out.println(station + "\n" + fileOrdinaire + "\n" + filePrioritaire + "\n" + nbNvClientOrdinaire + "\n" + nbNvClientPrioritaire);

                while (nbNvClientOrdinaire > 0) {
                    nvClient = new Client(Client.Priorite.ORDINAIRE);
                    nvClient.setDuree(probabiliteDuree());
                    fileOrdinaire.add(nvClient);

                    System.out.println(nvClient);
                    nbNvClientOrdinaire--;
                }

                while (nbNvClientPrioritaire > 0) {
                    nvClient = new Client(probabiliteDuree() > 0.3 ? Client.Priorite.PRIORITAIRE : Client.Priorite.ABSOLU);
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
                    if (station.get(iStation) != null) {
                        cout = (station.get(iStation).getType() == Client.Priorite.ORDINAIRE ? 0.47 : 0.55);
                        coutTotal += cout;
                        
                        station.get(iStation).decrementeDuree();

                        if (station.get(iStation).getDuree() == 0) {
                            station.set(iStation, null);
                        }
                    }
                }

                for (int iStation = 0; iStation < nbStations; iStation++) {
                    if (station.get(iStation) != null) {
                        if (!filePrioritaire.isEmpty()) {
                            station.set(iStation, filePrioritaire.get(0));

                            tailleFile = filePrioritaire.size() - 1;
                            for (int iFile = 0; iFile < tailleFile; iFile++) {
                                filePrioritaire.set(iFile, filePrioritaire.get(iFile + 1));
                            }

                            filePrioritaire.remove(tailleFile);

                        } else {
                            if (!fileOrdinaire.isEmpty()) {
                                station.set(iStation, fileOrdinaire.get(0));

                                tailleFile = fileOrdinaire.size() - 1;
                                for (int iFile = 0; iFile < tailleFile; iFile++) {
                                    fileOrdinaire.set(iFile, fileOrdinaire.get(iFile + 1));
                                }

                                fileOrdinaire.remove(tailleFile);
                            } else {
                                coutTotal += 0.3;
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
        return 0; // temporaire
    }

    private double probabiliteDuree() {
        return 0.4; // temporaire
    }

    private double probabilitePrioritaire() {
        return 0.5; // temporaire: 30% prioritaire, 70% absolu
    }
}
