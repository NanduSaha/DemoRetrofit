package androidguide.own.nandita.demoretrofit;





import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IMyApi {
    @GET("uapi")
    Observable<Model> getModel();
}
