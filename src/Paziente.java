import java.util.List;
import java.util.Scanner;

public class Paziente extends Persona{
    private String email;
    private String cellulare;
    private String id_paziente;
    private String password;


    public Paziente(String codiceFiscale, String nome, String cognome, String indirizzo, String email, String cellulare, String id_paziente, String password) {
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

    public String getId_paziente() {
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
    }
     public static List<Paziente> JoinWaitList(List<Paziente> waitList){
        Scanner tastiera = new Scanner(System.in);
        Paziente a = null;
        System.out.println("inserisc dati paziente da aggiungere alla waitList"+"\n codice fiscale \n nome \n cognome \n indirizzo \n email \n cellulare ");
         a.setCodiceFiscale(tastiera.nextLine());
         a.setNome(tastiera.nextLine());
         a.setCognome(tastiera.nextLine());
         a.setIndirizzo(tastiera.nextLine());
         a.setEmail(tastiera.nextLine());
         a.setCellulare(tastiera.nextLine());
         waitList.add(a);


        return waitList;
     }
}
