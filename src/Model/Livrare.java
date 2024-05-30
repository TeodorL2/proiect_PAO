package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class Livrare extends Entitate {
    private LocalDateTime data;
    private Double distanta_parcursa_km;

    public Livrare(){}
    public Livrare(String id, LocalDateTime data, Double distanta_parcursa_km) {
        super(id);
        this.data = data;
        this.distanta_parcursa_km = distanta_parcursa_km;
    }

    @Override
    public String toString()
    {
        return super.toString() + "   " + data + "   " + distanta_parcursa_km + " km";
    }
}
