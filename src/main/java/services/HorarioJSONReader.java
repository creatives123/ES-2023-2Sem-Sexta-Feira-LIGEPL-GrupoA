package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

import Models.Horario;

public class HorarioJSONReader {
     public static List<Horario> readJSON(String filePath) {
        try{
            return new ObjectMapper().readValue(new File(filePath), new TypeReference<List<Horario>>(){});
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

     }


}