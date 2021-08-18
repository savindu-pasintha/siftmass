package softmass;

import com.mysql.jdbc.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ListFormData extends Database {

    public DefaultTableModel getModelDataToJtTable() throws SQLException {
        super.Database();  //1-  connect database connection 
        super.view("SELECT *FROM product_details");//2-  sql query put preparestatement 

        rs = prepareSatement.executeQuery();

        model = new DefaultTableModel();

        rss = (ResultSetMetaData) rs.getMetaData();
        model.addColumn("ID");
        model.addColumn("BRAND");
        model.addColumn("PRODUCT");
        model.addColumn("SELLING-PRICE");
        model.addColumn("PURCHASE-PRICE");
        model.addColumn("SOLD-QTY");
        model.addColumn("TOTAL-PURCHASE");
        model.addColumn("TOTAL-PROFIT");
        model.addColumn("Profit per item %");
        model.setRowCount(0);

        int c = rss.getColumnCount();

        while (rs.next()) {
            Vector v2 = new Vector();

            {// database eke table wala colum piliwel oni ape table ek anuwa data retrive krnn  // for(int a=1; a<=c; a++)
                v2.add(rs.getString("id"));
                v2.add(rs.getString("brand_id"));
                v2.add(rs.getString("product_name"));
                v2.add(rs.getString("selling_price"));
                v2.add(rs.getString("purchase_price"));
                v2.add(rs.getString("sold_qty"));

            }
            double qty = Double.valueOf(rs.getString("sold_qty"));
            double sprice = Double.valueOf(rs.getString("selling_price"));
            double pprice = Double.valueOf(rs.getString("purchase_price"));
            double total = qty * sprice;
            v2.add(total);

            double profit = total - (qty * pprice);
            v2.add(profit);

            double precentage = ((sprice - pprice ) / pprice) * 100;
            if (15 <= precentage) {
                v2.add(precentage + "%");
            } else {
                v2.add("< 15%");
            }

            //System.out.println(v2);
            model.addRow(v2);
        }
        super.Connectionclose(); //  4-  close the connection  
        return model;
    }

}
