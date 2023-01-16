import java.util.*;

import static java.lang.System.exit;

public class Menu {
    private final static String PREINTRO = "Benvenuto nell'app per la gestione degli appuntamenti del centro medico \n" +
            "che vuoi fare? \n"+
            "1) Registrare nuovo medico\n"+
            "2) login medico\n"+
            "3) registrare un nuovo paziente\n"+
            "4) esci";
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
        List<Medico> medico = new ArrayList<>();
        List<Paziente> paziente  = new ArrayList<>();
        Medico medicoOperante =null;
        medicoOperante=preIntro(medico,paziente,medicoOperante);
        System.out.println(medicoOperante);
        menu(agenda,waitList, medicoOperante);
    }

    private static Medico preIntro(List<Medico> medici, List<Paziente> paziente, Medico medicoOperante) {
        Scanner tastiera = new Scanner(System.in);
        while(true){
            System.out.println(PREINTRO);
            try {
                String scelta = tastiera.nextLine();
                switch (Integer.parseInt(scelta)) {
                    case 4: {
                        exit(1);
                    }
                    case 1: {
                        medici.add(Medico.registrazioneMedico());
                        break;
                    }
                    case 3: {
                        paziente.add(Paziente.registrazionePaziente());
                        break;
                    }
                    case 2: {
                        medicoOperante = Medico.loginMedico(medici);
                        if(medicoOperante.equals(null))
                            throw new NullPointerException("Problemi con il login\n");
                        return medicoOperante;
                    }
                    default:{
                        throw new Exception("devi inserire un numero da 1 a 4");
                    }
                }
            }catch(NullPointerException e){
                System.out.println("Il login NON è avvenuto con successo");
            }catch (NumberFormatException e){
                System.out.println("devi inserire un numero compreso tra 1 e 4");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }



    private static void menu(Agenda agenda, List<WaitList> waitList, Medico medicoOperante) {
        Scanner tastiera = new Scanner(System.in);
        while(true) {
            try {
                System.out.println(INTRO);
                String scelta = tastiera.nextLine();
                switch (Integer.parseInt(scelta)) {
                    case 7: {
                        exit(1);
                    }
                    case 1: {
                        Appuntamento.creaAppuntamenti(tastiera, agenda, medicoOperante);
                        break;
                    }
                    case 2: {
                        agenda.modificaAppuntamento(agenda);
                        break;
                    }
                    case 3: {
                        agenda.eliminaAppuntamento(agenda, waitList);
                        break;
                    }
                    case 4: {
                        agenda.ricercaAppuntamenti(agenda);
                        break;
                    }
                    case 6: {
                        waitList.add(WaitList.joinWaitList());
                    }
                    default:{
                        throw new Exception("devi inserire un numero da 1 a 7");
                    }
                }

            }catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }
    }




}