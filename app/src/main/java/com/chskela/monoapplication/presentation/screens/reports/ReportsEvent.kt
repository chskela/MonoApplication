package com.chskela.monoapplication.presentation.screens.reports

import com.chskela.monoapplication.presentation.screens.reports.models.Report

sealed class ReportsEvent {
    data class SelectReport(val tab: Report) : ReportsEvent()
    data class SelectTab(val tab: Int) : ReportsEvent()
    object PreviousMonth: ReportsEvent()
    object NextMonth: ReportsEvent()
    object ToggleVisible: ReportsEvent()
}