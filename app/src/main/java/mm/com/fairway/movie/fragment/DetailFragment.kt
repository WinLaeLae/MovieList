package mm.com.fairway.movie.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import mm.com.fairway.movie.R
import mm.com.fairway.movie.adapter.HomeAdapter
import mm.com.fairway.movie.model.Movie
import mm.com.fairway.movie.model.detailMovie.MovieID
import mm.com.fairway.movie.viewModel.DetailViewModel


class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
var detailViewModel = DetailViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dataItem = arguments?.let {
        DetailFragmentArgs.fromBundle(it)
        }
        var id = dataItem!!.movieID
        Log.d("Id>>",id.toString())

        detailViewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.setLoadResult(id)
        observeDetailViewModel()
    }

    fun observeDetailViewModel(){
        detailViewModel.getLoadResult().observe(viewLifecycleOwner, Observer<MovieID>{ movieId -> // will get arryalist so no need to update
            Log.d("detail",movieId.toString())
            movieTitle_txt.text=movieId.title
            date_txt.text=movieId.release_date
            overView_txt.text= movieId.overview
            Picasso.get().load(HomeAdapter.imageUrl + movieId.poster_path).into(detail_img)
        })

    }
    }

