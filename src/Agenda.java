import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

import static java.lang.System.exit;

public class Agenda implements Serializable {
 private final static String frase = "puoi scegliere tra tre opzioni \n" +
         "che vuoi fare? \n"+
         "1) provare con orari diversi\n"+
         "2) provare con una nuova data e nuovi orari\n"+
         "3) aggiungere l'appuntamento alla lista d'attesa\n"+
         "4) chiudere il programma \n";
 private final Map<String, List<Appuntamento>> appuntamenti_per_medico;
 public Agenda(Map<String, List<Appuntamento>> appuntamenti_per_medico) {
  this.appuntamenti_per_medico = appuntamenti_per_medico;
 }
 public Map<String, List<Appuntamento>> getAppuntamentiPerMedico() {return appuntamenti_per_medico;}
 /*public void setAppuntamenti_per_medico(Map<String, List<Appuntamento>> appuntamenti_per_medico) {
  this.appuntamenti_per_medico = appuntamenti_per_medico;
 }*/
 public void InsericiAppuntamentoInAgenda(String id_medico, Appuntamento appuntamento, List<Paziente> paziente, List<Wait> wait){

  if( appuntamenti_per_medico.isEmpty() || appuntamenti_per_medico.get(id_medico)==null){
      List<Appuntamento> a = new ArrayList<>();
      a.add(appuntamento);
      appuntamenti_per_medico.put(id_medico, a);
      System.out.println("appuntamento fissato nell'orario selezionato");
  }
  else{
   List<Appuntamento> appuntamenti;
   appuntamenti=appuntamenti_per_medico.get(id_medico);
   if(controlloDisponibilita(appuntamenti,appuntamento, paziente, wait)){
    appuntamenti.add(appuntamento);
    appuntamenti_per_medico.put(id_medico, appuntamenti);
    System.out.println("appuntamento fissato nell'orario selezionato");
   }


  }

 }
 private boolean controlloDisponibilita(List<Appuntamento> appuntamenti, Appuntamento appuntamento, List<Paziente> paziente, List<Wait> wait) {
  Scanner tastiera = new Scanner(System.in);
  for(Appuntamento d: appuntamenti){
   if( d.getData().equals(appuntamento.getData()) &&
           (d.getOra_inizio()<=appuntamento.getOra_inizio() && appuntamento.getOra_inizio()<d.getOra_fine()
           || d.getOra_inizio()<appuntamento.getOra_fine() && appuntamento.getOra_fine()<=d.getOra_fine()
           || (d.getOra_inizio()==appuntamento.getOra_inizio()
           ||(appuntamento.getOra_inizio()<=d.getOra_inizio() && appuntamento.getOra_fine()>=d.getOra_fine())))){
    System.out.println("L'orario selezionato per questo appuntamento non è disponibile ");

   boolean flag = true;
    while(flag){
     System.out.println(frase);
     try {
      String scelta = tastiera.nextLine();
      switch (Integer.parseInt(scelta)) {
       case 4: {
        exit(1);
       }
       case 1: {
        System.out.println("inserisci ora inizio");
        appuntamento.setOra_inizio(Appuntamento.inserimentoOraInizio());
        System.out.println("inserisci ora fine");
        appuntamento.setOra_fine(Appuntamento.inserimentoOraFine(appuntamento.getOra_inizio()));
        controlloDisponibilita(appuntamenti, appuntamento, paziente, wait);
        flag = false;
        break;
       }

       case 2: {
        System.out.println("inserisci nuova data");
        appuntamento.setData(Appuntamento.inserimentoData());
        System.out.println("inserisci ora inizio");
        appuntamento.setOra_inizio(Appuntamento.inserimentoOraInizio());
        System.out.println("inserisci ora fine");
        appuntamento.setOra_fine(Appuntamento.inserimentoOraFine(appuntamento.getOra_inizio()));
        controlloDisponibilita(appuntamenti, appuntamento, paziente, wait);
        flag = false;
        break;

       }
       case 3: {
        Paziente a = Paziente.ricercaPazientePerCf(paziente,appuntamento.getCf_paziente());
        wait.add(new Wait(a,appuntamento.getData(), appuntamento.getOra_inizio(), appuntamento.getOra_fine(), appuntamento.getId_medico()));
        System.out.println("appuntamento aggiunto alla wait List");
        flag = false;
        break;

       }

       default:{
        throw new Exception("devi inserire un numero da 1 a 4");
       }
      }
     }catch (NumberFormatException e){
      System.out.println("devi inserire un numero compreso tra 1 e 4");
     } catch (Exception e) {
      System.out.println(e.getMessage());
     }

    }
   }
   }

  return true;
 }
 public void modificaAppuntamento(Agenda agenda, List<Paziente> paziente) {
  Scanner tastiera = new Scanner(System.in);
  System.out.println("inserisci id medico del quale si vuole modificare appuntamento");
  String id_medico = tastiera.nextLine();
  List<Appuntamento> appuntamentoPerMedico = agenda.getAppuntamentiPerMedico().get(id_medico);
  System.out.println(agenda.getAppuntamentiPerMedico().get(id_medico).toString());
  System.out.println("inserisci numero appuntamento");
  int a = tastiera.nextInt();
  agenda.getAppuntamentiPerMedico().get(id_medico).remove(a);
  Appuntamento appuntamento;
  appuntamento = Appuntamento.creaAppuntamento(id_medico, paziente);
  appuntamentoPerMedico.add(appuntamento);
  appuntamenti_per_medico.replace(id_medico, appuntamentoPerMedico);

 }
 public void eliminaAppuntamento(Agenda agenda, List<Wait> waitList, List<Paziente> paziente ) {
  Scanner tastiera = new Scanner(System.in);
  System.out.println("inserisci id medico del quale si vuole eliminare appuntamento");
  String id_medico = tastiera.nextLine();
  //List<Appuntamento> appuntamentoPerMedico = agenda.getAppuntamentiPerMedico().get(id_medico);
  System.out.println(agenda.getAppuntamentiPerMedico().get(id_medico).toString());
  System.out.println("inserisci numero appuntamento da eliminare");
  int a = tastiera.nextInt();
  Appuntamento appuntamento = agenda.getAppuntamentiPerMedico().get(id_medico).get(a);
  agenda.getAppuntamentiPerMedico().get(id_medico).remove(a);
  System.out.println("appuntamento eliminato");
  try {
   Thread.sleep(1000);
  } catch (InterruptedException e) {
   throw new RuntimeException(e);
  }
  System.out.println("Vuoi controllare se ci sono pazienti in lista d'attesa per la data selezionata [si/tutto il resto no]");
  if(tastiera.nextLine().equalsIgnoreCase("SI"))
  controlloWaitList(appuntamento.getId_medico(), waitList,agenda, paziente);
  else
   System.out.println("Non ci sono pazienti in waitList questa volta");

 }
 private void controlloWaitList( String idMedico, List<Wait> waitList, Agenda agenda, List<Paziente> paziente) {
  Scanner tastiera = new Scanner(System.in);
 Iterator<Wait> iterator = waitList.listIterator();
   while(iterator.hasNext()){
   Wait a = iterator.next();
    Appuntamento b = new Appuntamento(a.getData(),a.getPazienteInAttesa().getCodiceFiscale(),a.getIdMedico(),"sostituzione" ,a.getOraInizio(),a.getOraFine());
    if(Wait.controlloDisponibilita2(agenda.getAppuntamentiPerMedico().get(idMedico),b)) {
     System.out.println("un paziente è in attesa per la data e l'ora selezionata vuoi contattarlo? [si/tutto il resto no]");
     String risposta = tastiera.nextLine();
     if (risposta.equalsIgnoreCase("SI")) {
      System.out.println(a.getPazienteInAttesa().getCellulare()+"\n"+a.getPazienteInAttesa().getEmail());
      System.out.println("Il paziente è ancora disponibile? [si/tutto il resto no]" );
      risposta = tastiera.nextLine();
      if (risposta.equalsIgnoreCase("SI")) {
       Appuntamento sostituto = new Appuntamento(a.getData(),a.getPazienteInAttesa().getCodiceFiscale(),a.getIdMedico(), "sostituzione", a.getOraInizio(), a.getOraFine());
       agenda.InsericiAppuntamentoInAgenda(a.getIdMedico(), sostituto, paziente, waitList);
       iterator.remove();
       System.out.println(waitList);
      }else{

       iterator.remove();
      // waitList.remove(waitList.indexOf(a));
       System.out.println(waitList);

      }
     }
    }
   }

 }
 public void ricercaAppuntamenti(Agenda agenda) {
  Scanner tastiera = new Scanner(System.in);
  System.out.println("inserisci id medico del quale si vuole effettuare una ricerca di appuntamente");
  String id_medico = tastiera.nextLine();
  List<Appuntamento> appuntamentiPerMedico = agenda.getAppuntamentiPerMedico().get(id_medico);
  System.out.println("in base a cosa vuoi effettuare la ricerca ?"+
                   "\n1) codice fiscale paziente\n2)data\n3)ora inizio");
  int scelta = tastiera.nextInt();
  switch (scelta) {
   case 1 : { ricercaPerCodiceFiscale(appuntamentiPerMedico); break;}
   case 2 : { ricercaPerData(appuntamentiPerMedico); break;}
   case 3 : { ricercaPerOraInizio(appuntamentiPerMedico); break;}
  }
 }
 private void ricercaPerOraInizio(List<Appuntamento> appuntamentiPerMedico) {
  Scanner tastiera = new Scanner(System.in);
  System.out.println("inserisci l'ora inizo dell'appuntamento che vuoi ricercare");
  int oraI = tastiera.nextInt();
  boolean flag = true;
  for (Appuntamento a : appuntamentiPerMedico) {
   if (a.getOra_inizio()==oraI) {
    System.out.println("ho trovato un appuntamento con quest'ora inizio -->" + a + "\ned ha indice " + appuntamentiPerMedico.indexOf(a));
    flag = false;
   }
  }
  if(flag) System.out.println("non ho trovato appuntamenti ");
 }
 private void ricercaPerData(List<Appuntamento> appuntamentiPerMedico) {
  System.out.println("inserisci data che vuoi ricercare");
  Date d = inserimentoData();
  boolean flag = true;
  for (Appuntamento a : appuntamentiPerMedico) {
   if (a.getData().equals(d)) {
    flag = false;
    System.out.println("ho trovato un appuntamento con questa data -->" + a + "\ned ha indice " + appuntamentiPerMedico.indexOf(a));
   }
  }
  if(flag) System.out.println("non ho trovato appuntamenti");
 }
 private static Date inserimentoData() {
  String s;
  Date d = null;
  //si procura la data sotto forma di una stringa nel formato SHORT
  System.out.println("Inserisci la data [gg/mm/yyyy]: ");
  Scanner in = new Scanner(System.in);
  s = in.nextLine();
  //converte la stringa della data in un oggetto di classe Date
  try{
   DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
   //imposta che i calcoli di conversione della data siano rigorosi
   formatoData.setLenient(false);
   d = formatoData.parse(s);
  } catch (ParseException e) {
   System.out.println("Formato data non valido.");
  }

  return d;
 }
 private void ricercaPerCodiceFiscale(List<Appuntamento> appuntamentiPerMedico) {
  Scanner tastiera = new Scanner(System.in);
  System.out.println("inserisci codice fiscale paziente che vuoi ricercare");
  String codiceFiscaleR = tastiera.nextLine();
  boolean flag = true;
  for(Appuntamento a: appuntamentiPerMedico){
   if(a.getCf_paziente().equalsIgnoreCase(codiceFiscaleR)){
    flag = false;
    System.out.println("ho trovato un appuntamento con questo codice fiscale -->"+a+"\ned ha indice "+appuntamentiPerMedico.indexOf(a));
   }
  }
  if(flag) System.out.println("non ho trovato appuntamenti per i criteri selezionati");
 }
}
