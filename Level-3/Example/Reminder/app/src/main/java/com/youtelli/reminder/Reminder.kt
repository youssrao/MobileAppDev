package com.youtelli.reminder

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reminder(
    var reminderText: String
) :Parcelable