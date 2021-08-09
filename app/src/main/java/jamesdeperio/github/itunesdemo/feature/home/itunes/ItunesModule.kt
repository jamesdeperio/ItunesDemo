package jamesdeperio.github.itunesdemo.feature.home.itunes

import androidx.fragment.app.viewModels
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import jamesdeperio.github.itunesdemo.R
import jamesdeperio.github.itunesdemo.di.scope.FragmentScope
import jamesdeperio.github.itunesdemo.feature.home.itunes.list.HeaderContentViewHolder
import jamesdeperio.github.itunesdemo.feature.home.itunes.list.ItemContentViewHolder
import jamesdeperio.github.itunesdemo.feature.home.itunes.list.ItunesContentAdapter
import jamesdeperio.github.itunesdemo.network.repository.RestRepository

@Module
class ItunesModule {
    @FragmentScope
    @Provides
    fun providesDisposable(): CompositeDisposable = CompositeDisposable()

    @FragmentScope
    @Provides
    fun providesViewModel(fragment: ItunesFragment,
                          restRepository: RestRepository):ItunesContentViewModel {
        val viewModel:ItunesContentViewModel by fragment.viewModels {
            ItunesContentViewModelFactory(
                restRepository = restRepository)
        }
        return viewModel
    }

    @FragmentScope
    @Provides
    fun providesAdapter(): ItunesContentAdapter {
        val adapter =  ItunesContentAdapter()
        val headerViewHolder = HeaderContentViewHolder(adapter = adapter)
        headerViewHolder.setContentView(R.layout.item_list_header_content)
        adapter.addViewHolder(viewHolder = headerViewHolder)  // index 0

        val contentViewHolder = ItemContentViewHolder(adapter = adapter)
        contentViewHolder.setContentView(R.layout.item_list_content)
        adapter.addViewHolder(viewHolder = contentViewHolder)  // index 1

        return adapter
    }

}