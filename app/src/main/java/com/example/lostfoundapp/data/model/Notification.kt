package com.example.lostfoundapp.data.model

data class Notification(

    val id: Int,

    val type: String,

    val description: String,

    val time: String,

    val is_read: Boolean,

    val post_id: Int? = null
)

// "id": 1, es id
// "type": "Solicitud aprobada"
// "description": "Ya puedes ver la información de contacto del propietario."
// "is_read": 0,
// "post_id": 1,
// "time": "hace 18 minutos"