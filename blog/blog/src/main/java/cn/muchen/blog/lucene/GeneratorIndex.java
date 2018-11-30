package cn.muchen.blog.lucene;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;
import cn.muchen.blog.server.service.inter.ArticleManagerService;


/**
 * 
 * @ClassName:：GenIndex 
 * @Description： 生成索引
 * @author ：柯雷
 * @date ：2018年11月27日 下午1:31:16 
 *
 */
@Component
public class GeneratorIndex {
	
	/** 日志打印对象 */
	private static final Logger logger = LoggerFactory.getLogger(GeneratorIndex.class);
	
	/**
	 * 索引生成次数
	 */
	private static long createCount = 0;
	
	/**
	 * 文章信息处理service
	 */
	@Autowired
	ArticleManagerService articleManagerServiceImpl;
	
	/**
	 * @throws IOException 
	 * @Title：genIndex 
	 * @Description：生成索引
	 * @param ：@param path 索引文件存放路径
	 * @return ：void 
	 * @throws
	 */
	public void genIndex(String path) throws IOException {
		// 当前时间戳
		long start = System.currentTimeMillis();
		
		// 索引生成次数统计
		createCount++;
		
		// 第一次建立索引，删除原有的文件，并重新建立文件夹
		if (createCount == 1) {
			new File(path).deleteOnExit();
			new File(path).mkdirs();
		}
		
		//索引存放的位置，设置在当前目录中
	    Directory directory = FSDirectory.open(new File(path));
	    
		// 中文分词器
		Analyzer analyzer = new IKAnalyzer();
		
		//创建索引写入配置
	    IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_47, analyzer);

	    //创建索引写入对象
	    IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

	    //创建Document对象，存储索引
	    Document doc;
	    
	    List<Map<String, Object>> articleList = this.genData();
	    
	    try {
		    /**
		     * 循环文章列表建立索引
		     */
		    for (Map<String, Object> article : articleList) {
		    	doc = new Document();
		    	// 文章标题
		    	Field f_TITLE = new TextField("TITLE", (String) article.get("TITLE"), Field.Store.YES);
		    	doc.add(f_TITLE);
		    	// 文章作者
		    	Field f_AUTHOR = new TextField("AUTHOR", (String) article.get("AUTHOR"), Field.Store.YES);
		    	doc.add(f_AUTHOR);
		    	// 文章摘要
		    	Field f_ABSTRACT = new TextField("ABSTRACT", (String) article.get("ABSTRACT"), Field.Store.YES);
		    	doc.add(f_ABSTRACT);
		    	// 文章内容
		    	Field f_CONTENT = new TextField("CONTENT", (String) article.get("CONTENT"), Field.Store.YES);
		    	doc.add(f_CONTENT);
		    	// 文章id
		    	Field f_ARTICLEID = new TextField("ARTICLEID", "" + article.get("ARTICLEID"), Field.Store.YES);
		    	doc.add(f_ARTICLEID);
		    	
		    	if (createCount == 1) {
		    		indexWriter.addDocument(doc);
		    	} else {
		    		Term term = new Term("ARTICLEID", "" + article.get("ARTICLEID"));
		    		indexWriter.updateDocument(term, doc);
		    	}
		    	
		    	indexWriter.commit();
		    }
		    
		    logger.info("==【GenIndex】第" + createCount + "次更新索引，条 数："
					+ articleList.size());
			long costTime = System.currentTimeMillis() - start;
			logger.info("==【GenIndex】更新索引用时：" + costTime + " ms");
	    }  catch (Exception e) {
			logger.error("【GeneratorIndex】建立索引失败" + e);
			throw e;
		} finally {
			if (indexWriter != null) {
				indexWriter.close();
			}
		}
	}
	
	/**
	 * @Title：genData 
	 * @Description：从数据库查询数据
	 * @param ：@return 
	 * @return ：List<Map<String,Object>> 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> genData() {
		logger.info("【GeneratorIndex】从数据库获取索引数据进行建立索引");
		
		// 查询条件，查询所有有效且发布的文章
		Map<String, Object> params = new HashMap<>();
		params.put("SFFB", "1");
		params.put("VALIDATED", "1");
		
		Map<String, Object> articleLists = articleManagerServiceImpl.getArticlePage(params);
		
		return (List<Map<String, Object>>) articleLists.get("data");
	}
}
