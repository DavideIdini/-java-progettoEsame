import java.io.*;
import java.util.*;

import static java.lang.System.exit;

public class Menu {
    private final static String PREINTRO = "Benvenuto nell'app per la gestione degli appuntamenti del centro medico \n" +
            "che vuoi fare? \n"+
            "1) Registrare nuovo medico\n"+
            "2) login medico\n"+
            "3) registrare un nuovo paziente\n"+
            "4) Salva e esci \n"+
            "5) Elimina medico";
    private final static String INTRO = "Benvenuto nell'app per la gestione degli appuntamenti del centro medico \n" +
            "che vuoi fare? \n"+
            "1) Nuovo appuntamento \n"+
            "2) Modifica appuntamento\n"+
            "3) Elimina appuntamento\n"+
            "4) Ricerca appuntamenti\n"+
            "5) Calcola statistiche\n"+
            "6) Aggiungi pazienti alla Wait list\n"+
            "7) Salva e esci";
    public static void main(String[] args) {
        Map<String, List<Appuntamento>> appuntamenti_per_medico = new HashMap<>();
        Agenda agenda = new Agenda(appuntamenti_per_medico);
        List<Wait>  wait = new ArrayList<>();
        List<Medico> medico = new ArrayList<>();
        List<Paziente> paziente  = new ArrayList<>();
        Statistiche statistiche = new Statistiche(agenda, medico);


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



          inA.close();
            fileInA.close();
            inP.close();
            fileInP.close();
           inM.close();
            fileInM.close();
            inW.close();
            fileInW.close();

        }catch (IOException i){
            System.out.println("IOException "+i.getMessage());

        }catch (ClassNotFoundException c) {
            System.out.println("Classe non trovata.");
            c.printStackTrace();
            return;
        }

        Medico medicoOperante=preIntro(medico,paziente,null, agenda, wait);
        menu(agenda, wait, medicoOperante, medico, paziente, statistiche );
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
                        medici.add(Medico.registrazioneMedico(medici));
                        break;
                    }
                    case 3: {
                        paziente.add(Paziente.registrazionePaziente(paziente));
                        break;
                    }
                    case 2: {
                        medicoOperante = Medico.loginMedico(medici);
                        if(medicoOperante.equals(null))
                            throw new NullPointerException("Problemi con il login\n");
                        return medicoOperante;
                    }
                    case 5 :{
                        medici = eliminaMedico(medici);
                        break;
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

    private static List<Medico> eliminaMedico(List<Medico> medici) {
        System.out.println("Inserisci id medico da Licenziare");
        Scanner tastiera = new Scanner(System.in);
        String id = tastiera.nextLine();
        int c = 0;
        int index=0;
        boolean f = false;
        for(Medico a : medici){
            if(a.getId_medico().equals(id)){
                index = c;
                f= true;
            }
            c++;
        }
        if(f == true){
            System.out.println("Inserire la parola chiave per licenziare un medico");
            String k= tastiera.nextLine();
            if(k.equals("LICENZIATO!"))
            medici.remove(index);
            else{
                System.out.println("mi spiace non puoi licenziare, non sei a conoscenza della password segreta");
                return medici;
            }
        }

        else
            System.out.println("non ho trovato medici con quell'id");
        return medici;
    }

    public static void salvaDati(List<Medico> medico, List<Paziente> paziente, List<Wait> wait, Agenda agenda) {
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




    private static void menu(Agenda agenda, List<Wait> wait, Medico medicoOperante, List<Medico> medico, List<Paziente> paziente, Statistiche statistiche) {
        Scanner tastiera = new Scanner(System.in);
        while(true) {
            try {
                System.out.println(INTRO);
                String scelta = tastiera.nextLine();
                switch (Integer.parseInt(scelta)) {
                    case 7: {
                        salvaDati(medico, paziente,wait,agenda);
                        exit(1);
                    }
                    case 1: {
                        Appuntamento.creaAppuntamenti(tastiera, agenda, medicoOperante, paziente, wait);
                        break;
                    }
                    case 2: {
                        agenda.modificaAppuntamento(agenda,paziente);
                        break;
                    }
                    case 3: {
                        agenda.eliminaAppuntamento(agenda, wait, paziente);
                        break;
                    }
                    case 4: {
                        agenda.ricercaAppuntamenti(agenda);
                        break;
                    }
                    case 5:{
                        Statistiche.medicoPiuRichiesto(agenda, medico);
                        Statistiche.OraPiùRichiesta(agenda, medico);
                        Statistiche.percentualeSostituzioni(agenda, medico);
                        break;

                    }
                    case 6: {
                        System.out.println(wait.toString());
                        wait.add(Wait.joinWaitList(paziente, agenda, medicoOperante,wait,paziente));
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
