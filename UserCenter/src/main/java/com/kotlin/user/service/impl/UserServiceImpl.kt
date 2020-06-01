package com.kotlin.user.service.impl

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.base.ext.BaseException
import com.kotlin.user.data.repository.UserRepository
import com.kotlin.user.service.UserService
import rx.Observable
import rx.functions.Func1

class UserServiceImpl : UserService {
    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {

        val repository = UserRepository()

        var ob = repository.register(mobile, pwd, verifyCode)
            .flatMap(object : Func1<BaseResp<String>, Observable<Boolean>> {
                override fun call(t: BaseResp<String>?): Observable<Boolean> {
                    if (t != null) {
                        if (t.status != 0) {
                            return Observable.error(BaseException(t.status, t.msg))
                        }
                        return Observable.just(true)
                    }else{
                        return Observable.just(false)
                    }
                }
            })
        return ob
    }
}


