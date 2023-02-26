package com.chskela.monoapplication.domain.reports.usecase

import android.annotation.SuppressLint
import com.chskela.monoapplication.domain.common.usecase.BeginningOfMonthUseCase
import com.chskela.monoapplication.domain.reports.repository.ReportsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
class GetAmountByMonthsInCategoryMonthUseCase(
    private val reportsRepository: ReportsRepository,
    private val beginningOfMonthUseCase: BeginningOfMonthUseCase
) {

    private val simpleDateFormat by lazy { SimpleDateFormat("MMM") }

    operator fun invoke(categoryId: Long): Flow<List<Pair<String, Long>>> {

        val startOfMonth: Calendar = Calendar.getInstance()
        startOfMonth.add(Calendar.MONTH, -6)

        return reportsRepository.getAllTransactionsOfCategoryInThisMonth(
            categoryId = categoryId,
            startOfMonth = beginningOfMonthUseCase(startOfMonth)
        ).map { list ->
            (-5..1).map { n ->
                val m: Calendar = Calendar.getInstance()
                m.add(Calendar.MONTH, n)
                val monthNAme = simpleDateFormat.format(m.time)
                list.fold(monthNAme to 0L) { acc, transaction ->
                    if (transaction.timestamp.month == m.get(Calendar.MONTH)) {
                        acc.copy(second = acc.second + transaction.amount / 100)
                    } else acc
                }
            }
        }
    }
}