package com.kotlin.user.service.impl

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.base.ext.BaseException
import com.kotlin.user.data.repository.UserRepository
import com.kotlin.user.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

class UserServiceImpl @Inject constructor(): UserService {
    //让它自己实例化，使用Inject
    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        //UserRepository 才是真正的去访问网络层

        //注册成功后回调回来
        var ob = repository.register(mobile, pwd, verifyCode)
            .flatMap(object : Func1<BaseResp<String>, Observable<Boolean>> {
                override fun call(t: BaseResp<String>?): Observable<Boolean> {
                    //通过这个里面的状态来判断是成功还是失败
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


