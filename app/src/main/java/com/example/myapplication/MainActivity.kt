package com.example.myapplication

import kotlin.random.Random

abstract class StoreKeeper {
    val array: IntArray = IntArray(10) { Random.nextInt(1, 10) }
    abstract fun sort()
}

open class JuniorStoreKeeper : StoreKeeper() {
    // Сортировка пузырьком
     override fun sort() {
         bubbleSort()
    }
    private fun bubbleSort() {
        // Решила измерить время в наносекундах, так как в мс недостаточная точность для маленького массива
        val start = System.nanoTime()
        var swapped: Boolean
        val n = array.size
        do {
            swapped = false
            for (i in 0 until n - 1) {
                if (array[i] > array[i + 1]) {
                    val temp = array[i]
                    array[i] = array[i + 1]
                    array[i + 1] = temp
                    swapped = true
                }
            }
        } while (swapped)
        val end = System.nanoTime()

        println("Отсортированный джуном массив: ${array.contentToString()}")
        println("Время сортировки пузырьком: ${(end - start)} наносекунд")
    }
}

class SeniorStoreKeeper : JuniorStoreKeeper() {
    // Сортировка слиянием
    override fun sort() {
        val start = System.nanoTime()
        array.mergeSort()
        val end = System.nanoTime()
        println("Отсортированный сеньером массив: ${array.contentToString()}")
        println("Время сортировки слиянием: ${(end - start)} наносекунд")
    }
}

fun IntArray.mergeSort(low: Int = 0, high: Int = size - 1) {
    if (low < high) {
        val mid = (low + high) / 2
        mergeSort(low, mid)
        mergeSort(mid + 1, high)
        merge(low, mid, high)
    }
}

private fun IntArray.merge(low: Int, mid: Int, high: Int) {
    val leftArray = this.copyOfRange(low, mid + 1)
    val rightArray = this.copyOfRange(mid + 1, high + 1)
    var i = 0
    var j = 0
    var k = low

    while (i < leftArray.size && j < rightArray.size) {
        if (leftArray[i] <= rightArray[j]) {
            this[k] = leftArray[i]
            i++
        } else {
            this[k] = rightArray[j]
            j++
        }
        k++
    }

    while (i < leftArray.size) {
        this[k] = leftArray[i]
        i++
        k++
    }

    while (j < rightArray.size) {
        this[k] = rightArray[j]
        j++
        k++
    }
}

fun main() {
    val juniorStoreKeeper = JuniorStoreKeeper()
    println("Исходный массив: ${juniorStoreKeeper.array.contentToString()}")
    juniorStoreKeeper.sort()
    val seniorStoreKeeper = SeniorStoreKeeper()
    println("Исходный массив: ${seniorStoreKeeper.array.contentToString()}")
    seniorStoreKeeper.sort()
}