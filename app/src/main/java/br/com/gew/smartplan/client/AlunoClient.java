package br.com.gew.smartplan.client;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Aluno;

public class AlunoClient {

    private RestTemplate rt = new RestTemplate();

    public Aluno insertAluno(Aluno aluno){
        String url = Utils.BASE_URL + "aluno/insert";
        try{
            rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            JSONObject json = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            json.put("nome", aluno.getNome());
            json.put("email", aluno.getEmail());

            HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

            aluno = rt.postForObject(url, entity, Aluno.class);
        }
        catch(RestClientException ex){
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return aluno;
    }

    public boolean update(Long id, Aluno aluno){
        String url = Utils.BASE_URL + "aluno/" + id;
        try{
            JSONObject json = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            json.put("nome", aluno.getNome());
            json.put("email", aluno.getEmail());

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

    public void deleteAluno(Long id){
        String url = Utils.BASE_URL + "aluno/" + id;
        rt.delete(url);
    }
}
