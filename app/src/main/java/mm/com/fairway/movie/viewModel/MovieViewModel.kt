package mm.com.fairway.movie.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mm.com.fairway.movie.api.ApiClient
import mm.com.fairway.movie.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {
    private var result:MutableLiveData<Movie> = MutableLiveData() // to return response body
    private  var loading : MutableLiveData<Boolean> = MutableLiveData()
    fun setLoadResult(){
        var apiClient= ApiClient()
        var apiCall= apiClient.getMovieList(ApiClient.api_Key,"en_US",1)
    apiCall.enqueue(object : Callback<Movie>{
        override fun onFailure(call: Call<Movie>, t: Throwable) {
            Log.d("Tag>>>>>",t.toString())
        }

        override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
            result.value= response.body()
        }
    })

    }
    fun getLoadResult(): LiveData<Movie> = result
}