package Model;

import java.time.LocalDateTime;

public abstract class Tranzactie extends Entitate {
    protected LocalDateTime data;
    protected Double suma;
    protected String nr_card;

    public Tranzactie(){}
    public Tranzactie(String id, LocalDateTime data, Double suma, String nr_card) {
        super(id);
        this.data = data;
        this.suma = suma;
        this.nr_card = nr_card;
    }

    @Override
    public String toString() {
        return id + "   " + data + "   " + suma + "   " + nr_card;
    }
}
