package ru.retail.service;

import ru.retail.Storage;

import java.sql.*;
import java.util.HashMap;
import java.util.Map.Entry;

public class MoneyService {

    public static float getClientBalance(int clientId) {
        if (clientId != 0 ) {
            try(Statement statement = Storage.getInstance().getConn().createStatement()) {
                String sqlCommand = "select sum(sum) sum from wallets where client = " + clientId + ";";
                System.out.println(sqlCommand);
                ResultSet resultSet = statement.executeQuery(sqlCommand);
                if (resultSet.next()) {
                    return resultSet.getFloat("sum");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    public static int enterMoney(String clientId, String sum, String comments) {
        if (clientId != null) {
            if (!clientId.isEmpty()) {
                try(Statement statement = Storage.getInstance().getConn().createStatement()) {
                    String sqlCommand = "insert into wallets (client, sum, comment) values (" + clientId + ", " + sum + ", '" + comments + "');";
                    System.out.println(sqlCommand);
                    return statement.executeUpdate(sqlCommand);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public static boolean enterOrder(String clientId, String sum, String comments, String address, HashMap<Integer, Integer> basketWithQuantity) throws SQLException {
        if (clientId != null) {
            if (!clientId.isEmpty()) {
                Connection conn = Storage.getInstance().getConn();
                conn.setAutoCommit(false);
                StringBuffer sqlCommand = new StringBuffer();
                sqlCommand.append("select max(id) from orders;");
                sqlCommand.append("insert into orders (id, client, sum, address, comments, status) ");
                sqlCommand.append("values (10, " + clientId + ", " + sum + ",'" + address + "', '" + comments + "', 1)");
//                    sqlCommand.append("insert into orderComposition (orderId, productId, quantity) values ");
//                    boolean first = true;
//                    for (Entry entry: basketWithQuantity.entrySet()) {
//                        if (first) {
//                            first = false;
//                        } else {
//                            sqlCommand.append(",");
//                        }
//                        sqlCommand.append("(@maxid,");
//                        sqlCommand.append(entry.getKey().toString()).append(",");
//                        sqlCommand.append(entry.getValue().toString());
//                        sqlCommand.append(")");
//                    }
//                    sqlCommand.append(";");
//                    sqlCommand.append("insert into wallets (client, sum, comment) values (" + clientId + ", -" + sum + ", concat('Оплата заказа ', '2'));");
                try(Statement statement = conn.createStatement()) {
                    System.out.println(sqlCommand);
                    statement.addBatch("select max(id) from orders");
                    statement.addBatch("insert into orders (id, client, sum, address, comments, status) values (10," + clientId + ", " + sum + ",'" + address + "', '" + comments + "', 1)");
                    int[]  res = statement.executeBatch();
                    conn.commit();
                    return res.length != 0;
                } catch (SQLException e) {
                    e.printStackTrace();
                    conn.rollback();
                }
//                try(Statement statement = Storage.getInstance().getConn().createStatement()) {
//                    StringBuffer sqlCommand = new StringBuffer();
//                    sqlCommand.append("start transaction;");
//                    sqlCommand.append("select @maxid:=ifnull(max(id),0)+1 from orders;");
//                    sqlCommand.append("insert into orders (id, client, sum, address, comments, status) ");
//                    sqlCommand.append("values (@maxid, " + clientId + ", " + sum + ",'" + address + "', '" + comments + "', 1); ");
////                    sqlCommand.append("insert into orderComposition (orderId, productId, quantity) values ");
////                    boolean first = true;
////                    for (Entry entry: basketWithQuantity.entrySet()) {
////                        if (first) {
////                            first = false;
////                        } else {
////                            sqlCommand.append(",");
////                        }
////                        sqlCommand.append("(@maxid,");
////                        sqlCommand.append(entry.getKey().toString()).append(",");
////                        sqlCommand.append(entry.getValue().toString());
////                        sqlCommand.append(")");
////                    }
////                    sqlCommand.append(";");
////                    sqlCommand.append("insert into wallets (client, sum, comment) values (" + clientId + ", -" + sum + ", concat('Оплата заказа ', '2'));");
//                    sqlCommand.append("commit;");
//                    System.out.println(sqlCommand);
//                    return statement.execute(sqlCommand.toString());
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
            }
        }
        return false;
    }
}