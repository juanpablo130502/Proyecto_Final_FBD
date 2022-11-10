
package com.mycompany.pirulo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class Country {
    
    String code;
    String name; 
    String continent;
    String region;
    float superfaceArea;
    int indepYear;
    int population;
    float lifeExpectancy;
    float gnp;
    float gnpOLD;
    String localName;
    String govermentForm;
    String headOfState;
    int capital;
    String code2;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public float getSuperfaceArea() {
        return superfaceArea;
    }

    public void setSuperfaceArea(float superfaceArea) {
        this.superfaceArea = superfaceArea;
    }

    public int getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(int indepYear) {
        this.indepYear = indepYear;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public float getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(float lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public float getGnp() {
        return gnp;
    }

    public void setGnp(float gnp) {
        this.gnp = gnp;
    }

    public float getGnpOLD() {
        return gnpOLD;
    }

    public void setGnpOLD(float gnpOLD) {
        this.gnpOLD = gnpOLD;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovermentForm() {
        return govermentForm;
    }

    public void setGovermentForm(String govermentForm) {
        this.govermentForm = govermentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }
    
    public void InsertarCountry(JTextField paramCode, JTextField paramName, JTextField paramConti, JTextField paramRegi, JTextField paramSuperAr, JTextField paramIndeY, JTextField paramPopu, JTextField paramLifeExpe, JTextField paramGnp, JTextField paramGnpOLD, JTextField paramLocalName, JTextField paramGovForm, JTextField paramHeadOfSta, JTextField paramCap, JTextField paramCode2){
        
        setCode(paramCode.getText());
        setName(paramName.getText());
        setContinent(paramConti.getText());
        setRegion(paramRegi.getText());
        setSuperfaceArea(Float.parseFloat(paramSuperAr.getText()));
        setIndepYear(Integer.parseInt(paramIndeY.getText()));
        setPopulation(Integer.parseInt(paramPopu.getText()));
        setLifeExpectancy(Float.parseFloat(paramLifeExpe.getText()));
        setGnp(Float.parseFloat(paramGnp.getText()));
        setGnpOLD(Float.parseFloat(paramGnpOLD.getText()));
        setLocalName(paramLocalName.getText());
        setGovermentForm(paramGovForm.getText());
        setHeadOfState(paramHeadOfSta.getText());
        setCapital(Integer.parseInt(paramCap.getText()));
        setCode2(paramCode2.getText());
        
        conexion cn = new conexion();
        
        String consulta ="insert into world.country (Code ,Name,Continent,Region,SurfaceArea,IndepYear,Population,LifeExpectancy,GNP,GNPOld,LocalName,GovernmentForm,HeadOfState,Capital,Code2) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        
        try {
            
            CallableStatement cs = cn.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getCode());
            cs.setString(2, getName());
            cs.setString(3, getContinent());
            cs.setString(4, getRegion());
            cs.setFloat(5, getSuperfaceArea());
            cs.setInt(6, getIndepYear());
            cs.setInt(7, getPopulation());
            cs.setFloat(8, getLifeExpectancy());
            cs.setFloat(9, getGnp());
            cs.setFloat(10, getGnpOLD());
            cs.setString(11, getLocalName());
            cs.setString(12, getGovermentForm());
            cs.setString(13, getHeadOfState());
            cs.setInt(14, getCapital());
            cs.setString(15, getCode2());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se ha insertado correctamente el país");
 
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "No se ha podido insetar el país, error: " + e.toString());
           
        }
        
    }
    
    public void MostrarCountry (JTable paramTablaTotalCountry){
        
        conexion cn = new conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel> (modelo);
        paramTablaTotalCountry.setRowSorter(OrdenarTabla);
        
        String sql = "";
        
        modelo.addColumn("Code");
        modelo.addColumn("Name");
        modelo.addColumn("Continent");
        modelo.addColumn("Region");
        modelo.addColumn("SurfaceArea");
        modelo.addColumn("IndepYear");
        modelo.addColumn("Population");
        modelo.addColumn("LifeExpectancy");
        modelo.addColumn("GNP");
        modelo.addColumn("GNPOld");
        modelo.addColumn("LocalName");
        modelo.addColumn("GovernmentForm");
        modelo.addColumn("HeadOfState");
        modelo.addColumn("Capital");
        modelo.addColumn("Code2");
        
        paramTablaTotalCountry.setModel(modelo);
        
        sql = "select * from world.country;";
        
        String [] datos = new String[15];
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
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                datos[7]=rs.getString(8);
                datos[8]=rs.getString(9);
                datos[9]=rs.getString(10);
                datos[10]=rs.getString(11);
                datos[11]=rs.getString(12);
                datos[12]=rs.getString(13);
                datos[13]=rs.getString(14);
                datos[14]=rs.getString(15);
                
                modelo.addRow(datos);
            }
            
            paramTablaTotalCountry.setModel(modelo);
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
        }
    }
    
    public void SeleccionarCountry (JTable paramTablaCountry, JTextField paramCode, JTextField paramName, JTextField paramConti, JTextField paramRegi, JTextField paramSuperAr, JTextField paramIndeY, JTextField paramPopu, JTextField paramLifeExpe, JTextField paramGnp, JTextField paramGnpOLD, JTextField paramLocalName, JTextField paramGovForm, JTextField paramHeadOfSta, JTextField paramCap, JTextField paramCode2 ){
        
        try {
            int fila = paramTablaCountry.getSelectedRow();
            
            if (fila >=0) {
                
                paramCode.setText((paramTablaCountry.getValueAt(fila, 0).toString()));
                paramName.setText((paramTablaCountry.getValueAt(fila, 1).toString()));
                paramConti.setText((paramTablaCountry.getValueAt(fila, 2).toString()));
                paramRegi.setText((paramTablaCountry.getValueAt(fila, 3).toString()));
                paramSuperAr.setText((paramTablaCountry.getValueAt(fila, 4).toString()));
                
                if(paramTablaCountry.getValueAt(fila, 5) == null){
                    paramPopu.setText(null);
                }
                else{
                
                    paramIndeY.setText((paramTablaCountry.getValueAt(fila, 5).toString()));
                }
                
                if(paramTablaCountry.getValueAt(fila, 6) == null){
                    paramPopu.setText(null);
                }
                else{
                
                    paramPopu.setText((paramTablaCountry.getValueAt(fila, 6).toString()));
                }
                
                if(paramTablaCountry.getValueAt(fila, 7) == null){
                    paramPopu.setText(null);
                }
                else{
                
                    paramLifeExpe.setText((paramTablaCountry.getValueAt(fila, 7).toString()));
                }
                
                paramGnp.setText((paramTablaCountry.getValueAt(fila, 8).toString()));
                
                
                if(paramTablaCountry.getValueAt(fila, 9) == null){
                    paramPopu.setText(null);
                }
                else{
                
                    paramGnpOLD.setText((paramTablaCountry.getValueAt(fila, 9).toString()));
                }

                paramLocalName.setText((paramTablaCountry.getValueAt(fila, 10).toString()));
                paramGovForm.setText((paramTablaCountry.getValueAt(fila, 11).toString()));
                
                if(paramTablaCountry.getValueAt(fila, 12) == null){
                    paramPopu.setText(null);
                }
                else{
                
                    paramHeadOfSta.setText((paramTablaCountry.getValueAt(fila, 12).toString()));
                }
                
                if(paramTablaCountry.getValueAt(fila, 13) == null){
                    paramPopu.setText(null);
                }
                else{
                
                    paramCap.setText((paramTablaCountry.getValueAt(fila, 13).toString()));
                }
                
                paramCode2.setText((paramTablaCountry.getValueAt(fila, 14).toString()));
                
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }
    
    public void ModificarCountry (JTextField paramCode, JTextField paramName, JTextField paramConti, JTextField paramRegi, JTextField paramSuperAr, JTextField paramIndeY, JTextField paramPopu, JTextField paramLifeExpe, JTextField paramGnp, JTextField paramGnpOLD, JTextField paramLocalName, JTextField paramGovForm, JTextField paramHeadOfSta, JTextField paramCap, JTextField paramCode2){
    
        setCode(paramCode.getText());
        setName(paramName.getText());
        setContinent(paramConti.getText());
        setRegion(paramRegi.getText());
        setSuperfaceArea(Float.parseFloat(paramSuperAr.getText()));
        setIndepYear(Integer.parseInt(paramIndeY.getText()));
        setPopulation(Integer.parseInt(paramPopu.getText()));
        setLifeExpectancy(Float.parseFloat(paramLifeExpe.getText()));
        setGnp(Float.parseFloat(paramGnp.getText()));
        setGnpOLD(10);
        setLocalName(paramLocalName.getText());
        setGovermentForm(paramGovForm.getText());
        setHeadOfState(paramHeadOfSta.getText());
        setCapital(Integer.parseInt(paramCap.getText()));
        setCode2(paramCode2.getText());
        
        conexion cn = new conexion();
        
        String consulta = "update world.country set world.country.Code = ? ,world.country.Name = ?, world.country.Continent = ?, world.country.Region = ?, world.country.SurfaceArea = ?, world.country.IndepYear = ?, world.country.Population = ?, world.country.LifeExpectancy = ?, world.country.GNP = ?, world.country.GNPOld = ?, world.country.LocalName = ?, world.country.GovernmentForm = ?, world.country.HeadOfState = ?, world.country.Capital = ?, world.country.Code2 = ? where world.country.Code = ?;";

        try {
            
            CallableStatement cs = cn.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getCode());
            cs.setString(2, getName());
            cs.setInt(3,Integer.parseInt(paramConti.getText()));
            cs.setString(4, getRegion());
            cs.setFloat(5, getSuperfaceArea());
            cs.setInt(6, getIndepYear());
            cs.setInt(7, getPopulation());
            cs.setFloat(8, getLifeExpectancy());
            cs.setFloat(9, getGnp());
            cs.setFloat(10, 10);
           cs.setString(11, getLocalName());
            cs.setString(12, getGovermentForm());
            cs.setString(13, getHeadOfState());
            cs.setInt(14, getCapital());
            cs.setString(15, getCode2());
            cs.setString(16, getCode());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificación exitosa");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido modificar, error: " + e.toString() );
        }
    
    }
    
    public void EliminarCountry (JTextField paramCode){
        
        setCode(paramCode.getText());
        
        conexion cn = new conexion();
        
        String consulta = "delete from world.country where world.country.code = ?;";
        
        try {
            
            CallableStatement cs = cn.establecerConexion().prepareCall(consulta);
            cs.setString(1, getCode());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se ha eliminado el país exitosamente");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido eliminar, error" + e.toString());
        }
    }
    
    public void BuscarCountry (JTextField codeBusqueda, JTextField codeR, JTextField nameR, JTextField continentR, JTextField regionR, JTextField surfaR, JTextField IndepR, JTextField popuR, JTextField lifeExR, JTextField gnpR, JTextField gnpOLDr, JTextField localNamR, JTextField governFormR, JTextField headOStateR, JTextField capiR, JTextField code2R){
        
        String consulta = "Select code,name,continent,region,surfaceArea,IndepYear,population,LifeExpectancy,gnp,gnpOld,localName,GovernmentForm,headOfState,Capital,code2 from country where country.code = ?";
        
        conexion cn = new conexion();
        
        try {
            CallableStatement cs = cn.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, codeBusqueda.getText());
            cs.execute();
            
            ResultSet rs = cs.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Registro encontrado");
                
                nameR.setText(rs.getString("Name"));
                continentR.setText(rs.getString("Continent"));
                regionR.setText(rs.getString("Region"));
                surfaR.setText(rs.getString("SurfaceArea"));
                IndepR.setText(rs.getString("IndepYear"));
                popuR.setText(rs.getString("population"));
                lifeExR.setText(rs.getString("LifeExpectancy"));
                gnpR.setText(rs.getString("gnp"));
                gnpOLDr.setText(rs.getString("gnpOld"));
                localNamR.setText(rs.getString("LocalName"));
                governFormR.setText(rs.getString("GovernmentForm"));
                headOStateR.setText(rs.getString("headOfState"));
                capiR.setText(rs.getString("Capital"));
                code2R.setText(rs.getString("code2"));
            }else{
                JOptionPane.showMessageDialog(null, "Registro no encontrado");
                
                nameR.setText(rs.getString(null));
                continentR.setText(rs.getString(null));
                regionR.setText(rs.getString(null));
                surfaR.setText(rs.getString(null));
                IndepR.setText(rs.getString(null));
                popuR.setText(rs.getString(null));
                lifeExR.setText(rs.getString(null));
                gnpR.setText(rs.getString(null));
                gnpOLDr.setText(rs.getString(null));
                localNamR.setText(rs.getString(null));
                governFormR.setText(rs.getString(null));
                headOStateR.setText(rs.getString(null));
                capiR.setText(rs.getString(null));
                code2R.setText(rs.getString(null));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido hacer la busqueda, error: " + e.toString() );
        }
    
    }
}



