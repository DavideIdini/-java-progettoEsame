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
        Scanner tastiera = new Scanner(System.in);
        System.out.println("Inserisci id medico");
        int id = tastiera.nextInt();
        System.out.println("Inserisci password");
        String prova = tastiera.nextLine();
        String pass = tastiera.nextLine();
        System.out.println("prova"+prova);
        System.out.println("pass"+pass);
        for(Medico a : medici){
            System.out.println("stampo un medico"+a.toString());
            if(a.getId_medico()==id && a.getPassword().equals(pass))
                return a;
        }
        return null;
    }
   // public static Medico login(Scanner tastiera) {
   //     System.out.println("inserisci codice fiscale medico\n"+"inserisci nome medico\n"+"inserisci cognome medico\n"+"inserisci indirizzo medico\n"+"inserisci id medico");
    //    String prova = tastiera.nextLine();
     //   Medico medico = new Medico(tastiera.nextLine(),tastiera.nextLine(),tastiera.nextLine(),tastiera.nextLine(),tastiera.nextInt());
    //    return medico;

   // }
}
