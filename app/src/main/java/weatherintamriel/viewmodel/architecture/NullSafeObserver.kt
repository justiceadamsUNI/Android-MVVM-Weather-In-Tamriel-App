package weatherintamriel.viewmodel.architecture

import android.arch.lifecycle.Observer

class NullSafeObserver<T>(private val onNonNullDataChanged: (T) -> Unit): Observer<T> {
    override fun onChanged(t: T?) {
        t!! //Throw exception if data we're observing is null
        onNonNullDataChanged(t)
    }
}