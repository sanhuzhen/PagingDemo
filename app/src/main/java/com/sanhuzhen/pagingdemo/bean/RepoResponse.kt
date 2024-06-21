package com.sanhuzhen.pagingdemo.bean

import com.google.gson.annotations.SerializedName

class RepoResponse(
    @SerializedName("items") val items: List<Repo> = emptyList()
)