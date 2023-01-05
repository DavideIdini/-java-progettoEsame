import java.util.*;

import static java.lang.System.exit;

public class Menu {

    private final static String INTRO = "Benvenuto nell'app per la gestione degli appuntamenti del centro medico \n" +
            "che vuoi fare? \n"+
            "1) Nuovo appuntamento \n"+
            "2) Modifica appuntamento\n"+
            "3) Elimina appuntamento\n"+
            "4) Ricerca appuntamenti\n"+
            "5) Calcola statistiche\n"+
            "6) Esci";
    public static void main(String[] args) {
        //dichiarazione variabili
        Map<Integer, List<Appuntamento>> appuntamenti_per_medico = new HashMap<>();
        Agenda agenda = new Agenda(appuntamenti_per_medico);

        menu(agenda);




    }

    private static void menu(Agenda agenda) {

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

                        Appuntamento.creaAppuntamenti(tastiera,agenda);
                        //System.out.println(appuntamenti.get(0).toString());
                        a++;
                        break;

                    }
                }
                case 2: { agenda.modificaAppuntamento(agenda); break;}
                case 3 : { agenda.eliminaAppuntamento(agenda); break;}
                case 4 : { agenda.ricercaAppuntamenti(agenda); break;}
                case 6: {exit(1);}

            }

        }
    }



}