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

  if(appuntamenti_per_medico.isEmpty() || appuntamenti_per_medico.get(id_medico).isEmpty()){
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
   if(d.getOra_inizio()<appuntamento.getOra_inizio() && appuntamento.getOra_inizio()<d.getOra_fine()
           || d.getOra_inizio()<appuntamento.getOra_fine() && appuntamento.getOra_fine()<d.getOra_fine() ){
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

 @Override
 public String toString() {
  return "Agenda{" +
          "appuntamenti_per_medico=" + appuntamenti_per_medico+
          '}';
 }
}
