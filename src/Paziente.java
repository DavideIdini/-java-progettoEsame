import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Paziente extends Persona implements Serializable {
    private String email;
    private String cellulare;

    public Paziente(String codiceFiscale, String nome, String cognome, String indirizzo, String email, String cellulare) {
        super(codiceFiscale, nome, cognome, indirizzo);
        this.email = email;
        this.cellulare = cellulare;
    }

    public static Paziente ricercaPazientePerCf(List<Paziente> paziente, String cf_paziente) {
        for (Paziente a : paziente){
            if(a.getCodiceFiscale().equalsIgnoreCase(cf_paziente))
                return a;
        }
        //non dovremmo mai arrivare qua in teoria, per via dei controlli fatti in precedenza, ma nel caso
        System.out.println("paziente non trovato registrarlo perfavore");
        return registrazionePaziente(paziente);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }
    public static Paziente registrazionePaziente(List<Paziente> pazienti){
        String cf = Appuntamento.inserimentoCF();
        cf = controlloCfPaziente(cf, pazienti);
        if(cf.isEmpty())
            return null;
        String nome = inserimentoNome();
        String cognome = inserimentoCognome();
        String indirizzo = inserimentoIndirizzo();
        String email = insermentoEmail();
        String cellulare= inserimentoCellulare();
        Paziente paziente = new Paziente(cf, nome, cognome, indirizzo, email, cellulare );

        return paziente;
    }
    public static String controlloCfPaziente(String cf_paziente, List<Paziente> paziente) {
        if(paziente.isEmpty())
            return cf_paziente;
        while(true){
            for(Paziente a : paziente){
                if(a.getCodiceFiscale().equals(cf_paziente)){
                    System.out.println("ATTENZIONE, pazienta gia registrato  ");
                    return null;
                }
            }
            return cf_paziente;
        }

    }
    public static String inserimentoCellulare() {
        Scanner tastiera = new Scanner(System.in);
        while(true) {
            System.out.println("inserisci numero di cellulare \n +39    ");
            String cell = tastiera.nextLine();
            try{
                if(cell.length() != 10){
                    throw new Exception("numero di cellulare non valido.");
                }
                for(int i = 0; i<cell.length();i++){
                    if(!Character.isDigit(cell.charAt(i)))
                        throw new Exception("cellulare non valido");
                }
                return cell;
            }catch (Exception e){
                System.out.println(e.getMessage()+" reinseriscilo ");
            }
        }
    }
    public static String insermentoEmail() {
        String email;
        Scanner tastiera = new Scanner(System.in);
        while(true) {
            System.out.println("inserisci Email");
            email = tastiera.nextLine();
            try{
                if(email.isEmpty() || email.length()<2 ){
                    throw new Exception("Email non valida.");
                }
                int c=0;
                for(int i = 0; i<email.length();i++){
                    if(!Character.isLetterOrDigit(email.charAt(i)) && email.charAt(i)!='-' && email.charAt(i)!='_' && email.charAt(i)!='@' && email.charAt(i)!='.')
                        throw new Exception("Email contiene un carattere non valido");
                    if(email.charAt(i)=='@')
                        c++;

                }
                if(c!=1)
                    throw new Exception("Email deve contenere una sola '@' ");
                return email;
            }catch (Exception e){
                System.out.println(e.getMessage()+" reinseriscilo ");
            }
        }
    }

}
