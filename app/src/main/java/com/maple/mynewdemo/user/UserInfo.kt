package com.maple.mynewdemo.user

data class UserInfo (val name: String = "",val age: Int = 0, val avatar: String = "", val list: MutableList<String> = mutableListOf())