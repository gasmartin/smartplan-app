package br.com.gew.smartplan.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import br.com.gew.smartplan.model.Turma;

public class TurmaRestClient {

    private final String BASE_URL = "http://192.168.0.9:3000/api/professor/";
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
}
