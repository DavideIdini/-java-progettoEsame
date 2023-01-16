import java.util.List;
import java.util.Scanner;

public class Medico extends Persona{

    private int id_medico;
    private String password;


    public Medico(String codiceFiscale, String nome, String cognome, String indirizzo, int id_medico,String password) {
        super(codiceFiscale, nome, cognome, indirizzo);
        this.id_medico = id_medico;
        this.password = password;

    }

    public static Medico registrazioneMedico() {
        String cf = Appuntamento.inserimentoCFPaziente();
        String nome = Paziente.inserimentoNome();
        String cognome = Paziente.inserimentoCognome();
        String indirizzo = Paziente.inserimentoIndirizzo();
        int id = inserimentoId();
        String password= inserimentoPassword();
        Medico medico = new Medico(cf, nome, cognome, indirizzo, id, password);
        System.out.println(medico);

        return medico;
    }

    public String getPassword() {
        return password;
    }

    private static String inserimentoPassword() {
        Scanner tastiera = new Scanner(System.in);
        while(true){
            try{
                System.out.println("inserire password");
                String password = tastiera.nextLine();
                if(password.isEmpty())
                    throw new Exception ("non hai inserito nulla.");
                if(password.length()<5)
                    throw new Exception("password troppo corta.");
                return password;
            }catch(Exception e){
                System.out.println(e.getMessage()+" Reinserire");
            }
        }
    }

    private static int inserimentoId() {
        Scanner tastiera = new Scanner(System.in);

        int id=0;
        while(true){
            try {
                System.out.println("inserisci id medico");
                String intero = tastiera.nextLine();
                if(intero.isEmpty() || intero.length()>2 )
                    throw new Exception("id non valido. Deve avere massimo due numeri");
                if(Character.isAlphabetic(intero.charAt(0)))
                    throw new Exception("id non valido. L'id non puo contenere caratteri alfabetici");
                if(Character.isAlphabetic(intero.charAt(1)))
                    throw new Exception("id non valido. L'id non puo contenere caratteri alfabetici");
                return id=Integer.parseInt(intero);
            }catch (Exception e){
                System.out.println(e.getMessage()+" reinseriscilo ");
            }
        }

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
                "id_medico=" + id_medico + password+
                '}';
    }
    public static Medico loginMedico(List<Medico> medici) {
        if(medici.isEmpty()){
            System.out.println("Ancora nessun medico registrato");
            return null;
        }
        boolean flag = true;
        while(flag){
            try {
                Scanner tastiera = new Scanner(System.in);
                System.out.println("Inserisci id medico");
                String id = tastiera.nextLine();
                if(id.isEmpty() || id.length()>2 )
                    throw new Exception("id non valido. Deve avere massimo due numeri");
                if(Character.isAlphabetic(id.charAt(0)))
                    throw new Exception("id non valido. L'id non puo contenere caratteri alfabetici");
                if(Character.isAlphabetic(id.charAt(1)))
                    throw new Exception("id non valido. L'id non puo contenere caratteri alfabetici");

                System.out.println("Inserisci password");
                String pass = tastiera.nextLine();

                for (Medico a : medici){
                    if ( a.getId_medico() == Integer.parseInt(id) && a.getPassword().equals(pass)){
                        System.out.println("login effettuato con successo");
                        return a;

                    } else
                        throw new Exception(" Id o Password sbagliata ");
                }

            }catch(Exception e){
                Scanner tastiera = new Scanner(System.in);
                System.out.println(e.getMessage()+" digita si se vuoi riprovare a effettuare il login");
                String risposta = tastiera.nextLine();
                if(!risposta.toUpperCase().equals("SI"))
                    flag = false;

            }

        }
        return null;
    }

}
