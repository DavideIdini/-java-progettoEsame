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
        Scanner tastiera = new Scanner(System.in);
        String cf = Appuntamento.inserimentoCFPaziente();
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
            System.out.println("inserisci numero di cellulare");
            String cell = tastiera.nextLine();
            try{
                if(cell.isEmpty() || cell.length()!=10 ){
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

    public static String inserimentoIndirizzo() {
        String[] address = new String[3];
        Scanner tastiera = new Scanner(System.in);
        String indirizzo;
        while(true) {
            try {
                System.out.println("Inserimento indirizzo");
                System.out.println("Inserisci città");
                address[0] = tastiera.nextLine();
                if (address[0].isEmpty() || address[0].length() == 0) {
                    throw new Exception("Città non valida.");
                }
                for (int i = 0; i < address[0].length(); i++) {
                    if (!Character.isAlphabetic(address[0].charAt(i)))
                        throw new Exception("Città non valida.");
                }
                System.out.println("Inserisci via");
                address[1] = tastiera.nextLine();
                if (address[1].isEmpty() || address[1].length() == 0) {
                    throw new Exception("Via non valida.");
                }
                for (int i = 0; i < address[1].length(); i++) {
                    if (!Character.isAlphabetic(address[1].charAt(i)))
                        throw new Exception("Via non valida.");
                }
                System.out.println("Inserisci numero civico");
                address[2] = tastiera.nextLine();
                if (address[2].isEmpty() || address[2].length() == 0) {
                    throw new Exception("Numero civico non valido.");
                }
                for (int i = 0; i < address[2].length(); i++) {
                    if (!Character.isDigit(address[2].charAt(i)))
                        throw new Exception("numero civico non valido.");
                }
                return indirizzo = address[0] + " " + "via " + address[1] + " " + address[2];
            } catch (Exception e) {
                System.out.println(e.getMessage() + " reinserisci  ");
            }
        }

    }

    public static String inserimentoCognome() {
        Scanner tastiera = new Scanner(System.in);
        while(true) {
            System.out.println("inserisci Cognome");
            String nome = tastiera.nextLine();
            try{
                if(nome.isEmpty() || nome.length()==0 ){
                    throw new Exception("Cognome non valido.");
                }
                for(int i = 0; i<nome.length();i++){
                    if(!Character.isAlphabetic(nome.charAt(i)))
                        throw new Exception("Cognoome non valido");
                }
                return nome;
            }catch (Exception e){
                System.out.println(e.getMessage()+" reinseriscilo ");
            }
        }
    }



    public static String inserimentoNome() {
        Scanner tastiera = new Scanner(System.in);
        while(true) {
            System.out.println("inserisci nome");
            String nome = tastiera.nextLine();
            try{
                if(nome.isEmpty() || nome.length()==0 ){
                    throw new Exception("Nome non valido.");
                }
                for(int i = 0; i<nome.length();i++){
                    if(!Character.isAlphabetic(nome.charAt(i)))
                        throw new Exception("Nome non valido");
                }
                return nome;
            }catch (Exception e){
                System.out.println(e.getMessage()+" reinseriscilo ");
            }
        }
    }
}
