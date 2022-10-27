package com.dantesys.addpvidros.data.model

import androidx.room.Entity

@Entity
data class User(
    var nome:String,
    var email:String,
    val id:Int=0
)
