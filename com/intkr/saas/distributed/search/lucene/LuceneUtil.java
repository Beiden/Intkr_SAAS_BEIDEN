package com.intkr.saas.distributed.search.lucene;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Beiden
 * @date 2011-7-8 下午2:46:18
 * @version 1.0
 */
public class LuceneUtil {

	protected static final Logger logger = LoggerFactory.getLogger(LuceneUtil.class);

	private static Map<String, IndexWriter> indexWriterMap = new HashMap<String, IndexWriter>();

	public static IndexWriter getIndexWriter(String directoryString) {
		if (indexWriterMap.containsKey(directoryString)) {
			return indexWriterMap.get(directoryString);
		}
		synchronized (LuceneUtil.class) {
			if (indexWriterMap.containsKey(directoryString)) {
				return indexWriterMap.get(directoryString);
			}
			try {
				File indexDir = new File(directoryString);
				Analyzer luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_40);
				IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, luceneAnalyzer);
				config.setOpenMode(OpenMode.CREATE_OR_APPEND);
				Directory directory = FSDirectory.open(indexDir);
				IndexWriter indexWriter = new IndexWriter(directory, config);
				indexWriterMap.put(directoryString, indexWriter);
				return indexWriter;
			} catch (Exception e) {
				logger.error("", e);
				throw new RuntimeException("", e);
			}
		}
	}

	public synchronized static IndexReader getIndexReader(String directoryString) {
		try {
			File file = new File(directoryString);
			if (!file.exists()) {
				file.mkdirs();
			}
			IndexReader reader = DirectoryReader.open(FSDirectory.open(file));
			return reader;
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException("", e);
		}
	}

	// 过滤特殊字符
	public static String filterSpe(String str) {
		// 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ /-]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

}
