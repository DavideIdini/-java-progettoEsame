import java.io.*;
import java.util.*;

import static java.lang.System.exit;

public class Menu {
    private final static String PREINTRO = "Benvenuto nell'app per la gestione degli appuntamenti del centro medico \n" +
            "che vuoi fare? \n"+
            "1) Registrare nuovo medico\n"+
            "2) login medico\n"+
            "3) registrare un nuovo paziente\n"+
            "4) Salva e esci";
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
        List<Wait>  wait = new ArrayList<>();
        List<Medico> medico = new ArrayList<>();
        List<Paziente> paziente  = new ArrayList<>();


        try {
            FileInputStream fileInP = new FileInputStream("paziente");
            ObjectInputStream inP = new ObjectInputStream(fileInP);
            paziente = (List<Paziente>) inP.readObject();

            FileInputStream fileInM = new FileInputStream("medico");
            ObjectInputStream inM = new ObjectInputStream(fileInM);
            medico = (List<Medico>) inM.readObject();

            FileInputStream fileInA = new FileInputStream("agenda");
            ObjectInputStream inA = new ObjectInputStream(fileInA);
            agenda = (Agenda) inA.readObject();

            FileInputStream fileInW = new FileInputStream("wait");
            ObjectInputStream inW = new ObjectInputStream(fileInW);
            wait = (List<Wait>) inW.readObject();



          /*  inA.close();
            fileInA.close();
            inP.close();
            fileInP.close();
            inM.close();
            fileInM.close();
            inW.close();
            fileInW.close();*/

        }catch (IOException i){
            System.out.println("sono qua");
            i.getStackTrace();
        }catch (ClassNotFoundException c) {
            System.out.println("Classe non trovata.");
            c.printStackTrace();
            return;
        }


        Medico medicoOperante =null;
        medicoOperante=preIntro(medico,paziente,medicoOperante, agenda, wait);
        menu(agenda,wait, medicoOperante);
        salvaDati(medico, paziente, wait, agenda  );

    }




    private static Medico preIntro(List<Medico> medici, List<Paziente> paziente, Medico medicoOperante,Agenda agenda, List<Wait> wait) {
        Scanner tastiera = new Scanner(System.in);
        while(true){
            System.out.println(PREINTRO);
            try {
                String scelta = tastiera.nextLine();
                switch (Integer.parseInt(scelta)) {
                    case 4: {
                        salvaDati(medici, paziente, wait, agenda  );
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

    private static void salvaDati(List<Medico> medico, List<Paziente> paziente, List<Wait> wait, Agenda agenda) {
        try {
            FileOutputStream fileOutP = new FileOutputStream("paziente");
            ObjectOutputStream outP = new ObjectOutputStream(fileOutP);
            outP.writeObject(paziente);
            outP.flush();
            outP.close();
            FileOutputStream fileOutM = new FileOutputStream("medico");
            ObjectOutputStream outM = new ObjectOutputStream(fileOutM);
            outM.writeObject(medico);
            outM.flush();
            outM.close();
            FileOutputStream fileOutA = new FileOutputStream("agenda");
            ObjectOutputStream outA = new ObjectOutputStream(fileOutA);
            outA.writeObject(agenda);
            outA.flush();
            outA.close();
            FileOutputStream fileOutW = new FileOutputStream("wait");
            ObjectOutputStream outW = new ObjectOutputStream(fileOutW);
            outW.writeObject(wait);
            outW.flush();
            outW.close();
            System.out.printf("Serializzazione completata.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }




    private static void menu(Agenda agenda, List<Wait> waitList, Medico medicoOperante) {
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
                        waitList.add(Wait.joinWaitList());
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
