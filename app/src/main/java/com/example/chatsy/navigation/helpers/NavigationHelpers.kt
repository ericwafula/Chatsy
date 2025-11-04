package com.example.chatsy.navigation.helpers

import androidx.compose.runtime.snapshots.SnapshotStateList

fun <T> SnapshotStateList<T>.navigateForward(destination: T) {
    add(destination)
}

fun <T> SnapshotStateList<T>.navigateBack() {
    removeLastOrNull()
}

fun <T> SnapshotStateList<T>.replace(destination: T) {
    clear()
    add(destination)
}
