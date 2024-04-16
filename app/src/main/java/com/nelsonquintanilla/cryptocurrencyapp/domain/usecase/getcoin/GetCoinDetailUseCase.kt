package com.nelsonquintanilla.cryptocurrencyapp.domain.usecase.getcoin

import com.nelsonquintanilla.cryptocurrencyapp.common.Resource
import com.nelsonquintanilla.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.nelsonquintanilla.cryptocurrencyapp.domain.model.CoinDetail
import com.nelsonquintanilla.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetail = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coinDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
