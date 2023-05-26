package com.example.testeconexo

import java.sql.Connection
import java.sql.DriverManager

class Connection(private val serverName: String, private val databaseName: String, private val username: String, private val password: String) {

    fun testConnection(): Boolean {
        var conn: Connection? = null
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            val url = "jdbc:jtds:sqlserver://$serverName;databaseName=$databaseName;user=$username;password=$password;"
            conn = DriverManager.getConnection(url)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            conn?.close()
        }
        return false
    }
}
