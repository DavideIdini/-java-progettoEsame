import java.util.List;
import java.util.Scanner;

public class Paziente extends Persona{
    private String email;
    private String cellulare;
   // private String id_paziente;
   // private String password;


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
//implementazione futura in pausa per ora
 /*   public String getId_paziente() {
        return id_paziente;
    }

    public void setId_paziente(String id_paziente) {
        this.id_paziente = id_paziente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/
    public static Paziente registrazionePaziente(){
        Scanner tastiera = new Scanner(System.in);
        System.out.println("inserimento dati paziente\n-codice fiscale\n-nome\n-cognome\n-indirizzo\n-email\n-cellulare");
        String cf = tastiera.nextLine();
        String nome = tastiera.nextLine();
        String cognome = tastiera.nextLine();
        String indirizzo = tastiera.nextLine();
        String email = tastiera.nextLine();
        String cellulare= tastiera.nextLine();
        Paziente paziente = new Paziente(cf, nome, cognome, indirizzo, email, cellulare );

        return paziente;
    }
}
