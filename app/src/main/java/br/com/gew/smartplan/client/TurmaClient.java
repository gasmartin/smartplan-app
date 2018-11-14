package br.com.gew.smartplan.client;

import java.util.List;

import br.com.gew.smartplan.model.Turma;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TurmaClient {

    @GET("professor/{id}/turmas")
    Call<List<Turma>> returnListByProfessorId(@Path("id") Long id);

    @POST("turma/insert/{id}")
    Call<Turma> insertTurma(@Path("id") Long professor_id, @Body Turma planejamento);

    @PUT("turma/{id}")
    Call<Turma> updatePlanejamento(@Path("id") Long id);

    //CUIDADO
    @DELETE("turma/{id}")
    Call<Turma> deletePlanejamento(@Path("id") Long id);
}
