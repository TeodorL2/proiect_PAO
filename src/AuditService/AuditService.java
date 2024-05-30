package AuditService;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditService {
    private String fileName;

    public AuditService(String fileName)
    {
        this.fileName = fileName;
    }

    public void log(String numeActiune)
    {
        try(FileWriter f = new FileWriter(fileName, true);) {
            f.write(numeActiune + ", " + LocalDateTime.now().toString() + "\n");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Nu exista fisierul de audit: " + e.toString());
        }
        catch(IOException e)
        {
            System.out.println("Eroare in timpul scrierii in fisierul de audit: " + e.toString());
        }
    }
}
