package br.com.gew.smartplan.client;

import java.util.List;

import br.com.gew.smartplan.model.Aluno;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Gabriel San Martin on 14/11/2018.
 */

public interface AlunoClient {

    @GET("professor/{id}/alunos")
    Call<List<Aluno>> returnListByProfessorId(@Path("id") Long id);
}
