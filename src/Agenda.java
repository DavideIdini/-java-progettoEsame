import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class Agenda{

 private Map<Integer, List<Appuntamento>> appuntamenti_per_medico = new HashMap<>();

 public Agenda(Map<Integer, List<Appuntamento>> appuntamenti_per_medico) {
  this.appuntamenti_per_medico = appuntamenti_per_medico;
 }

 public Map<Integer, List<Appuntamento>> getAppuntamentiPerMedico() {
  return appuntamenti_per_medico;
 }

 public void setAppuntamenti_per_medico(Map<Integer, List<Appuntamento>> appuntamenti_per_medico) {
  this.appuntamenti_per_medico = appuntamenti_per_medico;
 }
 public void InsericiAppuntamentoInAgenda(int id_medico,Appuntamento appuntamento){

  if( appuntamenti_per_medico.isEmpty() || appuntamenti_per_medico.get(id_medico)==null){
      List<Appuntamento> a = new ArrayList<>();
      a.add(appuntamento);
      appuntamenti_per_medico.put(id_medico, a);
      System.out.println("appuntamento fissato nell'orario selezionato");
  }
  else{
   List<Appuntamento> appuntamenti = new ArrayList<>();
   appuntamenti=appuntamenti_per_medico.get(id_medico);
   if(controlloDisponibilita(appuntamenti,appuntamento)){
    appuntamenti.add(appuntamento);
    appuntamenti_per_medico.put(id_medico, appuntamenti);
    System.out.println("appuntamento fissato nell'orario selezionato");
   }


  }

 }

 private boolean controlloDisponibilita(List<Appuntamento> appuntamenti, Appuntamento appuntamento) {
  Scanner tastiera = new Scanner(System.in);
  for(Appuntamento d: appuntamenti){
   if(d.getOra_inizio()<=appuntamento.getOra_inizio() && appuntamento.getOra_inizio()<d.getOra_fine()
           && d.getOra_inizio()<appuntamento.getOra_fine() && appuntamento.getOra_fine()<=d.getOra_fine()
           && d.getData().equals(appuntamento.getData())
           || d.getOra_inizio()==appuntamento.getOra_inizio()){
    System.out.println("L'orario selezionato per questo appuntamento non è disponibile scegliere un altro orario");
    System.out.println("inserisci ora inizio");
    appuntamento.setOra_inizio(tastiera.nextInt());
    System.out.println("inserisci ora fine");
    appuntamento.setOra_fine(tastiera.nextInt());
    controlloDisponibilita(appuntamenti, appuntamento);
   }
   }

  return true;
 }


 public String toStamp(int n) {
  return "\n Agenda{ " +
          "appuntamenti per medico = " + appuntamenti_per_medico.get(n)+
          '}';
 }

 public void modificaAppuntamento(Agenda agenda) {
  Scanner tastiera = new Scanner(System.in);
  System.out.println("inserisci id medico del quale si vuole modificare appuntamento");
  int id_medico = tastiera.nextInt();
  List<Appuntamento> appuntamentoPerMedico = agenda.getAppuntamentiPerMedico().get(id_medico);
  System.out.println(agenda.getAppuntamentiPerMedico().get(id_medico).toString());
  System.out.println("inserisci numero appuntamento");
  int a = tastiera.nextInt();
  agenda.getAppuntamentiPerMedico().get(id_medico).remove(a);
  Appuntamento appuntamento;
  appuntamento = Appuntamento.creaAppuntamento();
  appuntamentoPerMedico.add(appuntamento);
  appuntamenti_per_medico.replace(id_medico, appuntamentoPerMedico);

 }

 public void eliminaAppuntamento(Agenda agenda, List<WaitList> waitList) {
  Scanner tastiera = new Scanner(System.in);
  System.out.println("inserisci id medico del quale si vuole eliminare appuntamento");
  int id_medico = tastiera.nextInt();
  List<Appuntamento> appuntamentoPerMedico = agenda.getAppuntamentiPerMedico().get(id_medico);
  System.out.println(agenda.getAppuntamentiPerMedico().get(id_medico).toString());
  System.out.println("inserisci numero appuntamento da eliminare");
  int a = tastiera.nextInt();
  Appuntamento appuntamento = agenda.getAppuntamentiPerMedico().get(id_medico).get(a);
  agenda.getAppuntamentiPerMedico().get(id_medico).remove(a);
  System.out.println("appuntamento eliminato");
  System.out.println("controllo se ci sono pazienti in lista d'attesa per la data selezionata");
  controlloWaitList(appuntamento,waitList,agenda);

 }

 private void controlloWaitList(Appuntamento appuntamento, List<WaitList> waitList, Agenda agenda) {
  Scanner tastiera = new Scanner(System.in);
   for(WaitList a: waitList){
    if(appuntamento.getId_medico().equals(a.getIdMedico())
            && appuntamento.getData().equals(a.getData())
            && a.getOraInizio()>=appuntamento.getOra_inizio()
            && a.getOraFine()<=appuntamento.getOra_fine()) {
     System.out.println("un paziente è in attesa per la data e l'ora selezionata vuoi contattarlo? [si/tutto il resto no]");
     String risposta = tastiera.nextLine();
     if (risposta.toUpperCase().equals("SI")) {
      System.out.println(a.getPazienteInAttesa().getCellulare()+"\n"+a.getPazienteInAttesa().getEmail());
      System.out.println("Il paziente è ancora disponibile?" );
      risposta = tastiera.nextLine();
      if (risposta.toUpperCase().equals("SI")) {
       Appuntamento sostituto = new Appuntamento(a.getData(),a.getPazienteInAttesa().getCodiceFiscale(),a.getIdMedico(), "sostituzione", a.getOraInizio(), a.getOraFine());
       agenda.InsericiAppuntamentoInAgenda(Integer.parseInt(a.getIdMedico()), sostituto);
      }
     }
    }
   }

 }

 public void ricercaAppuntamenti(Agenda agenda) {
  Scanner tastiera = new Scanner(System.in);
  System.out.println("inserisci id medico del quale si vuole effettuare una ricerca di appuntamente");
  int id_medico = tastiera.nextInt();
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
  int c = 0;
  for (Appuntamento a : appuntamentiPerMedico) {
   if (a.getOra_inizio()==oraI) {
    System.out.println("ho trovato un appuntamento con quest'ora inizio -->" + a.toString() + "\ned ha indice " + c);
   }
   c++;
  }
 }

 private void ricercaPerData(List<Appuntamento> appuntamentiPerMedico) {
  Scanner tastiera = new Scanner(System.in);
  System.out.println("inserisci data che vuoi ricercare");
  Date d = inserimentoData();
  int c = 0;
  for (Appuntamento a : appuntamentiPerMedico) {
   if (a.getData().equals(d)) {
    System.out.println("ho trovato un appuntamento con questa data -->" + a.toString() + "\ned ha indice " + c);
   }
   c++;
  }
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
  int c=0;
  for(Appuntamento a: appuntamentiPerMedico){
   if(a.getCf_paziente().toUpperCase().equals(codiceFiscaleR.toUpperCase())){
    System.out.println("ho trovato un appuntamento con questo codice fiscale -->"+a.toString()+"\ned ha indice "+c);
   }
   c++;
  }
 }
}
