package br.com.gew.smartplan.client;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.gew.smartplan.model.Planejamento;

public class PlanejamentoClient {

    private static final String BASE_URL = "http://192.168.0.20:3000/api/planejamento/";
    private RestTemplate rt = new RestTemplate();

    public Planejamento insert(String id, Planejamento planejamento){
        String url = BASE_URL + "insert/" + id;

        try{
            rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            JSONObject json = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            json.put("nome", planejamento.getNome());
            json.put("descricao", planejamento.getDescricao());
            json.put("data_inicio", planejamento.getDataInicio());
            json.put("data_final", planejamento.getDataFinal());

            HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

            return rt.postForObject(url, entity, Planejamento.class);
        }
        catch (RestClientException ex){
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
