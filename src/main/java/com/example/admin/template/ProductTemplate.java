package com.example.admin.template;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.example.admin.mapper.ProductMapper;
import com.example.core.Convert;
import com.example.core.entity.ProductEntity;



public class ProductTemplate {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private Convert convert = new Convert();

	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}	
	public int getCount() {
//		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withFunctionName("f_get_count_product");
//		count = jdbcCall.executeFunction(Integer.class);
//		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("pr_get_count_product");
		String SQL = "select count(*) as count "
				+"from product p";
		Map<String, Object> count = jdbcTemplateObject.queryForMap(SQL);
		return (int) count.get("count");

	}
	public int put(ProductEntity product) {
		product.setAlias(convert.getAlias(product.getTitle()));
		String SQL = "update product "
				+" set title = ?"
				+" set alias = ?"
				+" set content = ?"
				+" set description = ?"
				+"  where id = ?";
		int count = jdbcTemplateObject.update(SQL, 
				product.getTitle(),
				product.getAlias(),
				product.getContent(),
				product.getDescription(),
				product.getId());
		
		return count;
	}
	public int[] puts(List<ProductEntity> products) {
		String SQL = "update product "
				+" set title = ?"
				+" set alias = ?"
				+" set content = ?"
				+" set description = ?"
				+"  where id = ?";
				
		int[] counts = jdbcTemplateObject.batchUpdate(SQL,
				new BatchPreparedStatementSetter() {
				   public void setValues(PreparedStatement ps, int i) throws SQLException {
						products.get(i).setAlias(convert.getAlias(products.get(i).getTitle()));
					    ps.setString(1, products.get(i).getTitle());
					    ps.setString(2, products.get(i).getAlias());
					    ps.setString(3, products.get(i).getContent());
					    ps.setString(4, products.get(i).getDescription());
					    ps.setLong(5, products.get(i).getId());;
				   }
				   public int getBatchSize() {
				      return products.size();
				   }
				});
		return counts;
	}
	public int delete(long id) {
		String SQL = "delete from product "
				+"  where id = ?";
		int count = jdbcTemplateObject.update(SQL, id);
		return count;
	}
	public int[] deletes(long[] ids) {
		String SQL = "delete from product "
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
	public int post(ProductEntity product) {
		product.setAlias(convert.getAlias(product.getTitle()));
		String SQL = "insert into product "
				+"(title,alias,content,description)"
				+"values(?,?,?,?)";
		int count = jdbcTemplateObject.update(SQL, 
				product.getTitle(),
				product.getAlias(),
				product.getContent(),
				product.getDescription());
		return count;
	}
	public int[] posts(List<ProductEntity> products) {
		String SQL = "insert into product "
				+"(title,alias,content,description)"
				+"values(?,?,?,?)";			
		int[] counts = jdbcTemplateObject.batchUpdate(SQL,
				new BatchPreparedStatementSetter() {
				   public void setValues(PreparedStatement ps, int i) throws SQLException {
						products.get(i).setAlias(convert.getAlias(products.get(i).getTitle()));						
					    ps.setString(1, products.get(i).getTitle());
					    ps.setString(2, products.get(i).getAlias());
					    ps.setString(3, products.get(i).getContent());
					    ps.setString(4, products.get(i).getDescription());
				   }
				   public int getBatchSize() {
				      return products.size();
				   }
				});
		return counts;
	}
	public List<ProductEntity> get(long id) {
		String SQL = "select * "
				+"from product p"
				+" where p.id = ?";
		List<ProductEntity> products = jdbcTemplateObject.query(SQL, new ProductMapper(),new Object[] { id});
		return products;
	}
	public List<ProductEntity> get(String alias) {
		String SQL = "select * "
				+"from product p"
				+" where p.alias = ?";
		List<ProductEntity> products = jdbcTemplateObject.query(SQL, new ProductMapper(),new Object[] { alias});
		return products;
	}
	public List<ProductEntity> getAll() {
		String SQL = "select * "
				+" from product ";
		List<ProductEntity> products = jdbcTemplateObject.query(SQL, new ProductMapper());
		return products;
	}

}
