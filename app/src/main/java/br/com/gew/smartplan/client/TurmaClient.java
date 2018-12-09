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

import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Aluno;
import br.com.gew.smartplan.model.Turma;

public class TurmaClient {

    private RestTemplate rt = new RestTemplate();

    public Turma insert(String id, Turma turma){
        String url = Utils.BASE_URL + "turma/insert/" + id;

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

    public List<Aluno> getAlunosByTurmaId(Long id){
        String url = Utils.BASE_URL + "turma/" + id + "/alunos";
        List<Aluno> alunos = null;
        try {
            alunos = rt.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Aluno>>() {}).getBody();
        }
        catch (RestClientException ex){
            ex.printStackTrace();
        }
        return alunos;
    }

    public void update(Turma turma){
        String url = Utils.BASE_URL + "turma/" + turma.getId();

        try{
            rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            JSONObject json = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            json.put("nome", turma.getNome());
            json.put("sala", turma.getSala());

            HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

            rt.put(url, entity);
        }
        catch (RestClientException ex){
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id){
        String url = Utils.BASE_URL + "turma/" + id;
        rt.delete(url);
    }

    public Integer count(String id) {
        String url = Utils.BASE_URL + "professor/" + id + "/turmas/count";
        Integer number = 0;
        try{
            number = rt.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<Integer>() {}).getBody();
        }
        catch (RestClientException ex){
            ex.printStackTrace();
        }
        return number;
    }
}
