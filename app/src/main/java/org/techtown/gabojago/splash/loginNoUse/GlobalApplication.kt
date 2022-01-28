package org.techtown.gabojago.splash

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "ec29c64f3f038081326aee86c307a22b")
    }
}