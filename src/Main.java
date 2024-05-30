import Exceptions.EroareCitireDoubleInvalid;
import Exceptions.EroareCitireNumarInvalid;
import Model.*;
import Services.*;
import Utilities.Sequences;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

enum Stare
{
    Exiting,
    ReadOption,
    InsertAdresaFromStdIn,
    InsertCategorieFromStdIn,
    InsertClientFromStdIn,
    InsertComandaFromStdIn,
    InsertDepozitFromStdIn,
    InsertLivrareFromStdIn,
    InsertMagazinFromStdIn,
    InsertProdusDisponibilFromStdIn,
    InsertTipProdusFromStdIn,
    InsertVanzareFromStdIn,


    ProduseDupaCategorie,
    ProduseDupaDenumireCategorie,
    ProduseDupaTip,
    Categorii,
    TipuriProduse,
    ProduseDisponibile,
    Clienti,
    Magazine,
    MagazineUndeSeGasesteUnProdus,

    UpdateProdusDisponibilFromStdIn,
    UpdatePretProduseDisponibileDinCategorie,
    UpdateClientFromStdIn,

    DeleteClientById,
    DeleteTipProdusById,
}

public class Main {
    private static Stare stare;

    static{
        stare = Stare.ReadOption;
    }

    protected static Double readDouble(Scanner scanner)
    {
        String line = scanner.nextLine();
        if(line.isBlank())
            return null;
        try {
            return Double.valueOf(line);
        }
        catch(NumberFormatException e)
        {
            throw new EroareCitireDoubleInvalid("numarul introdus nu este valid");
        }
    }
    static Stare optionParse(String line)
    {
        switch (line)
        {
            case "InsertAdresaFromStdIn": return Stare.InsertAdresaFromStdIn;
            case "InsertCategorieFromStdIn": return Stare.InsertCategorieFromStdIn;
            case "InsertClientFromStdIn": return Stare.InsertClientFromStdIn;
            case "InsertComandaFromStdIn": return Stare.InsertComandaFromStdIn;
            case "InsertDepozitFromStdIn": return Stare.InsertDepozitFromStdIn;
            case "InsertLivrareFromStdIn": return Stare.InsertLivrareFromStdIn;
            case "InsertMagazinFromStdIn": return Stare.InsertMagazinFromStdIn;
            case "InsertProdusDisponibilFromStdIn": return Stare.InsertProdusDisponibilFromStdIn;
            case "InsertTipProdusFromStdIn": return Stare.InsertTipProdusFromStdIn;
            case "InsertVanzareFromStdIn": return Stare.InsertVanzareFromStdIn;

            case "ProduseDupaCategorie": return Stare.ProduseDupaCategorie;
            case "ProduseDupaDenumireCategorie": return Stare.ProduseDupaDenumireCategorie;
            case "ProduseDupaTip": return Stare.ProduseDupaTip;
            case "Categorii": return Stare.Categorii;
            case "TipuriProduse": return Stare.TipuriProduse;
            case "ProduseDisponibile": return Stare.ProduseDisponibile;
            case "Clienti": return Stare.Clienti;
            case "Magazine": return Stare.Magazine;
            case "MagazineUndeSeGasesteUnProdus": return Stare.MagazineUndeSeGasesteUnProdus;

            case "UpdateProdusDisponibilFromStdIn": return Stare.UpdateProdusDisponibilFromStdIn;
            case "UpdatePretProduseDisponibileDinCategorie": return Stare.UpdatePretProduseDisponibileDinCategorie;
            case "UpdateClientFromStdIn": return Stare.UpdateClientFromStdIn;

            case "DeleteClientById": return Stare.DeleteClientById;
            case "DeleteTipProdusById": return Stare.DeleteTipProdusById;

            case "exit": return Stare.Exiting;
            default: return Stare.ReadOption;
        }

    }


