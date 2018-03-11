package weatherintamriel.viewmodel.architecture

import android.arch.lifecycle.Observer

// Idea taken from here:
// https://stackoverflow.com/questions/46488442/is-it-possible-to-enforce-non-nullability-of-livedata-values
// but tweaked for kotlin
class NullSafeObserver<T>(private val onNonNullDataChanged: (T) -> Unit): Observer<T> {
    override fun onChanged(t: T?) {
        t!! //Throw exception if data we're observing is null
        onNonNullDataChanged(t)
    }
}