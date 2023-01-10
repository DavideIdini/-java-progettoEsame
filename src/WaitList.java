import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class WaitList {
    public Paziente pazienteInAttesa;
    public Date data;
    public int oraInizio;
    public int oraFine;
    public String idMedico;


    public WaitList(Paziente pazienteInAttesa, Date data, int oraInizio, int oraFine, String idMedico) {
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
    public static WaitList joinWaitList(){
        Scanner tastiera = new Scanner(System.in);
        System.out.println("inserimento dati paziente da aggiungere alla waitList");
        Paziente paziente = Paziente.registrazionePaziente();
        Date date = Appuntamento.inserimentoData();
        System.out.println("inserisci:\n ora inizio\n ora fine\n id medico");
        int oraI= tastiera.nextInt();
        System.out.println(oraI);
        int oraF = tastiera.nextInt();
        System.out.println(oraF);
        tastiera.nextLine();
        String idM = tastiera.nextLine();
        System.out.println(idM);
        WaitList a = new WaitList(paziente, date,oraI, oraF, idM );
        return a;
    }

    @Override
    public String toString() {
        return "WaitList{" +
                "pazienteInAttesa=" + pazienteInAttesa +
                ", data=" + data +
                ", oraInizio=" + oraInizio +
                ", oraFine=" + oraFine +
                ", idMedico='" + idMedico + '\'' +
                '}';
    }
}
