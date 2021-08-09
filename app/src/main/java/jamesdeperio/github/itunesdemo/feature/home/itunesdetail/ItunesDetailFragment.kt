package jamesdeperio.github.itunesdemo.feature.home.itunesdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.gson.Gson
import dagger.android.support.DaggerFragment
import jamesdeperio.github.itunesdemo.databinding.FragmentItunesDetailBinding
import jamesdeperio.github.itunesdemo.model.ItunesData

class ItunesDetailFragment: DaggerFragment() {
    private lateinit var binding: FragmentItunesDetailBinding
    private lateinit var data: ItunesData
    companion object {
        @JvmStatic
        fun newInstance(data: ItunesData) = ItunesDetailFragment().apply {
            this.arguments = Bundle().apply {
                putString(ItunesData::class.java.simpleName, Gson().toJson(data))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = Gson().fromJson(arguments?.getString(ItunesData::class.java.simpleName),ItunesData::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentItunesDetailBinding.inflate(inflater, container, false)

        binding.let {
            binding.tvTrackerName.text = data.trackName
            binding.tvPrice.text = "$${data.trackPrice}"
            binding.tvCollection.text = "${data.collectionName}"
            binding.tvArtist.text = "$${data.artistName}"
            binding.tvReleasedate.text = "${data.releaseDate}"
            binding.tvGenre.text = data.primaryGenreName
            binding.tvRating.text = "${data.contentAdvisoryRating}"
            Glide.with(requireContext())
                .load(data.artworkUrl100)
                .into(binding.ivMedia)

        }

        return binding.root
    }
}