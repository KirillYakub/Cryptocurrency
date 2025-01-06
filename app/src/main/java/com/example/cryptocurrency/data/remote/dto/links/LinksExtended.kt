package com.example.cryptocurrency.data.remote.dto.links

import com.example.cryptocurrency.data.remote.dto.Stats

data class LinksExtended(
    val stats: Stats,
    val type: String,
    val url: String
)