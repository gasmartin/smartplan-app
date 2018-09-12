package br.com.gew.smartplan.client;

import android.content.SharedPreferences;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import br.com.gew.smartplan.activities.MainActivity;
import br.com.gew.smartplan.model.Professor;

public class ProfessorRestClient {
    private final String BASE_URL = "http://192.168.0.9:3000/api/professor/";
    private RestTemplate restTemplate = new RestTemplate();
    private Professor professor;

    public boolean insertProfessor(String nome, String email, String senha){
        String url = BASE_URL + "insert";
        try{

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

    public Professor executarLogin(String email, String senha){
        //MODIFICAR PARA A APRESENTAÇÃO
        String url = BASE_URL + "executar_login/" + email + "/" + senha;
        professor = Professor.getInstance();

        try{
            professor = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Professor>() {}).getBody();
        }
        catch (RestClientException e){
            e.printStackTrace();
            return null;
        }

        return professor;
    }
}
