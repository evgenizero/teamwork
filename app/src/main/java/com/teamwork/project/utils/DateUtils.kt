package com.teamwork.project.utils

interface DateUtils {
    fun formatProjectCreatedOnDate(input: String?): String?

    fun formatProjectEndDate(input: String?): String?
    fun formatProjectDates(createdOn: String?, endDate: String?): CharSequence?
}
