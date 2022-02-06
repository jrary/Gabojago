
package org.techtown.gabojago.config

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.techtown.gabojago.ApplicationClass.Companion.X_ACCESS_TOKEN
import org.techtown.gabojago.main.getjwt

class XAccessTokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()

        val jwtToken: String? = getjwt()

        jwtToken?.let{
            builder.addHeader(X_ACCESS_TOKEN, jwtToken)
        }

        return chain.proceed(builder.build())
    }
}
