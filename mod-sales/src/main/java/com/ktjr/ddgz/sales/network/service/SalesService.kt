package com.ktjr.ddgz.sales.network.service

import com.torment.lib.core.entity.AppEntity
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap
import rx.Observable

/**
 * 销售相关接口
 */
interface SalesService {
//    /**
//     * 测试
//     */
//    @POST("sale/v1/users/login")
//    fun requestLogin(@QueryMap map: Map<String, String>): Observable<AppEntity<DevelopEntity>>
//
//    /**
//     * 销售统计（排名）
//     */
//    @GET("sale/v1/statistics/salesman/ranking")
//    fun requestSalesmanRanking(): Observable<AppEntity<SalesRankingEntity>>
//
//    /**
//     * 统计当月销售人员业绩线索情况数量
//     */
//    @GET("sale/v1/statistics/salesman/performance")
//    fun requestSalesAnalysis(): Observable<AppEntity<SalesAnalysisEntity>>
}