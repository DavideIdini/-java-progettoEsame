import java.io.Serializable;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;

public class Appuntamento implements Serializable {
    private Date data;
    private String cf_paziente; //codice fiscale paziente
    private String id_medico; //id medico che svolgerà la visita o la terapia
    private String descrizione;
    private int ora_inizio;
    private int ora_fine;




    public Appuntamento(Date data, String cf_paziente, String id_medico, String descrizione, int ora_inizio, int ora_fine) {
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

    public Date getData() {
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



    public void setData(Date data) {
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
        DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        //imposta che i calcoli di conversione della data siano rigorosi
        formatoData.setLenient(false);
        return "\nAppuntamento{" +
                "data='" + formatoData.format(data) + '\'' +
                ", cf_paziente='" + cf_paziente + '\'' +
                ", id_medico='" + id_medico + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", ora_inizio=" + ora_inizio +
                ", ora_fine=" + ora_fine +
                '}';
    }
    public static Appuntamento creaAppuntamento(String IdMedico, List<Paziente> paziente){
        Scanner tastiera = new Scanner(System.in);
        System.out.println("Registra nuovo  appuntamento");
        Date data = inserimentoData();
        System.out.println("inserisci codice fiscale del paziente");
        String cf_paziente = inserimentoCF();
        Paziente test = controlloCf(cf_paziente, paziente);//controllo che il codice fiscale corrisponda a un paziente già salvato
        paziente.add(test);
        String id_dottore = IdMedico;
        System.out.println("inserisci descrizione");
        String descrizione = tastiera.nextLine();
        System.out.println("inserisci ora inizio");
        int ora_inizio = inserimentoOraInizio();
        System.out.println("inserisci ora fine");
        int ora_fine = inserimentoOraFine(ora_inizio);


        Appuntamento a = new Appuntamento(data,cf_paziente,id_dottore,descrizione, ora_inizio, ora_fine);
        return a;
    }
    static Paziente controlloCf(String cf_paziente, List<Paziente> paziente) {
        while(true){
            for(Paziente a : paziente){
                if(a.getCodiceFiscale().equals(cf_paziente)){
                    System.out.println("pazienta gia registrato, tutto ok! ");
                    return a;
                }
            }
            System.out.println("non ho trovato  nessun paziente che corrisponde a questo codice ficale\n" +
                    "per favore Registrare prima il paziente poi procedere con l'inserimento dati per l'appuntamento");
            Paziente a = Paziente.registrazionePaziente(paziente);
            return a;

        }
    }


    public static int inserimentoOraFine(int ora_inizio) {
        Scanner tastiera = new Scanner(System.in);
        System.out.println("inserisci ora fine appuntamento\n Studio aperto dalle 9 alle 20");

        while(true){
            int oraF = tastiera.nextInt();
            try{
                if(oraF<10 || oraF>20)
                    throw new Exception("Lo studio è chiuso in questi orari");
                if(oraF<ora_inizio)
                    throw new Exception("L'appunatmentop non può svolgersi a ritroso nel tempo");
                return oraF;
            } catch(InputMismatchException e){
                System.out.println(e.getMessage()+"inserisci un'ora fine  valida perfavore");
            } catch(Exception e){
                System.out.println(e.getMessage()+"inserisci un'ora  fine valida perfavore");
            }
        }

    }

    public static int inserimentoOraInizio() {
        Scanner tastiera = new Scanner(System.in);
        System.out.println("inserisci ora inizio appuntamento\n Studio aperto dalle 9 alle 20");

        while(true){
            int oraI = tastiera.nextInt();
            try{
                if(oraI<9 || oraI>19)
                    throw new Exception("Lo studio è chiuso in questi orari");
                return oraI;
            } catch(InputMismatchException e){
                System.out.println(e.getMessage()+"inserisci un'ora inizo valida perfavore");
            } catch(Exception e){
                System.out.println(e.getMessage()+"inserisci un'ora  inizo valida perfavore");
            }
        }

    }

    public static String inserimentoCF() {
        Scanner tastiera = new Scanner(System.in);

        while (true) {

            System.out.print("Inserisci codice fiscale : ");
            String codiceFiscale = tastiera.nextLine();
            try {

                if (codiceFiscale.length() != 16) {
                    throw new Exception("Codice fiscale non valido. Il codice fiscale deve avere 16 caratteri.");
                }

                for (int i = 0; i < 6; i++) {
                    if (!Character.isLetter(codiceFiscale.charAt(i))) {
                        throw new Exception("Codice fiscale non valido. I primi sei caratteri devono essere alfabetici.");
                    }
                }
                for (int i = 6; i < 8; i++) {
                    if (!Character.isDigit(codiceFiscale.charAt(i))) {
                        throw new Exception("codice fiscale non valido. I caratteri 7 e 8 devono essere numerici");
                    }
                }
                if (!Character.isLetter(codiceFiscale.charAt(8))) {
                    throw new Exception("codice fiscale non valido. Il carattere 9 deve essere alfabetico");
                }
                for (int i = 9; i < 11; i++) {
                    if (!Character.isDigit(codiceFiscale.charAt(i))) {
                        throw new Exception("codice fiscale non valido. I caratteri 10 e 11 devono essere numerici.");
                    }
                }
                if (!Character.isLetter(codiceFiscale.charAt(11))) {
                    throw new Exception("codice fiscale non valido. Il carattere 12 deve essere alfabetico");
                }

                for (int i = 12; i < 15; i++) {
                    if (!Character.isDigit(codiceFiscale.charAt(i))) {
                        throw new Exception("codice fiscale non valido. I caratteri dal 13 al 15 devono essere numerici .");
                    }
                }


                if (!Character.isLetter(codiceFiscale.charAt(15))) {
                    throw new Exception("codice fiscale non valido. L'ultimo carattere deve essere alfabetico");
                }

                System.out.println("Codice fiscale valido");
                return codiceFiscale;
            } catch (Exception e) {

                System.out.println(e.getMessage());
            }
        }

    }

    public static void creaAppuntamenti(Scanner tastiera, Agenda agenda, Medico medicoOperante, List<Paziente> paziente, List<Wait> wait){

        Appuntamento appuntamento = creaAppuntamento(medicoOperante.getId_medico(),paziente);
        System.out.println("vuoi salvare il seguente appuntamento in agenda? [si/no]");
        String risposta = tastiera.nextLine();
        if(risposta.toUpperCase().equals("SI"))
            agenda.InsericiAppuntamentoInAgenda(medicoOperante.getId_medico(), appuntamento, paziente, wait);
        else
            return;

        }

    public static Date inserimentoData() {
        String s;
        Date d = null;
        while(true){
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
            //s = formatoData.format(d);
            //System.out.println(s);
            return d;
        } catch (ParseException e) {
            System.out.println("Formato data non valido.");
        }
    }
    }

}

