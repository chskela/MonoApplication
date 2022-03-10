package com.chskela.monoapplication.presentation.screens.monthreport

sealed class MonthReportEvent {
    data class SelectTab(val tab: Int) : MonthReportEvent()
    object PreviousMonth: MonthReportEvent()
    object NextMonth: MonthReportEvent()
}