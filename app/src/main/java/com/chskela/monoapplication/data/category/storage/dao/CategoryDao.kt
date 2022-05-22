package com.chskela.monoapplication.data.category.storage.dao

import androidx.room.*
import com.chskela.monoapplication.data.category.storage.models.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getAllCategory(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM category WHERE id = :id")
    fun getCategoryById(id: Long): Flow<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Update
    suspend fun updateCategory(categoryEntity: CategoryEntity)

    @Query("DELETE FROM category WHERE id = :id")
    suspend fun deleteCategory(id: Long)

}
