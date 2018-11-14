package br.com.gew.smartplan.client;

import java.util.List;

import br.com.gew.smartplan.model.Planejamento;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlanejamentoClient {

    @GET("professor/{id}/planejamentos")
    Call<List<Planejamento>> returnListByProfessorId(@Path("id") Long id);

    @POST("planejamento/insert/{id}")
    Call<Planejamento> insertPlanejamento(@Path("id") Long professor_id, @Body Planejamento planejamento);

    @PUT("planejamento/{id}")
    Call<Planejamento> updatePlanejamento(@Path("id") Long id);

    //CUIDADO
    @DELETE("planejamento/{id}")
    Call<Planejamento> deletePlanejamento(@Path("id") Long id);
}
