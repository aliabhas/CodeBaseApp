package aliabbas.com.codebaseapp.di_dependencies.app_dependencies;

import androidx.lifecycle.ViewModelProvider;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import aliabbas.com.codebaseapp.app_service_call.Api;
import aliabbas.com.codebaseapp.app_service_call.AppServiceBuilder;
import aliabbas.com.codebaseapp.models.pixabaymodels.PixaBayDataSource;
import aliabbas.com.codebaseapp.viewmodels.pixabay_home.ViewModelFactory;
import aliabbas.com.codebaseapp.utils.Constants;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created By Ali Abbas on on 12,January,2020
 * This Class is used for
 */
@Module
public class ApiServiceModule {

    @Provides
    OkHttpClient getRequestHeader() {

        final String MULTIPART_FORM = "multipart/form-dat";
        final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.retryOnConnectionFailure(true);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                response.header("Content-ReportCopy", APPLICATION_JSON_CHARSET_UTF_8);
                response.header("Accept", APPLICATION_JSON_CHARSET_UTF_8);
                response.header("Connection", "close");
                return response;
            }
        }).addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        return httpClient.build();
    }


    @Provides
    Api provideRetrofit(OkHttpClient client) {

        return new Retrofit.Builder()
                .baseUrl(Constants.Companion.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build().create(Api.class);

    }

    @Provides
    AppServiceBuilder getRepository(Api apiCallInterface) {
        return new AppServiceBuilder(apiCallInterface);
    }

    @Provides
    @Singleton
    public ViewModelProvider.Factory getViewModelFactory(
            AppServiceBuilder myRepository, PixaBayDataSource pixaBayDataSource
    ) {
        return new ViewModelFactory(myRepository, pixaBayDataSource);
    }

}
