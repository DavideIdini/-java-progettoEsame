

import java.util.*;

public class Statistiche {
    Agenda agenda;
    List<Medico> medici;

    public Statistiche(Agenda agenda, List<Medico> medico) {
        this.agenda = agenda;
        this.medici = medico;
    }
    public static void medicoPiuRichiesto(Agenda agenda, List<Medico> medico){

        Map<String,Integer> map = new HashMap<>();
        //popolo la mappa di medici
        int tot = 0;
        for(Medico a : medico){
            int c = 0;
            map.put(a.getId_medico(),c);
            for(Appuntamento b:agenda.getAppuntamentiPerMedico().get(a.getId_medico())){
                tot++;
                c++;
                map.put(a.getId_medico(), c);
            }
        }
        //salvo i dati in percentuale e li stampo
        int p = 0;
        for(Medico a : medico) {
            p = (map.get(a.getId_medico()) * 100) / tot;
            map.put(a.getId_medico(), p);
            System.out.println(a.getId_medico()+" ha una percentuale di appuntamenti salvati del "+map.get(a.getId_medico())+"%");
        }


    }
   public static void OraPiùRichiesta(Agenda agenda, List<Medico> medico){
        HashMap<Integer,Integer> occorrenzaOre = new HashMap<>();
        int ore,frequenza=0,max=0,pos = 0;
        for(Medico a : medico){
            for(Appuntamento b: agenda.getAppuntamentiPerMedico().get(a.getId_medico())){
                for( int i = b.getOra_inizio(); i<= b.getOra_fine(); i++){
                    if(occorrenzaOre.containsKey(i)){
                        frequenza = occorrenzaOre.get(i);
                        int count = frequenza+1;
                        occorrenzaOre.put(i, count);
                    }else{
                        occorrenzaOre.put(i,1);
                    }
                }
            }
                for(int i = 9; i <= 20; i++){
                    if(occorrenzaOre.getOrDefault(i, 0)>max){
                        max = occorrenzaOre.get(i);
                        pos = i;
                    }
                }
        }
       System.out.println("la fascia oraria più richiesta è dalle : "+pos+" alle :"+(pos+1));

   }

    public static void percentualeSostituzioni(Agenda agenda, List<Medico> medico){
        int tot = 0;
        int sost = 0;
        for(Medico a: medico){
            for( Appuntamento b : agenda.getAppuntamentiPerMedico().get(a.getId_medico())){
                tot++;
                if(b.getDescrizione().equalsIgnoreCase("sostituzione"))
                    sost++;
            }
        }
        System.out.println("la percentuale di appuntamenti con sostituzione è :"+(double)(100*sost/tot));
    }
}
