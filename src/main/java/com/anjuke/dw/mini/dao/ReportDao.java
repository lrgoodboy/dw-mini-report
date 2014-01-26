package com.anjuke.dw.mini.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.anjuke.dw.mini.model.Report;

public class ReportDao extends JdbcDaoSupport {

    public Report findById(int id) throws DataAccessException {

        try {
            return getJdbcTemplate().queryForObject("SELECT a.id, a.name, b.true_name, a.receivers"
                    + " FROM mini_report a JOIN dw_monitor_user b ON a.owner_id = b.id"
                    + " WHERE a.id = ?",
                    new RowMapper<Report>() {

                        @Override
                        public Report mapRow(ResultSet rs, int rowNum)
                                throws SQLException {

                            Report report = new Report();
                            report.setId(rs.getInt("id"));
                            report.setName(rs.getString("name"));
                            report.setOwnerName(rs.getString("true_name"));
                            report.setReceivers(rs.getString("receivers"));
                            return report;
                        }

                    }, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int updateRecievers(int id, String receivers) throws DataAccessException {
        return getJdbcTemplate().update("UPDATE mini_report SET receivers = ? WHERE id = ?", receivers, id);
    }

}
