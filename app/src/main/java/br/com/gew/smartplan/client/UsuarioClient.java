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
import br.com.gew.smartplan.model.Usuario;

public class UsuarioClient {

    private RestTemplate rt = new RestTemplate();

    public Usuario login(String username, String password){
        String url = Utils.BASE_URL + "usuario/executar_login/" + username + "/" + password;
        Usuario usuario = null;
        try{
            usuario = rt.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<Usuario>() {}
            ).getBody();
        }
        catch (RestClientException ex){
            ex.printStackTrace();
        }

        return usuario;
    }

    public Usuario insert(String id, Usuario usuario){

        String url = Utils.BASE_URL + "usuario/insert/" + id;

        try{
            rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            JSONObject json = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            json.put("username", usuario.getUsername());
            json.put("password", usuario.getPassword());

            HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

            return rt.postForObject(url, entity, Usuario.class);
        }
        catch (RestClientException ex){
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
