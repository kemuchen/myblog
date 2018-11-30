package cn.muchen.framework.dao.handler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.muchen.framework.util.Util;

/**
 * 
 * @ClassName:：BlobTypeHandler 
 * @Description： 自定义mybatis blob字段处理
 * @author ：柯雷
 * @date ：2018年9月28日 下午2:54:36 
 *
 */
public class BlobTypeHandler extends BaseTypeHandler<String> {

	/**
	 * @Description 日志打印对象 
	 */
	Logger logger = LoggerFactory.getLogger(BlobTypeHandler.class);
	
	/**
	 * <p>Title：setNonNullParameter</p> 
	 * <p>Description：insert或者update时前处理blob字段 </p> 
	 * @param ps
	 * @param i
	 * @param parameter
	 * @param jdbcType
	 * @throws SQLException 
	 * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {
		logger.info("【BlobTypeHandler】insert或者update时处理blob字段:");
		//声明一个输入流对象
        ByteArrayInputStream bis = null;  
        try {  
            //把字符串转为字节流
            bis = new ByteArrayInputStream(parameter.getBytes("gbk"));  
        } catch (Exception e) {  
        	logger.error("【BlobTypeHandler】insert或者update处理blob字段出错，错误原因为：" + e);
            throw new RuntimeException("Blob Encoding Error!");  
        } finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					logger.error("【BlobTypeHandler】insert或者update处理blob字段出错，错误原因为：" + e);
		            throw new RuntimeException("Blob Encoding Error!");
				}
			}
		}
        ps.setBinaryStream(i, bis, Util.getDbLength(parameter));
	}

	/**
	 * <p>Title：getNullableResult</p> 
	 * <p>Description： 查询成功后处理blob字段</p> 
	 * @param rs
	 * @param columnName
	 * @return
	 * @throws SQLException 
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, java.lang.String)
	 */
	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		logger.info("【BlobTypeHandler】query查询时处理blob字段");
		Blob blob = (Blob) rs.getBlob(columnName);  
        byte[] returnValue = null;  
        if (null != blob) {  
            returnValue = blob.getBytes(1, (int) blob.length());  
        }  
        try {  
            //将取出的流对象转为utf-8的字符串对象
            return new String(returnValue, "gbk");  
        } catch (Exception e) {  
        	logger.error("【BlobTypeHandler】查询处理blob字段出错，错误原因为：" + e);
            throw new RuntimeException("Blob Encoding Error!");
        }  
    }
	
	/**
	 * <p>Title：getNullableResult</p> 
	 * <p>Description： 查询成功后处理blob字段 </p> 
	 * @param rs
	 * @param columnIndex
	 * @return
	 * @throws SQLException 
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, int)
	 */
	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		logger.info("【BlobTypeHandler】query查询时处理blob字段");
		Blob blob = (Blob) rs.getBlob(columnIndex); 
        byte[] returnValue = null;  
        if (null != blob) {  
            returnValue = blob.getBytes(1, (int) blob.length());  
        }  
        try {  
            //将取出的流对象转为utf-8的字符串对象
            return new String(returnValue, "gbk");  
        } catch (Exception e) {  
        	logger.error("【BlobTypeHandler】查询处理blob字段出错，错误原因为：" + e);
            throw new RuntimeException("Blob Encoding Error!");
        }
	}

	/**
	 * <p>Title：getNullableResult</p> 
	 * <p>Description：  查询成功后处理blob字段</p> 
	 * @param cs
	 * @param columnIndex
	 * @return
	 * @throws SQLException 
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement, int)
	 */
	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		logger.info("【BlobTypeHandler】query查询时处理blob字段");
		Blob blob = (Blob) cs.getBlob(columnIndex); 
        byte[] returnValue = null;  
        if (null != blob) {  
            returnValue = blob.getBytes(1, (int) blob.length());  
        }  
        try {  
            //将取出的流对象转为utf-8的字符串对象
            return new String(returnValue, "gbk");  
        } catch (Exception e) {  
        	logger.error("【BlobTypeHandler】查询处理blob字段出错，错误原因为：" + e);
            throw new RuntimeException("Blob Encoding Error!");
        }
	}

}
