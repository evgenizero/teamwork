package com.teamwork.project.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtilsImpl : DateUtils {
    override fun formatProjectDates(createdOn: String?, endDate: String?): CharSequence? =
        "${formatProjectCreatedOnDate(createdOn)} - ${formatProjectEndDate(endDate)}"

    private val createdOnDateParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    private val endDateParser = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    private val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())

    override fun formatProjectEndDate(input: String?): String? {
        val date = endDateParser.parse(input)
        return dateFormat.format(date)
    }

    override fun formatProjectCreatedOnDate(input: String?): String? {
        val date = createdOnDateParser.parse(input)
        return dateFormat.format(date)
    }
}