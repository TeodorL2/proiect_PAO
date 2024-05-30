package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class IstoricComanda extends Tranzactie{
    private String id_client;
    private String id_adresa;

    public IstoricComanda(){}

    public IstoricComanda(String id, LocalDateTime data, Double suma, String nr_card, String id_client, String id_adresa) {
        super(id, data, suma, nr_card);
        this.id_client = id_client;
        this.id_adresa = id_adresa;
    }

    public IstoricComanda(String id, LocalDateTime data, Double suma, String id_client, String id_adresa) {
        this(id, data, suma, null, id_client, id_adresa);
    }

    @Override
    public String toString()
    {
        return super.toString() + "   " + id_client + "   " + id_adresa;
    }
}
