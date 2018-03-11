package weatherintamriel.viewmodel.architecture
import android.arch.lifecycle.MutableLiveData

class NullSafeMutableLiveData<T>: MutableLiveData<T>() {

    override fun getValue(): T {
        return super.getValue()!! //Throw an exception if live data value is null.
    }
}