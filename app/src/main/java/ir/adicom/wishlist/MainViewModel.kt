package ir.adicom.wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ir.adicom.wishlist.persistance.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {

    fun saveNewList(name: String) {
        repository.saveWishlist(Wishlist(name, listOf()))
    }

    fun getWishlists(): LiveData<List<Wishlist>> {
        return repository.getWishlists()
    }
}