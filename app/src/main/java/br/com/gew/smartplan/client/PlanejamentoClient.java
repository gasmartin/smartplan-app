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

import br.com.gew.smartplan.helpers.Utils;
import br.com.gew.smartplan.model.Planejamento;

public class PlanejamentoClient {

    private RestTemplate rt = new RestTemplate();

    public Planejamento insert(String id, Planejamento planejamento){
        String url = Utils.BASE_URL + "planejamento/insert/" + id;

        try{
            rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            JSONObject json = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            json.put("nome", planejamento.getNome());
            json.put("descricao", planejamento.getDescricao());
            json.put("dataInicio", planejamento.getDataInicio());
            json.put("dataFinal", planejamento.getDataFinal());

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

    public void update(Planejamento planejamento){
        String url = Utils.BASE_URL + "planejamento/" + planejamento.getId();

        try{
            rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            JSONObject json = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            json.put("nome", planejamento.getNome());
            json.put("descricao", planejamento.getDescricao());
            json.put("dataInicio", planejamento.getDataInicio());
            json.put("dataFinal", planejamento.getDataFinal());

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
        String url = Utils.BASE_URL + "planejamento/" + id;
        rt.delete(url);
    }

    public Integer count(String id) {
        String url = Utils.BASE_URL + "professor/" + id + "/planejamentos/count";
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
