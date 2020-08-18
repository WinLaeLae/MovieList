package mm.com.fairway.movie.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import mm.com.fairway.movie.R
import mm.com.fairway.movie.adapter.HomeAdapter
import mm.com.fairway.movie.model.Movie
import mm.com.fairway.movie.model.Result
import mm.com.fairway.movie.viewModel.MovieViewModel


class HomeFragment : Fragment(), HomeAdapter.ClickListener {
    var movieViewModel = MovieViewModel()
    lateinit var homeAdapter: HomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()
        movieViewModel.setLoadResult()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        homeAdapter = HomeAdapter()

        recylerView_Home_Movie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
        homeAdapter.setOnClickListener(this)
        observe_ViewModel()
    }

    private fun observe_ViewModel() {
        // var movieList: ArrayList<Movie>

        movieViewModel.getLoadResult().observe(viewLifecycleOwner, Observer<Movie> { movie ->
            homeAdapter.updateResult(
                movie.results as ArrayList<Result>
            )
            Log.d("New>>", movie.results.get(0).toString())
        })
    }

    override fun OnClick(result: Result) {
        Log.d("Detail>>>Tag>>>>", result.id.toString())
        var action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(result.id)
        findNavController().navigate(action)
    }
}