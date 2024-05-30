package Services;

import Model.*;
import java.util.List;

public interface IQueryService {
    List<ProdusDisponibil> ProduseDupaCategorie(String idCategorie);
    List<ProdusDisponibil> ProduseDupaDenumireCategorie(String denumire);
    List<ProdusDisponibil> ProduseDupaTip(String idTipProdus);
    List<Categorie> Categorii();
    List<TipProdus> TipuriProduse();
    List<ProdusDisponibil> ProduseDisponibile();
    List<Client> Clienti();
    List<Magazin> Magazine();
    ProdusDisponibil ProdusDisponibilDupaId(String id);
    List<Magazin> MagazineUndeSeGasesteUnProdus(String idProdus);



//    List<Comanda> CeleMaiVechiComenzi(Integer nrComenzi);

}
