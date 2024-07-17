import java.sql.Connection
import java.sql.DriverManager

object Database {
    private const val URL = "jdbc:postgresql://localhost:5432/dbcitas"
    private const val USER = "postgres"
    private const val PASSWORD = "Miclave.1"

    init {
        Class.forName("org.postgresql.Driver")
    }

    private fun getConnection(): Connection {
        return DriverManager.getConnection(URL, USER, PASSWORD)
    }

    fun insertUser(identificacion: Int, nombres: String, apellidos: String, correo: String) {
        val query = "INSERT INTO Usuarios (Identificacion, Nombres, Apellidos, Correo) VALUES (?, ?, ?, ?)"
        getConnection().use { conn ->
            conn.prepareStatement(query).use { stmt ->
                stmt.setInt(1, identificacion)
                stmt.setString(2, nombres)
                stmt.setString(3, apellidos)
                stmt.setString(4, correo)
                stmt.executeUpdate()
            }
        }
    }

    fun updateUser(identificacion: Int, nombres: String, apellidos: String, correo: String) {
        val query = "UPDATE Usuarios SET Nombres = ?, Apellidos = ?, Correo = ? WHERE Identificacion = ?"
        getConnection().use { conn ->
            conn.prepareStatement(query).use { stmt ->
                stmt.setString(1, nombres)
                stmt.setString(2, apellidos)
                stmt.setString(3, correo)
                stmt.setInt(4, identificacion)
                stmt.executeUpdate()
            }
        }
    }

    fun deleteUser(identificacion: Int) {
        val query = "DELETE FROM Usuarios WHERE Identificacion = ?"
        getConnection().use { conn ->
            conn.prepareStatement(query).use { stmt ->
                stmt.setInt(1, identificacion)
                stmt.executeUpdate()
            }
        }
    }

    fun getUser(identificacion: Int): User? {
        val query = "SELECT * FROM Usuarios WHERE Identificacion = ?"
        getConnection().use { conn ->
            conn.prepareStatement(query).use { stmt ->
                stmt.setInt(1, identificacion)
                stmt.executeQuery().use { rs ->
                    return if (rs.next()) {
                        User(
                            rs.getInt("Identificacion"),
                            rs.getString("Nombres"),
                            rs.getString("Apellidos"),
                            rs.getString("Correo")
                        )
                    } else {
                        null
                    }
                }
            }
        }
    }

    fun getAllUsers(): List<User> {
        val query = "SELECT * FROM Usuarios"
        val users = mutableListOf<User>()
        getConnection().use { conn ->
            conn.prepareStatement(query).use { stmt ->
                stmt.executeQuery().use { rs ->
                    while (rs.next()) {
                        users.add(
                            User(
                                rs.getInt("Identificacion"),
                                rs.getString("Nombres"),
                                rs.getString("Apellidos"),
                                rs.getString("Correo")
                            )
                        )
                    }
                }
            }
        }
        return users
    }
}

data class User(val identificacion: Int, val nombres: String, val apellidos: String, val correo: String)
