package com.example.basedatos.Repository

import com.example.basedatos.DAO.UserDao
import com.example.basedatos.Model.user

class UserRepository(private val userDao: UserDao) {
    suspend fun insert(user: user){
        userDao.insert(user)
    }

    suspend fun getAllUsers(): List<user>{
        return userDao.getAllUsers()
    }
    suspend fun deleteById(userId: Int): Int{
        return userDao.deleteById(userId)
    }
    suspend fun updateById(userId: Int, nombre: String, apellido: String, edad: Int): Int {
        return userDao.updateById(userId, nombre, apellido, edad)
    }

}