    public static void main(String[] args) {
        Scanner f = new Scanner(System.in);

        while(!stare.equals(Stare.Exiting))
        {
            try{
                switch (stare){
                    case Stare.ReadOption: {
                        System.out.println("Optiune:");
                        String line = f.nextLine().strip();
                        stare = optionParse(line);
                        break;
                    }
                    case Stare.InsertAdresaFromStdIn:{
//                        try{
                            IInsertService ins = InsertService.getInstance();
                            ins.InsertAdresaFromStdIn();
//                        }catch (Exception e)
//                        {
//                            e.printStackTrace();
//                        }
                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.InsertCategorieFromStdIn:{
                        try{
                            IInsertService ins = InsertService.getInstance();
                            ins.InsertCategorieFromStdIn();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.InsertClientFromStdIn:{
                        try{
                            IInsertService ins = InsertService.getInstance();
                            ins.InsertClientFromStdIn();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.InsertComandaFromStdIn:{
                        try{
                            IInsertService ins = InsertService.getInstance();
                            ins.InsertComandaFromStdIn();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.InsertDepozitFromStdIn:{
                        try{
                            IInsertService ins = InsertService.getInstance();
                            ins.InsertDepozitFromStdIn();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.InsertLivrareFromStdIn:{
                        try{
                            IInsertService ins = InsertService.getInstance();
                            ins.InsertLivrareFromStdIn();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.InsertMagazinFromStdIn:{
                        try{
                            IInsertService ins = InsertService.getInstance();
                            ins.InsertMagazinFromStdIn();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.InsertProdusDisponibilFromStdIn:{
                        try{
                            IInsertService ins = InsertService.getInstance();
                            ins.InsertProdusDisponibilFromStdIn();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.InsertTipProdusFromStdIn:{
                        try{
                            IInsertService ins = InsertService.getInstance();
                            ins.InsertTipProdusFromStdIn();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.InsertVanzareFromStdIn:{
                        try{
                            IInsertService ins = InsertService.getInstance();
                            ins.InsertVanzareFromStdIn();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.ProduseDupaCategorie:
                    {
                        System.out.println("Id Categorie:");
                        String id_categorie = f.nextLine().strip();
                        List<ProdusDisponibil> lista = new ArrayList<>();
                        try{
                            IQueryService ins = QueryService.getInstance();
                            lista = ins.ProduseDupaCategorie(id_categorie);

                            if(lista.isEmpty())
                                System.out.println("Nu a fost gasit niciun produs din categoria specificata.");
                            else
                                for(ProdusDisponibil elem : lista)
                                    System.out.println(elem);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.ProduseDupaDenumireCategorie:
                    {
                        System.out.println("Denumire Categorie:");
                        String categorie = f.nextLine().strip();
                        List<ProdusDisponibil> lista = new ArrayList<>();
                        try{
                            IQueryService ins = QueryService.getInstance();
                            lista = ins.ProduseDupaDenumireCategorie(categorie);

                            if(lista.isEmpty())
                                System.out.println("Nu a fost gasit niciun produs din categoria specificata.");
                            else
                                for(ProdusDisponibil elem : lista)
                                    System.out.println(elem);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.ProduseDupaTip:
                    {
                        System.out.println("Id Tip Produs:");
                        String id_tip_produs = f.nextLine().strip();
                        List<ProdusDisponibil> lista = new ArrayList<>();
                        try{
                            IQueryService ins = QueryService.getInstance();
                            lista = ins.ProduseDupaTip(id_tip_produs);

                            if(lista.isEmpty())
                                System.out.println("Nu a fost gasit niciun produs din tipul specificat.");
                            else
                                for(ProdusDisponibil elem : lista)
                                    System.out.println(elem);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        stare = Stare.ReadOption;
                        break;
                    }

                    case Stare.TipuriProduse:
                    {
                        List<TipProdus> lista = new ArrayList<>();
                        try{
                            IQueryService ins = QueryService.getInstance();
                            lista = ins.TipuriProduse();

                            if(lista.isEmpty())
                                System.out.println("Nu a fost gasita niciun tip de produs.");
                            else
                                for(TipProdus elem : lista)
                                    System.out.println(elem);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.Categorii:
                    {
                        List<Categorie> lista = new ArrayList<>();
                        try{
                            IQueryService ins = QueryService.getInstance();
                            lista = ins.Categorii();

                            if(lista.isEmpty())
                                System.out.println("Nu a fost gasita nicio categorie.");
                            else
                                for(Categorie elem : lista)
                                    System.out.println(elem);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.ProduseDisponibile:
                    {
                        List<ProdusDisponibil> lista = new ArrayList<>();
                        try{
                            IQueryService ins = QueryService.getInstance();
                            lista = ins.ProduseDisponibile();

                            if(lista.isEmpty())
                                System.out.println("Nu a fost gasita niciun produs disponibil.");
                            else
                                for(ProdusDisponibil elem : lista)
                                    System.out.println(elem);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.Clienti:
                    {
                        List<Client> lista = new ArrayList<>();
                        try{
                            IQueryService ins = QueryService.getInstance();
                            lista = ins.Clienti();

                            if(lista.isEmpty())
                                System.out.println("Nu a fost gasita niciun client.");
                            else
                                for(Client elem : lista)
                                    System.out.println(elem);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.Magazine:
                    {
                        List<Magazin> lista = new ArrayList<>();
                        try{
                            IQueryService ins = QueryService.getInstance();
                            lista = ins.Magazine();

                            if(lista.isEmpty())
                                System.out.println("Nu a fost gasita niciun magazin.");
                            else
                                for(Magazin elem : lista)
                                    System.out.println(elem);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.MagazineUndeSeGasesteUnProdus:
                    {
                        System.out.println("Id Produs:");
                        String id_produs = f.nextLine().strip();
                        List<Magazin> lista = new ArrayList<>();
                        try{
                            IQueryService ins = QueryService.getInstance();
                            lista = ins.MagazineUndeSeGasesteUnProdus(id_produs);

                            if(lista.isEmpty())
                                System.out.println("Nu a fost gasit niciun magazin care sa aiba produsul specificat.");
                            else
                                for(Magazin elem : lista)
                                    System.out.println(elem);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        stare = Stare.ReadOption;
                        break;
                    }
                    case Stare.UpdateProdusDisponibilFromStdIn:
                    {
                        try{
                            IUpdateService updateService = UpdateService.getInstance();
                            Integer nrRows = updateService.UpdateProdusDisponibilFromStdIn();

                            if(nrRows == null)
                                System.out.println("Actualizarea nu a avut succes.");
                            else
                                System.out.println("Actualizare cu success: " + nrRows.toString() + " randuri actualizate.");
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        stare = Stare.ReadOption;
                        break;
                    }

                    case Stare.UpdatePretProduseDisponibileDinCategorie:
                    {
                        System.out.println("Id Categorie:");
                        String id_categorie = f.nextLine().strip();

                        System.out.println("Procent marire pret (cu 'p'%):");
                        Double procent = readDouble(f);
                        procent = 1 + procent/100;

                        try{
                            IUpdateService updateService = UpdateService.getInstance();
                            Integer nrRows = updateService.UpdatePretProduseDisponibileDinCategorie(id_categorie, procent);

                            if(nrRows == null)
                                System.out.println("Actualizarea nu a avut succes.");
                            else
                                System.out.println("Actualizare cu success: " + nrRows.toString() + " randuri actualizate.");
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        stare = Stare.ReadOption;
                        break;
                    }

                    case Stare.UpdateClientFromStdIn:
                    {
                        try{
                            IUpdateService updateService = UpdateService.getInstance();
                            Integer nrRows = updateService.UpdateClientFromStdIn();

                            if(nrRows == null)
                                System.out.println("Actualizarea nu a avut succes.");
                            else
                                System.out.println("Actualizare cu success: " + nrRows.toString() + " randuri actualizate.");
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        stare = Stare.ReadOption;
                        break;
                    }

                    case Stare.DeleteClientById:
                    {
                        System.out.println("Id client:");
                        String id_client = f.nextLine().strip();

                        IDeleteService service = DeleteService.getInstance();
                        Integer nrRows = service.DeleteClientById(id_client);

                        if(nrRows != null)
                            System.out.println(nrRows.toString() + " randuri sterse.");

                        stare = Stare.ReadOption;
                        break;
                    }

                    case Stare.DeleteTipProdusById:
                    {
                        System.out.println("Id tip produs:");
                        String id_tip_produs = f.nextLine().strip();

                        IDeleteService service = DeleteService.getInstance();
                        Integer nrRows = service.DeleteTipProdusById(id_tip_produs);

                        if(nrRows != null)
                            System.out.println(nrRows.toString() + " randuri sterse.");

                        stare = Stare.ReadOption;
                        break;
                    }
                }


            }
            catch (EroareCitireNumarInvalid e)
            {
                System.out.println(e.getMessage());
                stare = Stare.ReadOption;
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                stare = Stare.ReadOption;
            }
        }


        Sequences.saveToFile();
    }
}
