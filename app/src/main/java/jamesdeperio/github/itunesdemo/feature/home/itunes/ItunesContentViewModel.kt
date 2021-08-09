package jamesdeperio.github.itunesdemo.feature.home.itunes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import jamesdeperio.github.itunesdemo.feature.home.itunes.list.ItunesContent
import jamesdeperio.github.itunesdemo.network.repository.RestRepository

class ItunesContentViewModel(
    private val restRepository: RestRepository
) : ViewModel() {
    private val mState = MutableLiveData<ItunesContentState>()
    val state:LiveData<ItunesContentState> = mState

    /*
     load content from api call
     */
    fun loadContent():Disposable
            = restRepository.getItunesSearchResponse("star","au","movie")
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            mState.value = ItunesContentState.OnLoadItem(contents = it, isFromCache = false)
        }, {
            mState.value = ItunesContentState.OnFailedToLoadItem(throwable = it)
        })

    /*
   load content from cache
   */
    fun loadContentFromCache():Disposable
            = restRepository.getItunesSearchCache()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            mState.value = ItunesContentState.OnLoadItem(contents = it, isFromCache = true)
        }, {
            mState.value = ItunesContentState.OnNoLoadedCache
        })

    fun loadDetailPage(content: ItunesContent) {
        mState.value = ItunesContentState.OnLoadDetailPage(content)
    }
}

/*
   state for itunes content
   */
sealed class ItunesContentState {
    data class OnLoadItem(var contents:List<ItunesContent>, var isFromCache:Boolean): ItunesContentState()
    data class OnFailedToLoadItem(var throwable: Throwable): ItunesContentState()
    data class OnLoadDetailPage(var content: ItunesContent) : ItunesContentState()

    object OnNoLoadedCache: ItunesContentState()
}

/*
   viewmodel factory to allow passing dependencies in constructor
   */
class ItunesContentViewModelFactory(
    private val restRepository: RestRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItunesContentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItunesContentViewModel(restRepository = restRepository) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }

}