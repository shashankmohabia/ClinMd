package com.example.shashankmohabia.clinmd.Utils.Extensions

/**
 * Created by Shashank Mohabia on 7/20/2018.
 */
fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()