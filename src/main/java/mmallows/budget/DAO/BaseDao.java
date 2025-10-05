package mmallows.budget.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import mmallows.budget.Models.Entity;

public abstract class BaseDao<T extends Entity> {
    protected String tableName;
    protected String url = "jdbc:sqlite:budget.db";

    public abstract String getTableName();

    // Maps the ResultSet to and Entity object
    public abstract T mapRow(ResultSet rs) throws SQLException;

    // Maps the Entity object to a HashMap of column name, value
    public abstract HashMap<String, Object> mapObject(T entity);

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public List<T> fetchAll() {
        List<T> list = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<T> findById(Integer id) {
        return findByColumn("id", id, false);
    }

    public List<T> findById(Integer id, boolean includeInvalid) {
        return findByColumn("id", id, includeInvalid);
    }

    public List<T> findByColumn(String columnName, Object value, boolean includeInvalid) {
        List<T> list = new ArrayList<>();
        String sql = "SELECT * FROM " + this.getTableName() + " WHERE " + columnName + " = ?";

        if (includeInvalid) {
            sql += " AND (valid = 1)";
        }

        try (Connection conn = getConnection();
                java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, value);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<T> findByColumns(HashMap<String, Object> conditions, boolean includeInvalid) {
        List<T> list = new ArrayList<>();
        if (conditions == null || conditions.isEmpty())
            return list;

        String whereClause = conditions.keySet().stream()
                .map(col -> col + " = ?")
                .collect(Collectors.joining(" AND "));
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + whereClause;

        try (Connection conn = getConnection();
                java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int index = 1;
            for (String col : conditions.keySet()) {
                pstmt.setObject(index++, conditions.get(col));
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(T entity) {
        HashMap<String, ?> map = mapObject(entity);
        Set<String> columns = map.keySet();

        String columnNames = String.join(", ", columns);
        String placeholders = String.join(", ", columns.stream().map(col -> "?").toArray(String[]::new));

        String sql = "INSERT INTO " + this.getTableName()
                + " (" + columnNames + ") VALUES (" + placeholders + ")";

        try (var conn = getConnection(); var pstmt = conn.prepareStatement(sql)) {
            int index = 1;
            for (String column : columns) {
                pstmt.setObject(index++, map.get(column));
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(T entity) {
        HashMap<String, Object> map = mapObject(entity);
        map.remove("id");
        Set<String> columns = map.keySet();

        String sql = "UPDATE " + this.getTableName()
                + " SET " + columns.stream().map(col -> col + " = ?").collect(Collectors.joining(", "))
                + " WHERE id = ?";

        try (var conn = getConnection(); var pstmt = conn.prepareStatement(sql)) {
            int index = 1;
            for (String column : columns) {
                pstmt.setObject(index++, map.get(column));
            }
            pstmt.setObject(index, entity.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
