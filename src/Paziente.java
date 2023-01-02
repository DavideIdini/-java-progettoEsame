public class Paziente extends Persona{
    private String email;
    private String cellulare;
    private String id_paziente;
    private String password;


    public Paziente(String codiceFiscale, String nome, String cognome, String indirizzo, String email, String cellulare, String id_paziente, String password) {
        super(codiceFiscale, nome, cognome, indirizzo);
        this.email = email;
        this.cellulare = cellulare;
        this.id_paziente = id_paziente;
        this.password = password;
    }
}
