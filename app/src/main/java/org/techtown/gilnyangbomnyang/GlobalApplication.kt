package org.techtown.gilnyangbomnyang

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        //카카오 sdk 초기화
        KakaoSdk.init(this,"2f76ead9dc5073b0edde67209e3e97cf")
    }
}