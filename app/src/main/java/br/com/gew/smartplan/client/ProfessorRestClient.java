package br.com.gew.smartplan.client;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.gew.smartplan.model.Professor;

public class ProfessorRestClient {
    private final String BASE_URL = "http://localhost:3000/professores/";
    private RestTemplate restTemplate = new RestTemplate();

    public Professor insertProfessor(String nome, String email, String senha){

        Professor professor = Professor.getInstance();
        professor.setNome(nome);
        professor.setEmail(email);
        professor.setSenha(senha);

        try{
            //restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            return restTemplate.postForObject(BASE_URL + "cadastrar", professor, Professor.class);
        }
        catch (Exception ex){
            return null;
        }
    }
}
