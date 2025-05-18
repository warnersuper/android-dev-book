package edu.hrbeu.chapterservice

import android.os.Parcel
import android.os.Parcelable

data class EResult(
    var quotient: Int,
    var remainder: Int,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(quotient)
        parcel.writeInt(remainder)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<EResult> {
        override fun createFromParcel(parcel: Parcel): EResult = EResult(parcel)
        override fun newArray(size: Int): Array<EResult?> = arrayOfNulls(size)
    }
}