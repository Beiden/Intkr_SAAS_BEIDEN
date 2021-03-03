package com.intkr.saas.distributed.search.lucene;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Beiden
 * @date 2017-12-4
 * @version 1.0
 */
public abstract class LuceneBase {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private IndexReader reader;

	private IndexWriter writer;

	public abstract String getDataDirectory();

	public IndexReader getIndexReader() {
		if (reader == null) {
			synchronized (this) {
				if (reader == null) {
					reader = LuceneUtil.getIndexReader(getDataDirectory());
				}
			}
		}
		if (writer == null) {
			return reader;
		}
		try {
			synchronized (this) {
				if (writer == null) {
					return reader;
				}
				IndexReader readerTmp = DirectoryReader.openIfChanged((DirectoryReader) reader, writer, true);
				if (readerTmp != null) {
					this.reader = readerTmp;
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException("", e);
		}
		return reader;
	}

	public IndexWriter getIndexWriter() {
		if (writer == null) {
			synchronized (this) {
				if (writer == null) {
					writer = LuceneUtil.getIndexWriter(getDataDirectory());
				}
			}
		}
		return writer;
	}

}
