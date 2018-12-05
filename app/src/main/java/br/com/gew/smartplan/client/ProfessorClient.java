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

import java.util.List;

import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Planejamento;
import br.com.gew.smartplan.model.Professor;
import br.com.gew.smartplan.model.Turma;

public class ProfessorClient {

    private  RestTemplate rt = new RestTemplate();

    public Professor cadastrar(Professor professor){

        String url = Utils.BASE_URL + "professor/insert";

        try{
            rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            JSONObject json = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            json.put("nome", professor.getNome());
            json.put("email", professor.getEmail());

            HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

            return rt.postForObject(url, entity, Professor.class);
        }
        catch (RestClientException ex){
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean alterarDados(Long id, Professor professor){

        String url = Utils.BASE_URL + "professor/" + id;

        try{
            JSONObject json = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            json.put("nome", professor.getNome());
            json.put("email", professor.getEmail());

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

    public List<Planejamento> getPlanejamentosByProfessorId(Long id){
        String url = Utils.BASE_URL + "professor/" + id + "/planejamentos";
        List<Planejamento> planejamentos = null;
        try {
            planejamentos = rt.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Planejamento>>() {}).getBody();
        }
        catch (RestClientException e){
            e.printStackTrace();
        }
        return planejamentos;
    }

    public List<Turma> getTurmasByProfessorId(Long id){
        String url = Utils.BASE_URL + "professor/" + id + "/turmas";
        List<Turma> turmas = null;
        try{
            turmas = rt.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Turma>>(){}).getBody();
        }
        catch (RestClientException e){
            e.printStackTrace();
        }
        return turmas;
    }

    public Professor getProfessor(Long id){
        String url = Utils.BASE_URL + "professor/" + id;
        Professor professor = null;
        try{
            professor = rt.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<Professor>() {}).getBody();
        }
        catch (RestClientException ex){
            ex.printStackTrace();
        }
        return professor;
    }

    public void deleteProfessor(Long id){
        String url = Utils.BASE_URL + "professor/" + id;
        rt.delete(url);
    }
}
