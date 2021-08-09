package jamesdeperio.github.itunesdemo.feature.home.itunes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import jamesdeperio.github.itunesdemo.network.repository.RestRepository

class ItunesContentViewModel(
    private val restRepository: RestRepository
) : ViewModel() {
    private val mState = MutableLiveData<ItunesContentState>()
    val state:LiveData<ItunesContentState> = mState

    fun loadContent():Disposable
    = Observable.just(true)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
           // mState.value = PopularContentState.OnLoadItem(contents = it)
        }, {
          //  mState.value = PopularContentState.OnFailedToLoadItem(throwable = it)
        })



}
sealed class ItunesContentState {
    data class OnLoadItem(var contents:List<Any>): ItunesContentState()
    data class OnFailedToLoadItem(var throwable: Throwable): ItunesContentState()
}

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