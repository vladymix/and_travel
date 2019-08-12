package com.fabricio.travel.listeners

interface IOnItemsSeleccted<T> {
    fun itemSeleccted(items: List<T>)
}