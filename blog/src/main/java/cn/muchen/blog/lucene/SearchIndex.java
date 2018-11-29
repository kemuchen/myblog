package cn.muchen.blog.lucene;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
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
 * @ClassName:：SearchIndex 
 * @Description： 查询索引
 * @author ：柯雷
 * @date ：2018年11月27日 下午2:25:37 
 *
 */
@Component
public class SearchIndex {
	
	/** 日志打印对象 */
	private static final Logger logger = LoggerFactory.getLogger(SearchIndex.class);

	/**
	 * 文章信息处理service
	 */
	@Autowired
	ArticleManagerService articleManagerServiceImpl;
	
	/**
	 * @throws Exception 
	 * @Title：searchArticle 
	 * @Description：检索文章
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	public Map<String, Object> searchArticle(String queryContent, String path) throws Exception {
		logger.info("【SearchIndex.searchArticle】检索文章：" + queryContent);
		
		try {
			//queryContent += "*";
			
			// 中文分词器
			Analyzer analyzer = new IKAnalyzer();
			
			QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47,
					new String[] {"TITLE", "AUTHOR", "ABSTRACT", "CONTENT"}, analyzer);
			
			Query query = queryParser.parse(queryContent);
		    
		    //索引存放的位置，设置在当前目录中
		    Directory directory = FSDirectory.open(new File(path));
		    
		    //创建索引的读取器
		    IndexReader indexReader = DirectoryReader.open(directory);

		    //创建一个索引的查找器，来检索索引库
		    IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		    
		    //执行查询，并打印查询到的记录数
		    TopDocs topDocs = indexSearcher.search(query, 100);

		    //打印查询到的记录数
		    logger.info("总共查询到" + topDocs.totalHits + "个文档");
		    
		    // 取的文章id串
		    String articeIds = "";
		    for (ScoreDoc scoreDoc : topDocs.scoreDocs) {

		        //取得对应的文档对象
		        Document document = indexSearcher.doc(scoreDoc.doc);
		        
		        articeIds += document.get("ARTICLEID") + ",";
		    }
		    
		    if (topDocs.totalHits == 0) {
		    	articeIds = "0";
		    }
		    
		    // 查询条件，id串
		    Map<String, Object> params = new HashMap<>();
		    params.put("ARTICLEID", articeIds);
		    
		    return articleManagerServiceImpl.getArticlePage(params);
		} catch (Exception e) {
			logger.error("【SearchIndex.searchArticle】检索文章出错:" + e);
			throw e;
		}
	}
}
