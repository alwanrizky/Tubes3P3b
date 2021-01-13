package com.example.petakecelakaansepeda.network;

import com.example.petakecelakaansepeda.MainActivity;

public class NetworkModule {
    private final MainActivity mApplication;

    public NetworkModule(MainActivity app) {
        this.mApplication = app;
    }

    @Provides
    @PerApp
    GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create(
                new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create()).create());
    }

    @Provides
    @PerApp
    Retrofit retrofit(GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BikeWiseClient.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @PerApp
    BikeWiseClient bikeWiseClient(Retrofit retrofit) {
        return new BikeWiseClient(retrofit);
    }
}
