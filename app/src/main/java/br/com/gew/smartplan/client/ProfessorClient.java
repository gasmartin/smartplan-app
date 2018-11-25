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

import br.com.gew.smartplan.model.Professor;

public class ProfessorClient {

    private final String BASE_URL = "http://192.168.0.20:3000/api/professor/";
    private  RestTemplate rt = new RestTemplate();

    public Professor login(String username, String password){
        String url = BASE_URL + "executar_login/" + username + "/" + password;
        Professor professor = null;
        try{
            professor = rt.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<Professor>() {}
            ).getBody();
        }
        catch (RestClientException ex){
            ex.printStackTrace();
        }

        return professor;
    }

    public boolean cadastrar(Professor professor){

        String url = BASE_URL + "insert";

        try{
            rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            JSONObject json = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            json.put("nome", professor.getNome());
            json.put("email", professor.getEmail());
            json.put("username", professor.getUsername());
            json.put("senha", professor.getSenha());

            HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);
            rt.postForEntity(url, entity, null);

            return true;
        }
        catch (RestClientException ex){
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean alterarDados(Long id, Professor professor){

        String url = BASE_URL + id;

        try{
            JSONObject json = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            json.put("nome", professor.getNome());
            json.put("email", professor.getEmail());
            json.put("username", professor.getUsername());
            json.put("senha", professor.getSenha());

            HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

            rt.put(url, entity);

            return true;
        }
        catch (RestClientException ex){
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
