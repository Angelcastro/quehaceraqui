/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.angelcastro.quehaceraqui.bd;

import es.angelcastro.quehaceraqui.clase.Rutas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Castro
 */
public class queaceraquiDBManagerMySQL {
    private static Connection connection;

    public static void connect(String databaseServer, String databaseName, String databaseUser, String databasePassword) {
        String strConection = "jdbc:mysql://" + databaseServer + "/" + databaseName;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(strConection, databaseUser, databasePassword);
            createTables();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1049) {
                Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                        "Database not found: " + strConection + " - " + databaseUser + "/" + databasePassword);
                createDatabase(databaseServer, databaseName, databaseUser, databasePassword);
                Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                        "Database created");
                createTables();
            }
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean isConnected() {
        if(connection!=null) {
            return true;
        } else {
            return false;
        }
    }

    private static void createDatabase(String databaseServer, String databaseName, String databaseUser, String databasePassword) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String strConecction = "jdbc:mysql://" + databaseServer;
            connection = DriverManager.getConnection(strConecction, databaseUser, databasePassword);
            Statement statement = connection.createStatement();
            String sql = "CREATE DATABASE " + databaseName;
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "Executing SQL statement: " + sql);
            boolean result = statement.execute(sql);
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "SQL result: " + result);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void createTables() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS rutas ("
                    + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + "nombre VARCHAR(50), "
                    + "alias VARCHAR(50), "
                    + "kilometros VARCHAR(50), "
                    + "dificultad VARCHAR(50), "
                    + "latitud VARCHAR(50), "
                    + "longitud VARCHAR(255), "
                    + "localidad VARCHAR(10), "
                    + "provincia VARCHAR(50), "
                    + "pais VARCHAR(50), "
                    + "comentario VARCHAR(50), "
                    + "fotoruta VARCHAR(50))";
            
            Statement statement = connection.createStatement();
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "Executing SQL statement: " + sql);
            boolean result = statement.execute(sql);
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "SQL result: " + result);
        } catch (SQLException ex) {
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertPerson(Rutas rutas) {
        //Formato para campos de tipo fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("''yyyy-MM-dd''");
        //Date birthDate = rutas.getBirthDate();
        String birthDateSql = null;
        /*if (birthDate != null) {
            birthDateSql = dateFormat.format(birthDate);
        }*/

        try {
            String sql = "INSERT INTO rutas "
                    //No se incluye el ID, ya que es autonumérico
                    + "(nombre, alias, kilometros, dificultad, latitud, longitud, "
                    + "localidad, provincia, pais, comentario, fotoruta) "
                    + "VALUES ("
                    + "'" + rutas.getNombre()+ "', "
                    + "'" + rutas.getAlias() + "', "
                    + "'" + rutas.getKilometros() + "', "
                    + "'" + rutas.getDificultad() + "', "
                    + "'" + rutas.getLatitud() + "', "
                    + "'" + rutas.getLongitud()+ "', "
                    + "'" + rutas.getLocalidad() + "', "
                    + "'" + rutas.getProvincia() + "', "
                    + "'" + rutas.getPais() + "', "
                    + "'" + rutas.getComentario()+ "', "
                    + "'" + rutas.getFotoruta()+ "')";

            Statement statement = connection.createStatement();
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "Executing SQL statement: " + sql);
            boolean result = statement.execute(sql);
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "SQL result: " + result);
        } catch (SQLException ex) {
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Rutas> getPersonsList() {
        ArrayList<Rutas> personsList = new ArrayList();
        try {
            String sql = "SELECT * FROM rutas";
            Statement statement = connection.createStatement();
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "Executing SQL statement: " + sql);
            ResultSet rs = statement.executeQuery(sql);
            boolean result = rs.isBeforeFirst();
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "SQL result: " + result);
            while (rs.next()) {
                int columnIndex = 1;
                int id = rs.getInt(columnIndex++);
                String nombre = rs.getString(columnIndex++);
                String alias = rs.getString(columnIndex++);
                String kilometros = rs.getString(columnIndex++);
                String dificultad = rs.getString(columnIndex++);
                String latitud = rs.getString(columnIndex++);
                String longitud = rs.getString(columnIndex++);
                String localidad = rs.getString(columnIndex++);
                String provincia = rs.getString(columnIndex++);
                String pais = rs.getString(columnIndex++);
                String comentario = rs.getString(columnIndex++);
                String fotoruta = rs.getString(columnIndex++);
                Rutas rutas = new Rutas(id, nombre, alias, kilometros, dificultad, latitud, longitud, localidad, provincia, pais, comentario, fotoruta);
                personsList.add(rutas);
            }
            return personsList;
        } catch (SQLException ex) {
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Rutas getPersonByID(int rutasId) {
        Rutas rutas = null;
        try {
            String sql = "SELECT * FROM rutas WHERE id=" + rutasId;
            Statement statement = connection.createStatement();
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "Executing SQL statement: " + sql);
            ResultSet rs = statement.executeQuery(sql);
            boolean result = rs.isBeforeFirst();
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "SQL result: " + result);
            if (rs.next()) {
                int columnIndex = 1;
                int id = rs.getInt(columnIndex++);
                String nombre = rs.getString(columnIndex++);
                String alias = rs.getString(columnIndex++);
                String kilometros = rs.getString(columnIndex++);
                String dificultad = rs.getString(columnIndex++);
                String latitud = rs.getString(columnIndex++);
                String longitud = rs.getString(columnIndex++);
                String localidad = rs.getString(columnIndex++);
                String provincia = rs.getString(columnIndex++);
                String pais = rs.getString(columnIndex++);
                String comentario = rs.getString(columnIndex++);
                String fotoruta = rs.getString(columnIndex++);
                rutas = new Rutas(id, nombre, alias, kilometros, dificultad, latitud, longitud, localidad, provincia, pais, comentario, fotoruta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rutas;
    }

    public static void updatePerson(Rutas rutas) {
        //Formato para campos de tipo fecha
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("''yyyy-MM-dd''");
        Date birthDate = rutas.getBirthDate();
        String birthDateSql = null;
        if (birthDate != null) {
            birthDateSql = dateFormat.format(birthDate);
        }*/
        try {
            String sql = "UPDATE rutas SET "
                    + "nombre='" + rutas.getNombre()+ "', "
                    + "alias='" + rutas.getAlias()+ "', "
                    + "kilometros='" + rutas.getKilometros()+ "', "
                    + "dificultad='" + rutas.getDificultad()+ "', "
                    + "latitud='" + rutas.getLatitud()+ "', "
                    + "longitud='" + rutas.getLongitud()+ "', "
                    + "localidad='" + rutas.getLocalidad()+ "', "
                    + "provincia='" + rutas.getProvincia()+ "', "
                    + "pais='" + rutas.getPais()+ "', "
                    + "comentario='" + rutas.getComentario()+ "', "
                    + "fotoruta='" + rutas.getFotoruta()+ "', "
                    + "WHERE id=" + rutas.getId();
            Statement statement = connection.createStatement();
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "Executing SQL statement: " + sql);
            boolean result = statement.execute(sql);
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "SQL result: " + result);
        } catch (SQLException ex) {
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deletePersonById(String id) {
        try {
            String sql = "DELETE FROM rutas WHERE id=" + id;
            Statement statement = connection.createStatement();
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "Executing SQL statement: " + sql);
            boolean result = statement.execute(sql);
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.FINE,
                    "SQL result: " + result);
        } catch (SQLException ex) {
            Logger.getLogger(queaceraquiDBManagerMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
