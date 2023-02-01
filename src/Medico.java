import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Medico extends Persona implements Serializable {

    private String id_medico;
    final private String Password;


    public Medico(String codiceFiscale, String nome, String cognome, String indirizzo, String id_medico,String password) {
        super(codiceFiscale, nome, cognome, indirizzo);
        this.id_medico = id_medico;
        this.Password = password;

    }

    public static Medico registrazioneMedico(List<Medico> medici) {
        String cf = Appuntamento.inserimentoCF();
        cf= controlloCF(cf, medici);
        String nome = inserimentoNome();
        String cognome = inserimentoCognome();
        String indirizzo = inserimentoIndirizzo();
        String id = inserimentoId(medici);
        String password= inserimentoPassword();


       Medico medico = new Medico(cf, nome, cognome, indirizzo, id, password);


        return medico;
    }

    private static String controlloCF(String cf, List<Medico> medici) {
        for(Medico a: medici){
            if(a.getCodiceFiscale().equals(cf)){
                System.out.println("medico gia registrato con questo codice fiscale, inserirne uno valido per favore");
                cf = Appuntamento.inserimentoCF();
                cf= controlloCF(cf, medici);
            }
        }
        return cf;
    }

    public String getPassword() {
        return Password;
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

    private static String inserimentoId(List<Medico> medici) {

        Scanner tastiera = new Scanner(System.in);
        while(true){
            try{
                System.out.println("inserire id medico");
                String id = tastiera.nextLine();
                if(id.isEmpty())
                    throw new Exception ("non hai inserito nulla.");
                for(Medico a : medici){
                    if(a.getId_medico().equals(id)){
                        throw new Exception("id già in uso, si prega di sceglierne un altro. ");
                    }
                }
                return id;
            }catch(Exception e){
                System.out.println(e.getMessage()+" Reinserire");
            }
        }
    }

    public String getId_medico() {
        return id_medico;
    }

    public void setId_medico(String id_medico) {
        this.id_medico = id_medico;
    }

    @Override
    public String toString() {
        return "\nMedico -->" +
                "nome =" + this.getNome() + "  username " + id_medico+
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
                if(id.isEmpty() )
                    throw new Exception("id vuoto");


                System.out.println("Inserisci password");
                String pass = tastiera.nextLine();
                if( id.isEmpty() )
                    throw new Exception("password vuota");

                for (Medico a : medici){
                    if ( a.getId_medico().equals(id) && a.getPassword().equals(pass)){
                        System.out.println("login effettuato con successo");
                        return a;

                    }

                }
                throw new Exception(" Id o Password sbagliata ");
            }catch(Exception e){
                Scanner tastiera = new Scanner(System.in);
                System.out.println(e.getMessage()+" digita si se vuoi riprovare a effettuare il login");
                String risposta = tastiera.nextLine();
                if(!risposta.equalsIgnoreCase("SI"))
                    flag = false;

            }

        }
        return null;
    }

}
