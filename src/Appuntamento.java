public class Appuntamento {
    private String data;
    private String cf_paziente; //codice fiscale paziente
    private String id_medico; //id medico che svolgerà la visita o la terapia
    private String descrizione;
    private int ora_inizio;
    private int ora_fine;




    public Appuntamento(String data, String cf_paziente, String id_medico, String descrizione, int ora_inizio, int ora_fine) {
        this.data = data;
        this.cf_paziente = cf_paziente;
        this.id_medico = id_medico;
        this.descrizione = descrizione;
        this.ora_inizio = ora_inizio;
        this.ora_fine = ora_fine;

    }


    public void setOra_inizio(int ora_inizio) {
        this.ora_inizio = ora_inizio;
    }

    public void setOra_fine(int ora_fine) {
        this.ora_fine = ora_fine;
    }

    public int getOra_inizio() {
        return ora_inizio;
    }

    public int getOra_fine() {
        return ora_fine;
    }

    public String getData() {
        return data;
    }

    public String getCf_paziente() {
        return cf_paziente;
    }

    public String getId_medico() {
        return id_medico;
    }

    public String getDescrizione() {
        return descrizione;
    }



    public void setData(String data) {
        this.data = data;
    }

    public void setCf_paziente(String cf_paziente) {
        this.cf_paziente = cf_paziente;
    }

    public void setId_medico(String id_medico) {
        this.id_medico = id_medico;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Appuntamento{" +
                "data='" + data + '\'' +
                ", cf_paziente='" + cf_paziente + '\'' +
                ", id_medico='" + id_medico + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", ora_inizio=" + ora_inizio +
                ", ora_fine=" + ora_fine +
                '}';
    }
}
