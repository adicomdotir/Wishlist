package ir.adicom.wishlist.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.adicom.wishlist.Wishlist

@Database(entities = [Wishlist::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class WishlistDatabase : RoomDatabase() {
}