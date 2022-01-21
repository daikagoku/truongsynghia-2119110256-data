package com.example.admin.template;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.example.admin.mapper.CategoryMapper;
import com.example.admin.template.base.AdminTemplateBase;
import com.example.core.entity.CategoryEntity;

public class CategoryTemplate extends AdminTemplateBase{
	public CategoryTemplate() {
		this.tables = " category c";
	}
	public int put(CategoryEntity category) {
		category.setAlias(convert.getAlias(category.getTitle()));
		String SQL = "update "+this.tables 
				+ " set title = ?" 
				+ " set alias = ?" 
				+ " set parent_id = ?"
				+ " set status = ?"
				+ " set trash = ?"
				+ " where id = ?";
		int count = jdbcTemplateObject.update(SQL,
					category.getTitle(),
					category.getAlias(),
					category.getParentId(),
					category.isStatus(),
					category.isTrash(),
					category.getId());
		return count;
	}
	public int[] puts(List<CategoryEntity> categorys) {
		String SQL = "update "+this.tables 
				+ " set title = ?" 
				+ " set alias = ?" 
				+ " set parent_id = ?"
				+ " set status = ?"
				+ " set trash = ?"
				+ " where id = ?";
		int[] counts = jdbcTemplateObject.batchUpdate(SQL, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				categorys.get(i).setAlias(convert.getAlias(categorys.get(i).getTitle()));
				ps.setString(1, categorys.get(i).getTitle());
				ps.setString(2, categorys.get(i).getAlias());
				ps.setLong(3, categorys.get(i).getParentId());
				ps.setBoolean(4, categorys.get(i).isStatus());
				ps.setBoolean(5, categorys.get(i).isTrash());
				ps.setLong(6, categorys.get(i).getId());
				;
			}
			public int getBatchSize() {
				return categorys.size();
			}
		});
		return counts;
	}

	public int delete(long id) {
		String SQL = "delete from "+this.tables
				+"  where id = ?";
		int count = jdbcTemplateObject.update(SQL, id);
		return count;
	}
	public int[] deletes(long[] ids) {
		String SQL = "delete from "+this.tables
				+"  where id = ?";		
		int[] counts = jdbcTemplateObject.batchUpdate(SQL,
				new BatchPreparedStatementSetter() {
				   public void setValues(PreparedStatement ps, int i) throws SQLException {
					   ps.setLong(1, ids[i]);
				   }
				   public int getBatchSize() {
				      return ids.length;
				   }
				});
		return counts;
	}
	public int post(CategoryEntity category) {
		category.setAlias(convert.getAlias(category.getTitle()));
		String SQL = "insert into "+this.tables
				+"(title,alias,parent_id)"
				+"values(?,?,?)";
		int count = jdbcTemplateObject.update(SQL, 
				category.getTitle(),
				category.getAlias(),
				category.getParentId());
		return count;
	}
	public int[] posts(List<CategoryEntity> categorys) {
		String SQL = "insert into "+this.tables
				+"(title,alias,parent)"
				+"values(?,?,?)";		
		int[] counts = jdbcTemplateObject.batchUpdate(SQL,
				new BatchPreparedStatementSetter() {
				   public void setValues(PreparedStatement ps, int i) throws SQLException {
					    categorys.get(i).setAlias(convert.getAlias(categorys.get(i).getTitle()));						
					    ps.setString(1, categorys.get(i).getTitle());
					    ps.setString(2, categorys.get(i).getAlias());
					    if(categorys.get(i).getParentId() instanceof Long) {
						    ps.setLong(3,categorys.get(i).getParentId());
					    }else {
					    	ps.setNull(3, Types.NULL);
					    }
				   }
				   public int getBatchSize() {
				      return categorys.size();
				   }
				});
		return counts;
	}
	public List<CategoryEntity> get(long id) {
		String SQL = "select * "
				+" from  " +this.tables
				+" where id = ?";
		List<CategoryEntity> categorys = jdbcTemplateObject.query(SQL, new CategoryMapper(),new Object[] { id});
		return categorys;
	}
	public List<CategoryEntity> get(String alias) {
		String SQL = "select * "
				+" from  " +this.tables
				+" where alias = ?";
		List<CategoryEntity> categorys = jdbcTemplateObject.query(SQL, new CategoryMapper(),new Object[] { alias});
		return categorys;
	}
	public List<CategoryEntity> gets() {
		String SQL = "select * "
				+" from  " +this.tables;
		List<CategoryEntity> categorys = jdbcTemplateObject.query(SQL, new CategoryMapper());
		return categorys;
	}

}
