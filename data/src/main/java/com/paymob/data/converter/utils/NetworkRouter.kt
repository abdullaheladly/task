package com.paymob.data.converter.utils



import com.paymob.domain.exception.ApiException
import com.paymob.domain.exception.EmptyResponseException
import com.paymob.domain.exception.MessageException
import com.paymob.domain.exception.UnexpectedException
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject


class NetworkRouter @Inject constructor() {

    suspend fun <T : Any> invokeApi(call: suspend () -> Response<T>): T {
        return try {
            val response = call.invoke()

            val body = response.body() ?: throw EmptyResponseException()

            val gson = Gson()

            val jsonString = gson.toJson(body)
            val jsonObject = JSONObject(jsonString)

            val isSuccess = jsonObject.optBoolean("success", true)

            if (isSuccess) {
                return body
            } else {
                val errorObject = jsonObject.opt("error")

                if (errorObject is JSONObject) {
                    val apiError = Gson().fromJson(errorObject.toString(), ErrorResponse::class.java)

                    if (apiError != null) {
                        throw MessageException(apiError.info.toString())
                    } else {
                        throw MessageException("Error object is null.")
                    }
                } else {
                    throw MessageException("Unknown error occurred")
                }

            }
        } catch (e: Exception) {
            // Catch different exceptions and throw appropriate ones
            throw when (e) {
                is HttpException -> ApiException()
                is EmptyResponseException, is UnexpectedException, is MessageException -> throw e
                else -> MessageException(e.message.toString())
            }
        }
    }

}
