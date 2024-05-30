package Services;

import Model.*;

public interface IUpdateService {
    Integer UpdateProdusDisponibilFromStdIn();
    Integer UpdatePretProduseDisponibileDinCategorie(String id_categorie, Double scale);
    Integer UpdateClientFromStdIn();
}
