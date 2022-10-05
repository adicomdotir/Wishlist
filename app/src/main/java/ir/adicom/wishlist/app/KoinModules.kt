package ir.adicom.wishlist.app

import ir.adicom.wishlist.DetailViewModel
import ir.adicom.wishlist.MainViewModel
import ir.adicom.wishlist.persistance.Repository
import ir.adicom.wishlist.persistance.RepositoryImpl
import ir.adicom.wishlist.persistance.WishlistDao
import ir.adicom.wishlist.persistance.WishlistDaoImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Repository> { RepositoryImpl(get()) }

    single<WishlistDao> { WishlistDaoImpl() }

    viewModel { MainViewModel(get()) }

    viewModel { DetailViewModel(get()) }
}