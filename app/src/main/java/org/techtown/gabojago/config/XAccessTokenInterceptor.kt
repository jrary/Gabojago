package org.techtown.gabojago.config

//class XAccessTokenInterceptor: Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val builder: Request.Builder = chain.request().newBuilder()
//
//        val jwtToken: String? = getJwt()
//
//        jwtToken?.let{
//            builder.addHeader(X_ACCESS_TOKEN, jwtToken)
//        }
//
//        return chain.proceed(builder.build())
//    }
//}