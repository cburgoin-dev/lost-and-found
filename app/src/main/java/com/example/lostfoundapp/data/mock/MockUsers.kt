package com.example.lostfoundapp.data.mock

import com.example.lostfoundapp.R
import com.example.lostfoundapp.data.model.User

val anaUser = User(
    id = 1,
    fullName = "Ana Sofía Perez",
    profileImageRes = R.drawable.ana_profile,
    email = "ana.sofia@uabcs.mx",
    phone = "612 123 4567"
)

val carlosUser = User(
    id = 2,
    fullName = "Carlos Mendoza",
    profileImageRes = R.drawable.carlos_profile,
    email = "carlos.mendoza@uabcs.mx",
    phone = "612 987 6543"
)

val anonymousUser = User(
    id = 3,
    fullName = "Usuario anónimo",
    profileImageRes = null,
    email = null,
    phone = null
)

val sofiaUser = User(
    id = 4,
    fullName = "Sofía Ramírez",
    profileImageRes = R.drawable.sofia_profile,
    email = "sofia.ramirez@uabcs.mx",
    phone = "612 456 7890"
)