package com.mad.zocket.model

import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("access_token")
    @Expose
    private var accessToken: String? = null

    @SerializedName("category")
    @Expose
    private var category: String? = null

    @SerializedName("category_list")
    @Expose
    @TypeConverters(CategoryConvertor::class)
    private var categoryList: List<Category?>? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("tasks")
    @Expose
    private var tasks: List<String?>? = null

    fun getAccessToken(): String? {
        return accessToken
    }

    fun setAccessToken(accessToken: String?) {
        this.accessToken = accessToken
    }

    fun getCategory(): String? {
        return category
    }

    fun setCategory(category: String?) {
        this.category = category
    }

    fun getCategoryList(): List<Category?>? {
        return categoryList
    }

    fun setCategoryList(categoryList: List<Category?>?) {
        this.categoryList = categoryList
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getTasks(): List<String?>? {
        return tasks
    }

    fun setTasks(tasks: List<String?>?) {
        this.tasks = tasks
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

}