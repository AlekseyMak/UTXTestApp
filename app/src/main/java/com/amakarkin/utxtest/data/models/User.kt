package com.amakarkin.utxtest.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val name: String,
    val type: String,
    val password: String,
    var isBlocked: Boolean,
    var failedAttempts: Int) {

    override fun toString(): String {
        return "Role: $type, " +
                "name: $name," +
                " password: $password," +
                " blocked: $isBlocked, " +
                "failedAttempts: $failedAttempts"
    }
}