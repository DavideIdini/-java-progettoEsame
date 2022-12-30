import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final static String INTRO = "Benvenuto nell'app per la gestione degli appuntamenti del centro medico \n" +
            "che vuoi fare? \n"+
            "1) Nuovo appuntamento \n"+
            "2) Modifica appuntamento\n"+
            "3) Elimina appuntamento\n"+
            "4) Ricerca appuntamenti\n"+
            "5) Calcola statistiche\n";
    public static void main(String[] args) {
        //dichiarazione variabili
        List<Appuntamento> appuntamenti = new ArrayList<>();
        List<Medico> medici = new ArrayList<>();
        List<Paziente> pazienti = new ArrayList<>();

        menu(appuntamenti);




    }

    private static void menu(List<Appuntamento> appuntamenti) {
        Scanner tastiera = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println(INTRO);
            int scelta = tastiera.nextInt();
            switch (scelta)
            {
                case 1:{
                    int a = 0;
                    while ( a == 0)
                    {

                        appuntamenti.add(creaAppuntamenti(tastiera));
                        System.out.println(appuntamenti.get(0).toString());
                        a++;
                        break;

                    }
                }
            }

        }
    }
    public static Appuntamento creaAppuntamenti(Scanner tastiera){
        String prova = tastiera.nextLine();
        System.out.println("Registra nuovo  appuntamento");
        System.out.println("inserisci data");
        String data = tastiera.nextLine();
        System.out.println("inserisci codice fiscale del paziente");
        String cf_paziente = tastiera.nextLine();
        System.out.println("Inserisci id dottore");
        String id_dottore = tastiera.nextLine();
        System.out.println("inserisci descrizione");
        String descrizione = tastiera.nextLine();
        System.out.println("inserisci ora inizio");
        int ora_inizio = tastiera.nextInt();
        System.out.println("inserisci ora fine");
        int ora_fine = tastiera.nextInt();
        System.out.println(ora_fine);

        Appuntamento a = new Appuntamento(data,cf_paziente,id_dottore,descrizione, ora_inizio, ora_fine);
        return a;
    }
}