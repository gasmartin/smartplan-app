package br.com.gew.smartplan.client;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import br.com.gew.smartplan.model.Professor;

public class ProfessorRestClient {
    private final String BASE_URL = "http://localhost:3000/professores/";
    private RestTemplate restTemplate = new RestTemplate();

    public boolean insertProfessor(String nome, String email, String senha){

        String url = BASE_URL + "cadastrar";

        try{
            Map<String, String> values = new HashMap<>();
            values.put("nome", nome);
            values.put("email", email);
            values.put("senha", senha);

            JSONObject jsonObject = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


            jsonObject.put("nome", nome);
            jsonObject.put("email", email);
            jsonObject.put("senha", senha);

            HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);

            restTemplate.postForEntity(url, entity, null);

            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
