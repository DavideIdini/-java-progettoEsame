import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Wait implements Serializable {
    public Paziente pazienteInAttesa;
    public Date data;
    public int oraInizio;
    public int oraFine;
    public String idMedico;


    public Wait(Paziente pazienteInAttesa, Date data, int oraInizio, int oraFine, String idMedico) {
        this.pazienteInAttesa = pazienteInAttesa;
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.idMedico = idMedico;
    }

    public Paziente getPazienteInAttesa() {
        return pazienteInAttesa;
    }

    public void setPazienteInAttesa(Paziente pazienteInAttesa) {
        this.pazienteInAttesa = pazienteInAttesa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(int oraInizio) {
        this.oraInizio = oraInizio;
    }

    public int getOraFine() {
        return oraFine;
    }

    public void setOraFine(int oraFine) {
        this.oraFine = oraFine;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }
    public static Wait joinWaitList(List<Paziente> pazienti, Agenda agenda, Medico medicoOperante, List<Wait> wait){
        Scanner tastiera = new Scanner(System.in);
        System.out.println("inserimento dati paziente da aggiungere alla waitList");
        String cf_paziente = Appuntamento.inserimentoCF();
        Paziente paziente1 = Appuntamento.controlloCf(cf_paziente, pazienti);
        if(!pazienti.contains(paziente1))
            pazienti.add(paziente1);
        Date date = Appuntamento.inserimentoData();
        System.out.println("inserisci:ora inizio ora fine");
        int oraI= Appuntamento.inserimentoOraInizio();
        int oraF = Appuntamento.inserimentoOraFine(oraI);
        String idM = medicoOperante.getId_medico();
        Appuntamento w = new Appuntamento(date,paziente1.getCodiceFiscale(), idM, "sostituzione", oraI, oraF);
        if(controlloDisponibilita2(agenda.getAppuntamentiPerMedico().get(idM),w)){
            System.out.println("L'appuntamento selezionato è disponibile, vuoi confermarlo e salvarlo in agenda?" +
                    "\n[si/tutto il resto no]");
            String risposta = tastiera.nextLine();
            if(risposta.equalsIgnoreCase("si"))
                agenda.InsericiAppuntamentoInAgenda(idM, w, pazienti, wait);
        }

        Wait a = new Wait(paziente1, date,oraI, oraF, idM );

        return a;
    }
    public static boolean controlloDisponibilita2(List<Appuntamento> appuntamenti, Appuntamento appuntamento){
        if(appuntamenti.isEmpty())
            return true;
        for(Appuntamento d: appuntamenti) {
            if (d.getData().equals(appuntamento.getData()) &&
                    (d.getOra_inizio()<=appuntamento.getOra_inizio() && appuntamento.getOra_inizio()<d.getOra_fine()
                            || d.getOra_inizio()<appuntamento.getOra_fine() && appuntamento.getOra_fine()<=d.getOra_fine()
                            || (d.getOra_inizio()==appuntamento.getOra_inizio()
                            ||(appuntamento.getOra_inizio()<=d.getOra_inizio() && appuntamento.getOra_fine()>=d.getOra_fine())))){
                return false;
            }

        }
        return true;
    }

    @Override
    public String toString() {
        return "\nWaitList{" +
                "pazienteInAttesa=" + pazienteInAttesa.getNome() +
                ", data=" + data +
                ", oraInizio=" + oraInizio +
                ", oraFine=" + oraFine +
                ", idMedico='" + idMedico + '\'' +
                '}';
    }
}
