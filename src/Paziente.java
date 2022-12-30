public class Paziente extends Persona{
    private String email;
    private String cellulare;
    private String id_paziente;
    private String password;
    public Paziente(String codiceFiscale, String nome, String cognome, String indirizzo) {
        super(codiceFiscale, nome, cognome, indirizzo);
    }
}
