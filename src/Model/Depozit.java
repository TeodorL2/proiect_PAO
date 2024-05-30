package Model;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class Depozit extends Cladire {
    private Integer capacitate;

    public Depozit(){}
    public Depozit(String id, String id_adresa, Integer capacitate) {
        super(id, id_adresa);
        this.capacitate = capacitate;
    }

    @Override
    public String toString()
    {
        return super.toString() + "   " + capacitate;
    }
}
