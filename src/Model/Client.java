package Model;

import Exceptions.ClientInvalid;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Client extends Entitate{
    private String cnp;
    private String nume;
    private String prenume;
    private String nr_tel;
    private String mail;

    public Client(){}

    private Client(String id, String cnp, String nume, String prenume, String nr_tel, String mail) {
        super(id);
        this.cnp = cnp;
        this.nume = nume;
        this.prenume = prenume;
        this.nr_tel = nr_tel;
        this.mail = mail;
    }

    public static Client getClient(String id, String cnp, String nume, String prenume, String nr_tel, String mail){
        if(nr_tel == null && mail == null)
            throw new ClientInvalid("Nu au fost oferite date de contact");
        return new Client(id, cnp, nume, prenume, nr_tel, mail);
    }

    @Override
    public String toString() {
        return super.toString() + "   " + cnp + "   " + nume + "   " + prenume +
                "   " + nr_tel + "   " + mail;
    }
}
