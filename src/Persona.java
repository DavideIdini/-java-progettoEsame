import java.io.Serializable;
import java.util.Scanner;

public class Persona implements Serializable {
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String indirizzo;

    public Persona(String codiceFiscale, String nome, String cognome, String indirizzo) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public static String inserimentoNome() {
        Scanner tastiera = new Scanner(System.in);
        while(true) {
            System.out.println("inserisci nome");
            String nome = tastiera.nextLine();
            try{
                if(nome.isEmpty()){
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
                    if (!(Character.isLetter(address[0].charAt(i)) ))
                        if(address[0].charAt(i) != ' ')
                            throw new Exception("Città non valida.");
                }
                System.out.println("Inserisci via");
                address[1] = tastiera.nextLine();
                if (address[1].isEmpty() || address[1].length()>30) {
                    throw new Exception("Via non valida.");
                }
                for (int i = 0; i < address[1].length(); i++) {
                    if (!Character.isAlphabetic(address[1].charAt(i)))
                        if(address[0].charAt(i) != ' ')
                            throw new Exception("Via non valida.");
                }
                System.out.println("Inserisci numero civico");
                address[2] = tastiera.next();
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
}

