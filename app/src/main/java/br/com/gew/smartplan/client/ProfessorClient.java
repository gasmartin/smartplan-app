package br.com.gew.smartplan.client;

import br.com.gew.smartplan.model.Professor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProfessorClient {

    @GET("professor/executar_login/{username}/{senha}")
    Call<Professor> executarLogin(@Path("username") String username, @Path("senha") String senha);

    @POST("professor/insert")
    Call<Professor> cadastrar(@Body Professor professor);

    @PUT("professor/{id}")
    Call<Professor> atualizarDados(@Path("id") Long id, @Body Professor professor);
}
