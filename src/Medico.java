public class Medico extends Persona{
    public Medico(String codiceFiscale, String nome, String cognome, String indirizzo) {
        super(codiceFiscale, nome, cognome, indirizzo);
    }
    private int id_medico;
    private String password;


    public Medico(String codiceFiscale, String nome, String cognome, String indirizzo, int id_medico) {
        super(codiceFiscale, nome, cognome, indirizzo);
        this.id_medico = id_medico;

    }
}
