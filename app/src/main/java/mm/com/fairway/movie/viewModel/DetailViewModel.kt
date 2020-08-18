package mm.com.fairway.movie.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mm.com.fairway.movie.api.ApiClient
import mm.com.fairway.movie.model.detailMovie.MovieID
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel:ViewModel() {
    private var result: MutableLiveData<MovieID> = MutableLiveData()
    fun getLoadResult(): LiveData<MovieID> =result
    fun setLoadResult(id:Int){
   //  var movie_id=id
        Log.d("detail",id.toString())
        var apiClient = ApiClient()
        var  apiCall= apiClient.getDetail(id,ApiClient.api_Key,"en_US")
        apiCall.enqueue(object : Callback<MovieID>{
            override fun onFailure(call: Call<MovieID>, t: Throwable) {
               Log.d("TAG>>>>",t.toString())
            }

            override fun onResponse(call: Call<MovieID>, response: Response<MovieID>) {
                Log.d("TAG>>>>",response.body().toString())
               result.value = response.body()
            }

        })
    }
}