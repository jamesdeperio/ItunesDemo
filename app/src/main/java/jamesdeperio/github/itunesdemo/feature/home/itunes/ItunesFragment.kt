package jamesdeperio.github.itunesdemo.feature.home.itunes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import jamesdeperio.github.itunesdemo.databinding.FragmentItunesBinding
import jamesdeperio.github.itunesdemo.feature.home.itunes.list.ItunesContentAdapter
import javax.inject.Inject


class ItunesFragment : DaggerFragment(), Observer<ItunesContentState> {
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

                adapter.notifyDataSetChanged()
            }
            is ItunesContentState.OnFailedToLoadItem -> {

            }
        }
    }
}