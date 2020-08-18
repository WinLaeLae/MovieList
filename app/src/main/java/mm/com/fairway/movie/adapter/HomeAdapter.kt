package mm.com.fairway.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.fragment_detail.view.movieTitle_txt
import kotlinx.android.synthetic.main.item_movielist_layout.view.*
import mm.com.fairway.movie.R
import mm.com.fairway.movie.model.Result


class HomeAdapter(var movieList:ArrayList<Result> = ArrayList()) :RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){

    var mClickListener:ClickListener?=null
    companion object {
        var imageUrl = "https://image.tmdb.org/t/p/w500/"
    }
    fun updateResult(resultList:ArrayList<Result>){
        this.movieList=resultList
        notifyDataSetChanged()
    }
    fun setOnClickListener(clickListener:ClickListener){
        this.mClickListener = clickListener
    }
 inner   class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
     lateinit var  result: Result
     init {
         itemView.setOnClickListener(this)
     }
        fun bind(result: Result) {
            this.result= result
            itemView.movieTitle_txt.text = result.title
            itemView.relate_date_txt.text = result.release_date
            Picasso.get().load(imageUrl + result.poster_path).into(itemView.Home_imageView)
        }

        override fun onClick(v: View?) {
            mClickListener?.OnClick(result)
        }
    }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
     return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movielist_layout,parent,false))
   }

   override fun getItemCount(): Int {
    return movieList.size
   }

   override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
      holder.bind(movieList.get(position))
   }

    interface ClickListener{
        fun OnClick(result: Result)

    }
}