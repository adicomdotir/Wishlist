package ir.adicom.wishlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.adicom.wishlist.persistance.RepositoryImpl
import ir.adicom.wishlist.persistance.WishlistDao
import ir.adicom.wishlist.persistance.WishlistDaoImpl
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.util.*

@RunWith(AndroidJUnit4::class)
class DetailViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val wishlistDao: WishlistDao = Mockito.spy(WishlistDaoImpl())
    private val viewModel = DetailViewModel(RepositoryImpl(wishlistDao))

    @Test
    fun saveNewItemCallsDatabase() {
        viewModel.saveNewItem(
            Wishlist(
                "Victoria", listOf(
                    "RW Android Book", "Android phone"
                ), 1
            ), "Smart watch"
        )
        verify(wishlistDao).save(any())
    }

    @Test
    fun saveNewItemSavesData() {
        val wishlist = Wishlist(
            "Victoria", listOf(
                "RW Android Book", "Android phone"
            ), 1
        )
        val name = "Smart watch"
        viewModel.saveNewItem(wishlist, name)
        val mockObserver = mock<Observer<Wishlist>>()
        wishlistDao.findById(wishlist.id).observeForever(mockObserver)
        verify(mockObserver).onChanged(wishlist.copy(wishes = wishlist.wishes + name))
    }

    @Test
    fun getWishlistCallsDatabase() {
        viewModel.getWishlist(1)
        verify(wishlistDao).findById(any())
    }

    @Test
    fun getWishListReturnsCorrectData() {
        val wishlist = Wishlist(
            "Victoria", listOf(
                "RW Android Book", "Android phone"
            ), 1
        )
        wishlistDao.save(wishlist)
        val mockObserver = mock<Observer<Wishlist>>()
        viewModel.getWishlist(1).observeForever(mockObserver)

        verify(mockObserver).onChanged(wishlist)
    }
}