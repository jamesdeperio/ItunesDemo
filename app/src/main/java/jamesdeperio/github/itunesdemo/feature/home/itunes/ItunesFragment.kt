package jamesdeperio.github.itunesdemo.feature.home.itunes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import jamesdeperio.github.itunesdemo.R
import jamesdeperio.github.itunesdemo.databinding.FragmentItunesBinding
import jamesdeperio.github.itunesdemo.feature.MainActivity
import jamesdeperio.github.itunesdemo.feature.home.itunes.list.ItunesContentAdapter
import jamesdeperio.github.itunesdemo.feature.home.itunesdetail.ItunesDetailFragment
import jamesdeperio.github.itunesdemo.util.NetworkUtil
import javax.inject.Inject


class ItunesFragment : DaggerFragment(), Observer<ItunesContentState>,
    SwipeRefreshLayout.OnRefreshListener {
    //region DEPENDENCY INJECTION
    @Inject lateinit var threadBag: CompositeDisposable
    @Inject lateinit var viewModel: ItunesContentViewModel
    @Inject lateinit var adapter: ItunesContentAdapter
    //endregion

    //region VARIABLE
    private lateinit var binding: FragmentItunesBinding
    //endregion

    //region STATIC VARIABLE AND METHOD
    companion object {
        @JvmStatic
        fun newInstance() = ItunesFragment()
    }
    //endregion

    //region LIFECYCLE
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentItunesBinding.inflate(inflater, container, false)

        binding.rvItunes.let {
            it.adapter = this.adapter
            it.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(requireActivity(),this)
        threadBag.add(viewModel.loadContent())
    }

    override fun onDestroy() {
        super.onDestroy()
        threadBag.dispose()
    }
    //endregion

    //region EVENT STATE
    override fun onChanged(state: ItunesContentState?) {
        Log.d("ItunesContentState:"," $state")
        when(state) {
            is ItunesContentState.OnLoadItem -> {
                if (state.isFromCache) {
                    Toast.makeText(activity,"Data from cache...",Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(activity,"Data from api...",Toast.LENGTH_LONG).show()
                }
                adapter.contents.clear()
                adapter.contents.addAll(state.contents)
                adapter.notifyDataSetChanged()
            }
            is ItunesContentState.OnFailedToLoadItem -> {
                if (NetworkUtil.isNetworkError(state.throwable)){
                    viewModel.loadContentFromCache()
                }
                else {
                    Toast.makeText(activity,"Something went wrong!",Toast.LENGTH_LONG).show()
                }
            }
            is ItunesContentState.OnNoLoadedCache -> {
                Toast.makeText(activity,"No data from cache!",Toast.LENGTH_LONG).show()
            }
            is ItunesContentState.OnLoadDetailPage -> {
                if (activity is MainActivity){
                    (activity as MainActivity).supportFragmentManager.commit {
                        this.setCustomAnimations(R.anim.anim_slide_in, R.anim.anim_fade_out, R.anim.anim_fade_in, R.anim.anim_slide_out)
                        this.addToBackStack(ItunesDetailFragment::class.java.simpleName)
                        this.add(R.id.fragment_container,ItunesDetailFragment.newInstance(
                           data = state.content.data!!),ItunesDetailFragment::class.java.simpleName)
                    }
                }
            }
            else -> throw  RuntimeException("INVALID STATE")
        }
    }
    //endregion

    //region USER ACTION
    override fun onRefresh() {
        binding.swipeRefreshLayout.isRefreshing = false
        viewModel.loadContent()
    }
    //endregion
}