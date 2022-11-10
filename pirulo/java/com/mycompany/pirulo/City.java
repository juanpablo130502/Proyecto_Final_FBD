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

public class City {
    
    int id;
    String nameCity;
    String countryCode;
    String district;
    int population;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
    
    public void InsertarCity(JTextField paramNames, JTextField paramCC, JTextField paramDistrict, JTextField paramPopulation ){
       
        setNameCity(paramNames.getText());
        setCountryCode(paramCC.getText());
        setDistrict(paramDistrict.getText());
        setPopulation(Integer.parseInt(paramPopulation.getText()));
        
        conexion cn = new conexion();
        
        String consulta = "insert into world.city (Name,CountryCode,District,Population) values (?,?,?,?);";
        
        try {
            
            CallableStatement cs = cn.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getNameCity());
            cs.setString(2, getCountryCode());
            cs.setString(3, getDistrict());
            cs.setInt(4, getPopulation());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se ha insertado correctamente la ciudad");
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido insetar la ciudad error: " + e.toString());
        }
        
        
    }
    
    public void MostrarCiudades(JTable paramTablaTotalCity){
        
        conexion cn = new conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel> (modelo);
        paramTablaTotalCity.setRowSorter(OrdenarTabla);
        
        String  sql = "";
        
        modelo.addColumn("ID");
        modelo.addColumn("Name");
        modelo.addColumn("Country Code");
        modelo.addColumn("District");
        modelo.addColumn("Population");
        
        paramTablaTotalCity.setModel(modelo);
        
        sql = "select * from world.city;";
        
        String [] datos = new String[5];
        Statement st;
        
        try {
            st= cn.establecerConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                
                modelo.addRow(datos);
            }
            
            paramTablaTotalCity.setModel(modelo);
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
        }
        
    
    }
    
    public void SeleccionarCity(JTable paramTablaCity, JTextField paramID ,JTextField paramNames, JTextField paramCC, JTextField paramDistrict, JTextField paramPopulation){
    
        try {
            int fila = paramTablaCity.getSelectedRow();
            
            if (fila >=0){
                
                paramID.setText((paramTablaCity.getValueAt(fila, 0).toString()));
                paramNames.setText((paramTablaCity.getValueAt(fila, 1).toString()));
                paramCC.setText((paramTablaCity.getValueAt(fila, 2).toString()));
                paramDistrict.setText((paramTablaCity.getValueAt(fila, 3).toString()));
                paramPopulation.setText((paramTablaCity.getValueAt(fila, 4).toString()));
                
            }else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error:" + e.toString());
        }
    }
    
    public void ModificarCity (JTextField paramID,JTextField paramNames, JTextField paramCC, JTextField paramDistrict, JTextField paramPopulation){
        
        setId(Integer.parseInt(paramID.getText()));
        setNameCity(paramNames.getText());
        setCountryCode(paramCC.getText());
        setDistrict(paramDistrict.getText());
        setPopulation(Integer.parseInt(paramPopulation.getText()));
        
        conexion cn = new conexion();
        
        String consulta = "update world.city set world.city.Name = ?, world.city.CountryCode = ?, world.city.District = ?, world.city.Population = ? where world.city.ID = ?;";
        
        try {
            
            CallableStatement cs = cn.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getNameCity());
            cs.setString(2, getCountryCode());
            cs.setString(3, getDistrict());
            cs.setInt(4, getPopulation());
            cs.setInt(5, getId());
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Modificación exitosa");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido modificar, error: " + e.toString() );
        }
    }
    
    public void EliminarCity(JTextField paramID){
        
        setId(Integer.parseInt(paramID.getText()));
        
        conexion cn = new conexion();
        
        String consulta = "delete from world.city where world.city.ID = ?;";
        
        try {
            
            CallableStatement cs = cn.establecerConexion().prepareCall(consulta);
            cs.setInt(1, getId());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se ha eliminado el país exitosamente");

            
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "No se ha podido eliminar, error" + e.toString() );
        }
    }
    
    public void BuscarCityID(JTextField IdBusqueda, JTextField IdR, JTextField txtName, JTextField CountryCodeR, JTextField DistrictR, JTextField PopulationR){
        
        String consulta = "Select name,countrycode, district, population from city where city.ID = ?";
        
        conexion cn = new conexion();
        
        try {
            CallableStatement cs = cn.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, IdBusqueda.getText());
            cs.execute();
            
            ResultSet rs = cs.executeQuery();
            
            if (rs.next()) {
                
                JOptionPane.showMessageDialog(null, "Registro encontrado");
                
                txtName.setText(rs.getString("Name"));
                CountryCodeR.setText(rs.getString("CountryCode"));
                DistrictR.setText(rs.getString("District"));
                PopulationR.setText(rs.getString("Population"));     
                
            }else {
            
                JOptionPane.showMessageDialog(null, "Registro no encontrado");
                
                txtName.setText(null);
                CountryCodeR.setText(rs.getString(null));
                DistrictR.setText(rs.getString(null));
                PopulationR.setText(rs.getString(null));
                
            
            }
            
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "No se ha podido hacer la busqueda, error: " + e.toString() );
        }
        
        
    }
    
    
}
