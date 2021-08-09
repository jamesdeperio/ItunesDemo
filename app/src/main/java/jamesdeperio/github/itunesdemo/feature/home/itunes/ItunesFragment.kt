package jamesdeperio.github.itunesdemo.feature.home.itunes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import jamesdeperio.github.itunesdemo.databinding.FragmentItunesBinding
import jamesdeperio.github.itunesdemo.feature.home.itunes.list.ItunesContentAdapter
import jamesdeperio.github.itunesdemo.util.NetworkUtil
import javax.inject.Inject


class ItunesFragment : DaggerFragment(), Observer<ItunesContentState>,
    SwipeRefreshLayout.OnRefreshListener {
    @Inject lateinit var threadBag: CompositeDisposable
    @Inject lateinit var viewModel: ItunesContentViewModel
    @Inject lateinit var adapter: ItunesContentAdapter
    private lateinit var binding: FragmentItunesBinding

    companion object {
        @JvmStatic
        fun newInstance() = ItunesFragment()
    }

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

    override fun onChanged(state: ItunesContentState?) {
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
            else -> throw  RuntimeException("INVALID STATE")
        }
    }

    override fun onRefresh() {
        binding.swipeRefreshLayout.isRefreshing = false
        viewModel.loadContent()
    }
}