package com.example.materialdesign.view.recycler

data class Data(val id:Int=0,val name: String = "None", val someDescription: String? = "Description",val type:Int = TYPE_MARS)

data class Change<out T>(
    val oldData: T,
    val newData: T
)

fun <T> createCombinedPayload(payloads: List<Change<T>>): Change<T> {
    assert(payloads.isNotEmpty())
    val firstChange = payloads.first()
    val lastChange = payloads.last()

    return Change(firstChange.oldData, lastChange.newData)
}