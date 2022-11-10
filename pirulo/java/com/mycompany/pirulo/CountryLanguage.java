package com.mycompany.pirulo;

import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CountryLanguage {

    String CountryCode;
    String Language;
    String IsOfficial;
    float Percentage;

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public String getIsOfficial() {
        return IsOfficial;
    }

    public void setIsOfficial(String IsOfficial) {
        this.IsOfficial = IsOfficial;
    }

    public float getPercentage() {
        return Percentage;
    }

    public void setPercentage(float Percentage) {
        this.Percentage = Percentage;
    }

    public void InsertarLanguage(JTextField paramLang, JTextField paramCC, JTextField paramIsof, JTextField paramPercen) {

        setCountryCode(paramCC.getText());
        setLanguage(paramLang.getText());
        setIsOfficial(paramIsof.getText());
        setPercentage(Float.parseFloat(paramPercen.getText()));

        conexion cn = new conexion();

        String consulta = "insert into world.countrylanguage (CountryCode,Language,IsOfficial,Percentage) values (?,?,?,?);";

        try {

            CallableStatement cs = cn.establecerConexion().prepareCall(consulta);

            cs.setString(1, getCountryCode());
            cs.setString(2, getLanguage());
            cs.setString(3, getIsOfficial().toString());
            cs.setFloat(4, getPercentage());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se ha insertado correctamente la ciudad");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido insetar la ciudad error: " + e.toString());
        }
    }

    public void MostrarCountryCode(JTable paramTablaTotalCountryLanguage) {

        conexion cn = new conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalCountryLanguage.setRowSorter(OrdenarTabla);

        String sql = "";

        modelo.addColumn("Countrycode");
        modelo.addColumn("Language");
        modelo.addColumn("Isofficial");
        modelo.addColumn("Percentage");

        paramTablaTotalCountryLanguage.setModel(modelo);

        sql = "select * from CountryLanguage;";

        String[] datos = new String[4];
        Statement st;

        try {

            st = cn.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);

                modelo.addRow(datos);
            }
            paramTablaTotalCountryLanguage.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
        }

    }

    public void SeleccionarCLenguage(JTable paramTablaCountryLanguage, JTextField paramLanguage, JTextField paramCC, JTextField paramIsof, JTextField paramPercen) {

        try {
            int fila = paramTablaCountryLanguage.getSelectedRow();

            if (fila>=0) {
                
                paramLanguage.setText((paramTablaCountryLanguage.getValueAt(fila, 0).toString()));
                paramCC.setText((paramTablaCountryLanguage.getValueAt(fila, 1).toString()));
                paramIsof.setText((paramTablaCountryLanguage.getValueAt(fila, 2).toString()));
                paramPercen.setText((paramTablaCountryLanguage.getValueAt(fila, 3).toString()));
                
            }else
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: "+e.toString());
        }

    }
    
    public void ModificarLanguage(JTextField paramLanguage, JTextField paramCountrycode,JTextField paramIsofficial, JTextField parampercentage){
        
        setLanguage(paramLanguage.getText());  
        setCountryCode(paramCountrycode.getText());
        setIsOfficial(paramIsofficial.getText());
        setPercentage(Float.parseFloat(parampercentage.getText()));
        
        conexion cn = new conexion();
        
        String consulta ="UPDATE world.countrylanguage set countrylanguage.isofficial = ? ,countrylanguage.percentage= ?  WHERE countrylanguage.language= ? AND countrylanguage.countrycode = ? ;";
        
        try {
            CallableStatement cs;
            
            cs = cn.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getCountryCode());
            cs.setString(2, getLanguage());
            cs.setInt(3,Integer.parseInt(paramIsofficial.getText()));
            cs.setFloat(4, getPercentage());
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Modificación exitosa");
    
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "No se logro modificar, error: " + e.toString());
        }
        
        
    
    }
    
    public void EliminarCountryLanguage(JTextField paramLanguage , JTextField paramCountrycode){
    
        setLanguage(paramLanguage.getText()); 
        setCountryCode(paramCountrycode.getText());
        
        conexion cn = new conexion();
        
        String Consulta= "DELETE FROM world.CountryLanguage WHERE countrylanguage.language= ? and countrylanguage.CountryCode= ?";
        
        try {
            CallableStatement cs;
            cs = cn.establecerConexion().prepareCall(Consulta);
            
            cs.setString(1,getLanguage());
            cs.setString(2,getCountryCode());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamente el Country Language");
               
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        }
    
    }
}
