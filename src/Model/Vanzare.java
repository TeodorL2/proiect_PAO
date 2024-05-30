package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class Vanzare extends Tranzactie {

    public Vanzare(){}
    public Vanzare(String id, LocalDateTime data, Double suma, String nr_card) {
        super(id, data, suma, nr_card);
    }

    public Vanzare(String id, LocalDateTime data, Double suma) {
        this(id, data, suma, null);
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
