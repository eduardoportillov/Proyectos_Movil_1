package com.moviles.marketplace.api

import android.content.Context
import android.net.Uri
import com.example.marketplace.models.Search
import com.moviles.marketplace.api.retrofit.RetroFit
import com.moviles.marketplace.api.retrofit.RetroFitService
import com.moviles.marketplace.models.Photo
import com.moviles.marketplace.models.Product
import com.moviles.marketplace.models.Response
import com.moviles.marketplace.utilities.FileUtils
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class ProductRepository {
    private val retrofitService: RetroFitService

    init {
        retrofitService = RetroFit.getRetrofitService()
    }

    //@GET("/api/products")
    fun getProduct(listener: GetProductByUserListener) {
        retrofitService.getProductUserLoged()
            .enqueue(object : retrofit2.Callback<ArrayList<Product>> {
                override fun onFailure(call: retrofit2.Call<ArrayList<Product>>, t: Throwable) {
                    listener.onProductByUserError(t)
                }

                override fun onResponse(
                    call: retrofit2.Call<ArrayList<Product>>,
                    response: retrofit2.Response<ArrayList<Product>>
                ) {
                    listener.getProductByUserReady(response.body()!!)
                }
            })
    }

    //@POST("/api/products")
    fun createProduct(product: Product, listener: CreteProductListener) {
        retrofitService.createProduct(product).enqueue(object : retrofit2.Callback<Product> {
            override fun onFailure(call: retrofit2.Call<Product>, t: Throwable) {
                listener.onCreteProductError(t)
            }

            override fun onResponse(
                call: retrofit2.Call<Product>,
                response: retrofit2.Response<Product>
            ) {
                listener.creteProductReady(response.body()!!)
            }
        })
    }

    //@PUT("/api/products/{id}")
    fun updateProduct(id: Long, product: Product, listener: UpdateProductListener) {
        retrofitService.updateProduct(product, id).enqueue(object : retrofit2.Callback<Product> {
            override fun onFailure(call: retrofit2.Call<Product>, t: Throwable) {
                listener.onUpdateProductError(t)
            }

            override fun onResponse(
                call: retrofit2.Call<Product>,
                response: retrofit2.Response<Product>
            ) {
                listener.updateProductReady(response.body()!!)
            }
        })
    }

    //@DELETE("/api/products/{id}")
    fun deleteProduct(id: Long, listener: DeleteProductListener) {
        retrofitService.deleteProduct(id).enqueue(object : retrofit2.Callback<Response> {
            override fun onFailure(call: retrofit2.Call<Response>, t: Throwable) {
                listener.onDeleteProductError(t)
            }

            override fun onResponse(
                call: retrofit2.Call<Response>,
                response: retrofit2.Response<Response>
            ) {
                listener.deleteProductReady(response.body()!!)
            }
        })
    }

    //@GET("/api/products/{id}")
    fun getProductById(id: Long, listener: GetProductByIdListener) {
        retrofitService.getProductById(id).enqueue(object : retrofit2.Callback<Product> {
            override fun onFailure(call: retrofit2.Call<Product>, t: Throwable) {
                listener.onGetProductByIdError(t)
            }

            override fun onResponse(
                call: retrofit2.Call<Product>,
                response: retrofit2.Response<Product>
            ) {
                listener.getProductByIdReady(response.body()!!)
            }
        })
    }

    //@POST("/api/products/search")
    fun getAllProductsWithSearch(search: Search, listener: GetAllProductsWithSearchListener) {
        retrofitService.getAllProductsWithSearch(search)
            .enqueue(object : retrofit2.Callback<ArrayList<Product>> {
                override fun onFailure(call: retrofit2.Call<ArrayList<Product>>, t: Throwable) {
                    listener.onGetAllProductsWithSearchError(t)
                }

                override fun onResponse(
                    call: retrofit2.Call<ArrayList<Product>>,
                    response: retrofit2.Response<ArrayList<Product>>
                ) {
                    listener.getAllProductsWithSearchReady(response.body()!!)
                }
            })
    }

    //@POST("/api/products/{id}/images")
    fun uploadImage(id: Long, fileUri: Uri, context: Context, listener: UploadImageListener) {

            val file: File = FileUtils.getFile(context, fileUri) ?: return
            val fileBody: RequestBody =
                RequestBody.create(MediaType.parse(context.contentResolver.getType(fileUri)), file)
            val body = MultipartBody.Part.createFormData("image", file.name, fileBody)

            retrofitService.addProductPhoto(id, body).enqueue(object : retrofit2.Callback<Photo> {
                override fun onFailure(call: Call<Photo>, t: Throwable) {
                    listener.onUploadImagehError(t)
                }

                override fun onResponse(
                    call: Call<Photo>,
                    response: retrofit2.Response<Photo?>
                ) {
                    listener.uploadImagehReady(response.body()!!)
                }
            })
        }

        //@DELETE("/api/images/{id}") TODO implementar @DELETE("/api/images/{id}")


        interface GetProductByUserListener {
            fun getProductByUserReady(products: ArrayList<Product>)
            fun onProductByUserError(t: Throwable)
        }

        interface CreteProductListener {
            fun creteProductReady(product: Product)
            fun onCreteProductError(t: Throwable)
        }

        interface UpdateProductListener {
            fun updateProductReady(product: Product)
            fun onUpdateProductError(t: Throwable)
        }

        interface DeleteProductListener {
            fun deleteProductReady(response: Response)
            fun onDeleteProductError(t: Throwable)
        }

        interface GetProductByIdListener {
            fun getProductByIdReady(product: Product)
            fun onGetProductByIdError(t: Throwable)
        }

        interface GetAllProductsWithSearchListener {
            fun getAllProductsWithSearchReady(products: ArrayList<Product>)
            fun onGetAllProductsWithSearchError(t: Throwable)
        }

        interface UploadImageListener {
            fun uploadImagehReady(photo: Photo)
            fun onUploadImagehError(t: Throwable)
        }


    }

