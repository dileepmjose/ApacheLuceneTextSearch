package com.lucene.file;


import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class LuceneSearch {
	
   String indexDir = "index";
  
   FileIndexSearch searcher;

   public static void main(String[] args) {
	   LuceneSearch tester;
      try {
         tester = new LuceneSearch();
         tester.search("Biarritz");
      } catch (IOException e) {
         e.printStackTrace();
      } catch (ParseException e) {
         e.printStackTrace();
      }
   }

   private void search(String searchQuery) throws IOException, ParseException {
      searcher = new FileIndexSearch(indexDir);
      long startTime = System.currentTimeMillis();
      TopDocs hits = searcher.search(searchQuery);
      long endTime = System.currentTimeMillis();

      System.out.println(hits.totalHits +
         " documents found. Time :" + (endTime - startTime) +" ms");
      for(ScoreDoc scoreDoc : hits.scoreDocs) {
         Document doc = searcher.getDocument(scoreDoc);
         System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH));
         System.out.println("File: "+ doc.get(LuceneConstants.CONTENTS));
      }
      searcher.close();
   }	
}