import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.Horario;

public class HorarioCsvReader {
    public static List<Horario> readCsv(String filePath) {
        List<Horario> horarios = new ArrayList<Horario>();
        BufferedReader br = null;
    
        try{
            br = new BufferedReader(new FileReader(filePath));
            String line;
            boolean firstLine = true;
    
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the header row
                }
    
                String[] fields = line.split(",", -1);
    
                // Remove quotes from fields that are enclosed in them
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].replaceAll("^\"|\"$", "");
                }
    
                Horario horario = new Horario(
                        fields[0],
                        fields[1],
                        fields[2],
                        fields[3],
                        Integer.parseInt(fields[4]),
                        fields[5],
                        fields[6],
                        fields[7],
                        fields[8],
                        fields[9],
                        Integer.parseInt(fields[10])
                );
    
                horarios.add(horario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return horarios;
    }
}