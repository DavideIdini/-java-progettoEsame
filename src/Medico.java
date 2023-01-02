import java.util.Scanner;

public class Medico extends Persona{

    private int id_medico;


    public Medico(String codiceFiscale, String nome, String cognome, String indirizzo, int id_medico) {
        super(codiceFiscale, nome, cognome, indirizzo);
        this.id_medico = id_medico;

    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id_medico=" + id_medico +
                '}';
    }
    public static Medico login(Scanner tastiera) {
        System.out.println("inserisci codice fiscale medico\n"+"inserisci nome medico\n"+"inserisci cognome medico\n"+"inserisci indirizzo medico\n"+"inserisci id medico");
        String prova = tastiera.nextLine();
        Medico medico = new Medico(tastiera.nextLine(),tastiera.nextLine(),tastiera.nextLine(),tastiera.nextLine(),tastiera.nextInt());
        return medico;

    }
}
