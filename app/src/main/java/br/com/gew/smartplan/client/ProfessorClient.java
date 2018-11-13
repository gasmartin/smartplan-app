package br.com.gew.smartplan.client;

import br.com.gew.smartplan.model.Professor;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProfessorClient {

    String BASE_URL = "http://192.168.0.20:3000/api/professor/";

    @GET("executar_login/{username}/{senha}")
    Call<Professor> executarLogin(@Path("username") String username, @Path("senha") String senha);

    @POST("insert")
    Call<Professor> cadastrar(@Body Professor professor);
}
