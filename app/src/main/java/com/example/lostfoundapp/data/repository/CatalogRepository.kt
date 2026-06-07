package com.example.lostfoundapp.data.repository;


import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.model.Category;
import com.example.lostfoundapp.data.model.Location;
import com.example.lostfoundapp.data.remote.RetrofitInstance;
class CatalogRepository(
    sessionManager: SessionManager
) {

    private val api
    = RetrofitInstance.createApi(
        sessionManager
    )
    suspend fun getCategories(): Result<List<Category>> {

        return try {

            val response =
                api.getCategories()

            if(response.isSuccessful) {

                Result.success(
                    response.body()?.data
                            ?: emptyList()
                )

            } else {

                Result.failure(
                    Exception(
                        "Error al obtener categorías"
                    )
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun getLocations(): Result<List<Location>> {

        return try {

            val response =
                api.getLocations()

            if(response.isSuccessful) {

                Result.success(
                    response.body()?.data
                            ?: emptyList()
                )

            } else {

                Result.failure(
                    Exception(
                        "Error al obtener ubicaciones"
                    )
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }
}
