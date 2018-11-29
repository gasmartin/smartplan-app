package br.com.gew.smartplan.client;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import br.com.gew.smartplan.model.Turma;

public class TurmaClient {

    private static final String BASE_URL = "http://192.168.0.20:3000/api/turma/";
    private RestTemplate rt = new RestTemplate();

    public Turma insert(String id, Turma turma){
        String url = BASE_URL + "insert/" + id;

        try{
            rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            JSONObject json = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            json.put("nome", turma.getNome());
            json.put("sala", turma.getSala());

            HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

            return rt.postForObject(url, entity, Turma.class);
        }
        catch (RestClientException ex){
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
