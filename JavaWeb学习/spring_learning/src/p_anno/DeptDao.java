package cn.itcast.b_anno;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * dao实现，使用Spring对jdbc支持功能
 * @author Jie.Yuan
 */
@Repository
public class DeptDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public void save(Dept dept){
		String sql = "insert into t_dept (deptName) values(?)";
		jdbcTemplate.update(sql,dept.getDeptName());
	}
}