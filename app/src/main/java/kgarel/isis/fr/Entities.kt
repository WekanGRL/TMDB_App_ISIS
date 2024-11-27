package kgarel.isis.fr

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(@PrimaryKey val id: Int, val file: Movie)

@Entity
data class ShowEntity(@PrimaryKey val id: Int, val file: Show)

@Entity
data class ActorEntity(@PrimaryKey val id: Int, val file: Actor)