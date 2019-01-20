package androidguide.own.nandita.demoretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    IMyApi iMyApi;
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit=RetrofitClient.getInstance();
        iMyApi=retrofit.create(IMyApi.class);
        fetch();
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }


    void fetch()
    {
        compositeDisposable.add(iMyApi.getModel()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Model>() {
            @Override
            public void accept(Model model) throws Exception {
                Toast.makeText(getApplicationContext(),model.key+model.message+model.status,Toast.LENGTH_LONG ).show();
            }
        }));
    }

}
