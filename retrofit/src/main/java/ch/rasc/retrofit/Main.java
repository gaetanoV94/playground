package ch.rasc.retrofit;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Main {

	public static void main(String[] args) throws IOException {
		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://httpbin.org")
				.addConverterFactory(JacksonConverterFactory.create()).build();

		HttpBinService service = retrofit.create(HttpBinService.class);
		Call<Ip> ip = service.getIp();
		ip.enqueue(new Callback<Ip>() {

			@Override
			public void onResponse(Call<Ip> call, Response<Ip> response) {
				System.out.println(response.body().origin());
			}

			@Override
			public void onFailure(Call<Ip> call, Throwable t) {
				t.printStackTrace();
			}
		});

		Response<Ip> response = service.getIp().execute();
		System.out.println(response.body().origin());

	}

}
