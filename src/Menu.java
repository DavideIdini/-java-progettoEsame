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
            "6) Aggiungi pazienti alla Wait list\n"+
            "7) Esci";
    public static void main(String[] args) {

        Map<Integer, List<Appuntamento>> appuntamenti_per_medico = new HashMap<>();
        Agenda agenda = new Agenda(appuntamenti_per_medico);
        List<WaitList>  waitList = new ArrayList<>();
        menu(agenda,waitList);
    }

    private static void menu(Agenda agenda,List<WaitList> waitList) {

        Scanner tastiera = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println(INTRO);
            int scelta = tastiera.nextInt();
            switch (scelta)
            {   case 7: {exit(1);}
                case 1: {Appuntamento.creaAppuntamenti(tastiera,agenda);break;}
                case 2: { agenda.modificaAppuntamento(agenda); break;}
                case 3 : { agenda.eliminaAppuntamento(agenda,waitList); break;}
                case 4 : { agenda.ricercaAppuntamenti(agenda); break;}
                case 6 : { waitList.add(WaitList.joinWaitList()); System.out.println(waitList.toString());}


            }

        }
    }




}