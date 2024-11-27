package kgarel.isis.fr

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Dao
interface TMDBDao {
    @Query("SELECT * FROM MovieEntity")
    suspend fun getMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("DELETE FROM MovieEntity WHERE id = :id")
    suspend fun deleteMovie(id: Int)


    @Query("SELECT * FROM ShowEntity")
    suspend fun getShows(): List<ShowEntity>

    @Insert
    suspend fun insertShow(show: ShowEntity)

    @Query("DELETE FROM ShowEntity WHERE id = :id")
    suspend fun deleteShow(id: Int)


    @Query("SELECT * FROM ActorEntity")
    suspend fun getActors(): List<ActorEntity>

    @Insert
    suspend fun insertActor(actor: ActorEntity)

    @Query("DELETE FROM ActorEntity WHERE id = :id")
    suspend fun deleteActor(id: Int)

}

@Database(entities = [MovieEntity::class, ShowEntity::class, ActorEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): TMDBDao
}