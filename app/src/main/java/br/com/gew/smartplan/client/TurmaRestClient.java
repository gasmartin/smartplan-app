package br.com.gew.smartplan.client;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import br.com.gew.smartplan.model.Turma;

public class TurmaRestClient {

    private final String BASE_URL = "http://192.168.0.20:3000/api";
    private RestTemplate restTemplate = new RestTemplate();
    private Turma turma;

    public List<Turma> getAllTurmasByProfessorId(Long professorId){
        String url = BASE_URL + "/professor/" + professorId + "/turmas";
        List<Turma> turmas = null;
        try{
            turmas = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Turma>>(){}).getBody();
        }
        catch (RestClientException ex) {
            ex.printStackTrace();
        }
        return turmas;
    }

    public Boolean insertTurma(Integer cor, Integer sala, String nome, String descricao, Long professorId){
        String url = BASE_URL + "/turma/insert/" + professorId;
        try{

            JSONObject jsonObject = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            jsonObject.put("cor", cor);
            jsonObject.put("sala", sala);
            jsonObject.put("nome", nome);
            jsonObject.put("descricao", descricao);

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
