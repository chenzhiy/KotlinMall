package com.kotlin.base.rx

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.base.ext.BaseException
import rx.Observable
import rx.functions.Func1

class BaseFunc<T> : Func1<BaseResp<T>, Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
        //通过这个里面的状态来判断是成功还是失败
        if (t.status != 0) {
            return Observable.error(BaseException(t.status, t.msg))
        }
        return Observable.just(t.data)
    }
}