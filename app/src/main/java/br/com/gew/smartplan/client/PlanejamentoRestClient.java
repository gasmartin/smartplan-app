package br.com.gew.smartplan.client;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import br.com.gew.smartplan.model.Planejamento;

public class PlanejamentoRestClient {

    private final String BASE_URL = "http://192.168.0.20:3000/api";
    private RestTemplate restTemplate = new RestTemplate();

    private Planejamento planejamento;

    public List<Planejamento> getAllPlanejamentosByProfessorId(Long professorId){
        String url = BASE_URL + "/professor/" + professorId + "/planejamentos";
        List<Planejamento> planejamentoList = null;
        try{
            planejamentoList = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Planejamento>>(){}).getBody();
        }
        catch (RestClientException ex) {
            ex.printStackTrace();
        }
        return planejamentoList;
    }

    //IMPLEMENTAR DATAS
    public Boolean insertPlanejamento(Integer cor, String nome, String descricao, Long professorId){
        String url = BASE_URL + "/planejamento/insert/" + professorId;
        try{

            JSONObject jsonObject = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            jsonObject.put("cor", cor);
            jsonObject.put("nome", nome);
            jsonObject.put("descricao", descricao);
            jsonObject.put("dataInicio", null);
            jsonObject.put("dataFinal", null);

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
